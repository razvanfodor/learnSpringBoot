package com.raz.crud.config;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class ConfigDev {

    @Autowired
    public void doSomething(AnnotationConfigServletWebServerApplicationContext appContext, Logger logger) {
        logger.info(appContext.getDisplayName() + " started in mode DEVELOP");
        logger.info("Note that this method is called on bean initialisation because of @Autowired");
    }

}
