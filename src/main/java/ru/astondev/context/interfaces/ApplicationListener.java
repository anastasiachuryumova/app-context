package ru.astondev.context.interfaces;

import ru.astondev.context.classes.PayloadApplicationEvent;
import ru.astondev.context.classes.ApplicationEvent;

import java.util.EventListener;
import java.util.function.Consumer;

@FunctionalInterface
public interface ApplicationListener<E extends ApplicationEvent> extends EventListener {
    void onApplicationEvent(E event);

    static <T> ApplicationListener<PayloadApplicationEvent<T>> forPayload(Consumer<T> consumer) {
        return (event) -> {
            consumer.accept(event.getPayload());
        };
    }
}
