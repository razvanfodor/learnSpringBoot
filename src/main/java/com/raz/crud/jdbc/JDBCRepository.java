package com.raz.crud.jdbc;

import org.slf4j.Logger;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class JDBCRepository {

    @Inject
    private Logger logger;

    @PersistenceContext
    private EntityManager entityManager;

    public void printAll(){
        logger.info("Printing discounts");
        entityManager.createNativeQuery("select * from discount").getResultList().stream().forEach( d -> {
            Object[] dd = (Object[]) d;
            logger.info("Id: {} Name: {}", dd[0], dd[1]);
        });
    }
}
