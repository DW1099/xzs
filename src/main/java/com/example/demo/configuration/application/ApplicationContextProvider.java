package com.example.demo.configuration.application;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ApplicationContextProvider implements ApplicationContextAware {
        private static ApplicationContext context;

        private ApplicationContextProvider(){

        }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context=applicationContext;
    }

    public static <T> T getBean(Class<T> aClass){
        return  context.getBean(aClass);
    }

    public static <T> T getBean(String name){
            return (T)context.getBean(name);
    }
}
