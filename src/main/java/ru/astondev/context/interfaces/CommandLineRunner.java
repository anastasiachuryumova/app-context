package ru.astondev.context.interfaces;

@FunctionalInterface
public interface CommandLineRunner {
    void run(String... args) throws Exception;
}
