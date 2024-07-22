package ru.astondev.context.interfaces;

public interface Lifecycle {
    void start();

    void stop();

    boolean isRunning();
}
