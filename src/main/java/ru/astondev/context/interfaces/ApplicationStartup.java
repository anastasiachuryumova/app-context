package ru.astondev.context.interfaces;

public interface ApplicationStartup {
    ApplicationStartup DEFAULT = new DefaultApplicationStartup();

    StartupStep start(String name);
}
