package com.raz.crud.config;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("int")
public class ConfigInt {

    @Autowired
    public void initContext(ApplicationContext appContext, Logger logger){
        logger.info(appContext.getDisplayName() + " started in mode INTEGRATION");
        logger.info("Note that this method is called on bean initialisation because of @Autowired");
    }
}
