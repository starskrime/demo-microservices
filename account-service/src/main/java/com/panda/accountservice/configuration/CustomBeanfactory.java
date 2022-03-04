package com.panda.accountservice.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomBeanfactory {
    @Bean
    public ModelMapper getModelMapper(){
        return new ModelMapper();

    }
}
