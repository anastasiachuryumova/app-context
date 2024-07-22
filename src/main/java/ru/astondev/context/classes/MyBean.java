package ru.astondev.context.classes;

import ru.astondev.context.context.ApplicationContext;
import ru.astondev.context.exceptions.BeansException;
import ru.astondev.context.interfaces.ApplicationContextAware;

public class MyBean implements ApplicationContextAware {
    private String applicationId;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        applicationId = applicationContext.getId();
    }

    public String getApplicationId() {

        return applicationId;
    }
}
