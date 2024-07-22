package ru.astondev.context.interfaces;

import ru.astondev.context.classes.ApplicationEvent;

@FunctionalInterface
public interface ApplicationEventPublisher {

    default void publishEvent(ApplicationEvent event) {
        this.publishEvent((Object)event);
    }

    void publishEvent(Object event);
}
