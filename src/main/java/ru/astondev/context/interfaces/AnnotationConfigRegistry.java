package ru.astondev.context.interfaces;

public interface AnnotationConfigRegistry {
    void register(Class<?>... componentClasses);

    void scan(String... basePackages);
}
