package com.mprog.traindemo1.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration()
public class I18nConfig {

    @Bean
    public MessageSource messageSource(){
        var src = new ReloadableResourceBundleMessageSource();
        src.setDefaultEncoding("UTF-8");
        src.setBasename("bundle");
        src.setUseCodeAsDefaultMessage(true);
        return src;
    }
}
