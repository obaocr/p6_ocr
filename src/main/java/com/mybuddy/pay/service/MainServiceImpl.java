package com.mybuddy.pay.service;

import com.mybuddy.pay.dao.*;
import com.mybuddy.pay.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


    private static final Logger log = LogManager.getLogger(RelationDaoImpl.class);

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
        if(user != null  && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    @Override
    public ServiceResponse addRelationByEmail(long userId, String relationEmail) {

        try {
            log.info("addRelationByEmail");
            //Recherche des ID des personnes
            User userRel = userDao.getByEmail(relationEmail);
            if (userRel == null) {
                return response(false, "Person not found for :" +relationEmail);
            }

            if (relationDao.countRelationByEmail(userId, relationEmail) == 0) {
                return relationDao.addRelationById(userId, userRel.getId()) > 0 ? response(true,"OK") : response(false, "Relation creation failure");
            } else {
                return response(false, "Relation already exists for :" + userId + " et " + relationEmail);
            }
        } catch (Exception e) {
            log.error("Exception : " + e.toString());
            return response(false, e.toString());
        }

    }

    @Override
    public ServiceResponse creditAccount(long userId, Double amount, String description) {
        try {
            log.info("creditAccount");
            //Recherche des infos du compte
            AccountUser accountUser = accountUserDao.getByUserId(userId);
            if (accountUser == null) {
                return response(false, "AccountPerson not found : " + userId);
            }
            // Creation de l'opération
            //Operation operation = new Operation();
            Operation operation = new Operation(accountUser.getId(), null, amount, 0.0, "PAYMENT","C", description,null,null);
            if (operationDao.createOperation(operation) > 0) {
                // Mise a jour de la balance
                Double balance = accountUser.getBalance() + amount;
                int nbUpd = accountDao.updateBalance(accountUser.getId(), balance);
                return nbUpd > 0 ? response(true, "Balance update OK : " + nbUpd) : response(false, "Balance update failure");
            } else {
                return response(false, "Non-created payment transaction : " + userId);
            }
        } catch (Exception e) {
            log.error("Exception : " + e.toString());
            return response(false, e.toString());
        }
    }

    @Override
    public ServiceResponse externalTransferAccount(long userId, Double amount, String description) {
        try {
            log.info("externalTransferAccount");
            //Recherche des infos du compte
            AccountUser accountUser = accountUserDao.getByUserId(userId);
            if (accountUser == null) {
                return response(false, "AccountPerson not found : " + userId);
            }
            if (accountUser.getBalance() < amount) {
                return response(false, "Operation impossible, insufficient balance :" + userId);
            }
            // Creation de l'opération
            //Operation operation = new Operation();
            Operation operation = new Operation(accountUser.getId(), null, amount, 0.0, "WITHDRAWAL","D", description,accountUser.getBic(),accountUser.getIban());
            if (operationDao.createOperation(operation) > 0) {
                // Mise a jour de la balance
                Double balance = accountUser.getBalance() - amount;
                int nbUpd = accountDao.updateBalance(accountUser.getId(), balance);
                return nbUpd > 0 ? response(true, "Balance update OK : " + nbUpd) : response(false, "Balance update failure");
            } else {
                return response(false, "Operation de virement non créée  : " + userId);
            }
        } catch (Exception e) {
            log.error("Exception : " + e.toString());
            return response(false, e.toString());
        }
    }

    @Override
    public ServiceResponse transferToAnotherAccount(long userId, String beneficiaryEmail, Double amount, String description) {
        try {
            log.info("transferToAnotherAccount");

            // Recherche du taux de commission
            RateFee rateFee = rateDao.getRate("RTFEE");
            if (rateFee == null) {
                return response(false, "Operation impossible: rate not found");
            }
            // Calcul commisssion avec arrondis
            double feeAmount = (amount * rateFee.getRate())/100.0;
            feeAmount = Math.round(feeAmount * 100) / 100.0;
            double totalAmountWithFee = amount + feeAmount;
            //Recherche des infos du compte tiers
            AccountUser accountUserBenef = accountUserDao.getByEmail(beneficiaryEmail);
            if (accountUserBenef == null) {
                return response(false, "Third party account not found for :" +beneficiaryEmail);
            }

            // recherche info compte
            AccountUser accountUser = accountUserDao.getByUserId(userId);
            if (accountUser == null) {
                return response(false, "AccountPerson not found : " + userId);
            }
            if (accountUser.getBalance() < totalAmountWithFee) {
                return response(false, "Operation impossible, insufficient balance: " + userId);
            }
            // Verification existence de la relation
            if (relationDao.countRelationByEmail(accountUser.getId(), beneficiaryEmail) == 0) {
                return response(false, "Operation impossible, non-existent relation : " + beneficiaryEmail);
            }
            log.info("Taux de commission:"+rateFee.getRate());
            // Creation de l'opération du compte
            //Operation operation = new Operation();
            Operation operation = new Operation(accountUserBenef.getId(), accountUserBenef.getId(), amount, feeAmount, "TRANSFER","D", description,null,null);
            if (operationDao.createOperation(operation) > 0) {
                // Mise a jour de la balance
                Double balance = accountUser.getBalance() - totalAmountWithFee;
                if(accountDao.updateBalance(accountUser.getId(), balance) == 0) {
                       response(false, "Balance update failure");
                }
            } else {
                response(false, "Operation creation failure");
            }
            // Creation de l'opération du compte tiers
            Operation operationBenef = new Operation(accountUserBenef.getId(), null, amount, 0.0, "TRANSFER","C", description,null,null);
            if (operationDao.createOperation(operationBenef) > 0) {
                // Mise a jour de la balance
                Double balanceBenef = accountUserBenef.getBalance() + amount;
                int nbUpdBenef = accountDao.updateBalance(accountUserBenef.getId(), balanceBenef);
                return nbUpdBenef > 0 ? response(true, "Beneficiary balance update OK: " + nbUpdBenef) : response(false, "Failure to update the beneficiary balance");
            } else {
                return response(false, "Beneficiary transfer operation not created : " + userId);
            }
        } catch (Exception e) {
            log.error("Exception : " + e.toString());
            return response(false, e.toString());
        }
    }
}
