package com.raz.crud.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Profile("int")
public class ConfigInt {

    @Autowired
    public void initContext(ApplicationContext appContext){
        System.out.println(appContext.getDisplayName() + " started in mode INTEGRATION");
        System.out.println("Note that this method is called on bean initialisation because of @Autowired");
    }
}
