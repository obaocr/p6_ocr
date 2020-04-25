package com.mybuddy.pay.service;

import com.mybuddy.pay.dao.PersonDao;
import com.mybuddy.pay.dao.RelationDao;
import com.mybuddy.pay.dao.RelationDaoImpl;
import com.mybuddy.pay.model.ServiceResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MainServiceImpl implements MainService {

    @Autowired
    PersonDao personDao;

    @Autowired
    RelationDao relationDao;

    private static final Logger log = LogManager.getLogger(RelationDaoImpl.class);

    private ServiceResult returnResult (boolean result, String message) {
        ServiceResult serviceResult = new ServiceResult();
        serviceResult.setResult(result);
        serviceResult.setMessage(message);
        return serviceResult;
    }

    @Override
    public ServiceResult addRelationByEmail(String accountEmail, String relationEmail) {

        try {
            //Recherche des ID des personnes
            long idAccount = personDao.getByEmail(accountEmail).getId();
            long idRelation = personDao.getByEmail(relationEmail).getId();
            if (relationDao.countRelationByEmail(accountEmail, relationEmail) == 0) {
                return relationDao.addRelationById(idAccount, idRelation) > 0 ? returnResult(true,"OK") : returnResult(false, "Echec de la création de la relation");
            } else {
                return returnResult(false, "Relation déjà existante pour " + accountEmail + " et " + relationEmail);
            }
        } catch (Exception e) {
            log.error("Exception : " + e.toString());
            return returnResult(false, e.toString());
        }

    }
}
