package com.raz.crud.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class ConfigDev {

    @Autowired
    public void doSomething(AnnotationConfigServletWebServerApplicationContext appContext) {
        System.out.println(appContext.getDisplayName() + " started in mode DEVELOP");
        System.out.println("Note that this method is called on bean initialisation because of @Autowired");
    }

}
