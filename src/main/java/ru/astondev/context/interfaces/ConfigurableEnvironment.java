package ru.astondev.context.interfaces;

import java.util.Map;

public interface ConfigurableEnvironment extends Environment, ConfigurablePropertyResolver {
    void setActiveProfiles(String... profiles);

    void addActiveProfile(String profile);

    void setDefaultProfiles(String... profiles);

    Map<String, Object> getSystemProperties();

    Map<String, Object> getSystemEnvironment();

    void merge(ConfigurableEnvironment parent);
}
