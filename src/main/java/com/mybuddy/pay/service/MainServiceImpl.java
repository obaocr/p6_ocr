package com.mybuddy.pay.service;

import com.mybuddy.pay.Util.CalculateAmountFee;
import com.mybuddy.pay.constants.Message;
import com.mybuddy.pay.dao.*;
import com.mybuddy.pay.model.AccountUser;
import com.mybuddy.pay.model.Operation;
import com.mybuddy.pay.model.ServiceResponse;
import com.mybuddy.pay.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Interface MainServiceImpl
 * ==> En cas de problème SQL  (not found, update ou insert en échec ou non fait)  niveau DAO une exception est levée et un rollback est géré par Spring
 * Exception DAO : EmptyResultDataAccessException ou MyUncheckedCustomException
 * ==> Controles applicatifs (solde insuffisant, relation existante) : sont faits avant les mises à jour, retourne une réponse applicative et pas de mises  à jour effectuée
 */

@Service
@Transactional
public class MainServiceImpl implements MainService {

    @Autowired
    UserDao userDao;
    @Autowired
    AccountUserDao accountUserDao;
    @Autowired
    AccountDao accountDao;
    @Autowired
    RelationDao relationDao;
    @Autowired
    OperationDao operationDao;
    @Autowired
    RateDao rateDao;

    private static final Logger log = LogManager.getLogger(MainServiceImpl.class);

    private ServiceResponse response(boolean result, String message) {
        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setResult(result);
        serviceResponse.setMessage(message);
        return serviceResponse;
    }

    @Override
    @Transactional(readOnly = true)
    public User login(String email, String password) {
        log.info("login");
        User user = userDao.getByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    //
    // Gestion transaction OK, une msie à jour et si problème rollback global
    //
    @Override
    public ServiceResponse addRelationByEmail(long userId, String relationEmail) {
        log.info("addRelationByEmail");
        // Recherche des ID des personnes / si non trouvé exception standard EmptyResultDataAccessException et rollback
        User userRel = userDao.getByEmail(relationEmail);
        if (relationDao.countRelationByEmail(userId, relationEmail) == 0) {
            int nbIns = relationDao.addRelationById(userId, userRel.getId());
            log.info("addRelationByEmail OK, nb relation : " + nbIns);
            return response(true, Message.MSG_INFO_001);
        } else {
            return response(false, Message.MSG_ERR_001 + userId + " => " + relationEmail);
        }
    }

    //
    // Rollback a gerer si une des deux mises  à jour plante
    //
    @Override
    public ServiceResponse creditAccount(long userId, Double amount, String description) {
        log.info("creditAccount");
        //Recherche des infos du compte / si non trouvé exception standard EmptyResultDataAccessException et rollback
        AccountUser accountUser = accountUserDao.getByUserId(userId);
        Operation operation = new Operation(accountUser.getId(), null, amount, 0.0, "PAYMENT", "C", description, null, null);
        int nbOperationIns = operationDao.createOperation(operation);
        int nbUpd = accountDao.updateBalance(accountUser.getId(), (accountUser.getBalance() + amount));
        log.info("creditAccount OK, nb operation  et nb update balance : " + nbOperationIns + "/" + nbUpd);
        return response(true, Message.MSG_INFO_002);
    }

    //
    // Rollback a gerer si une des deux mises  à jour plante
    //
    @Override
    public ServiceResponse externalTransferAccount(long userId, Double amount, String description) {
        log.info("externalTransferAccount");
        AccountUser accountUser = accountUserDao.getByUserId(userId);
        if (accountUser.getBalance() < amount) {
            return response(false, Message.MSG_ERR_002 + " :" + userId);
        }
        Operation operation = new Operation(accountUser.getId(), null, amount, 0.0, "WITHDRAWAL", "D", description, accountUser.getBic(), accountUser.getIban());
        int nbOperationIns = operationDao.createOperation(operation);
        int nbUpd = accountDao.updateBalance(accountUser.getId(), (accountUser.getBalance() - amount));
        log.info("externalTransferAccount OK, nb operation  et nb update balance : " + nbOperationIns + "/" + nbUpd);
        return response(true, Message.MSG_INFO_003);
    }

    //
    // Rollback a gerer si une des 4 mises  à jour plante
    //
    @Override
    public ServiceResponse transferToAnotherAccount(long userId, String beneficiaryEmail, Double amount, String description) {
        log.info("transferToAnotherAccount");
        AccountUser accountUserBenef = accountUserDao.getByEmail(beneficiaryEmail);
        AccountUser accountUser = accountUserDao.getByUserId(userId);
        double feeAmount = CalculateAmountFee.calculateFee(rateDao.getRate("RTFEE").getRate(), amount);
        double totalAmountWithFee = amount + feeAmount;
        if (accountUser.getBalance() < totalAmountWithFee) {
            return response(false, Message.MSG_ERR_002 + userId);
        }
        // Verification existence de la relation
        if (relationDao.countRelationByEmail(accountUser.getId(), beneficiaryEmail) == 0) {
            return response(false, Message.MSG_ERR_003 + beneficiaryEmail);
        }
        // Creation de l'opération du compte
        Operation operation = new Operation(accountUserBenef.getId(), accountUserBenef.getId(), amount, feeAmount, "TRANSFER", "D", description, null, null);
        int nbOperationInsAcc = operationDao.createOperation(operation);
        int nbUpdAcc = accountDao.updateBalance(accountUser.getId(), (accountUser.getBalance() - totalAmountWithFee));
        // Creation de l'opération du compte tiers
        Operation operationBenef = new Operation(accountUserBenef.getId(), null, amount, 0.0, "TRANSFER", "C", description, null, null);
        int nbOperationInsRel = operationDao.createOperation(operationBenef);
        int nbUpdRel = accountDao.updateBalance(accountUserBenef.getId(), (accountUserBenef.getBalance() + amount));
        //
        log.info("transferToAnotherAccount OK, nb operation  et nb update balance  Account et Relation: " + nbOperationInsAcc + "/" + nbUpdAcc + " :" + nbOperationInsRel + "/" + nbUpdRel);
        return response(true, Message.MSG_INFO_004);
    }

}
