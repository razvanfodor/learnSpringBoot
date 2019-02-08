package com.raz.crud.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class Config {

    @Bean
    @Scope("prototype")
    public Logger getLogger(InjectionPoint injectionPoint){
        return LoggerFactory.getLogger(injectionPoint.getMember().getDeclaringClass());
    }

}
