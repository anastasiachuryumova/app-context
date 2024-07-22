package ru.astondev.context.classes;

import ru.astondev.context.annotations.Nullable;

import java.util.Properties;

public final class SpringProperties {
    private static final Properties localProperties = new Properties();
    private SpringProperties() {
    }
    @Nullable
    public static String getProperty(String key) {
        String value = localProperties.getProperty(key);
        if (value == null) {
            try {
                value = System.getProperty(key);
            } catch (Throwable var3) {
                System.err.println("Could not retrieve system property '" + key + "': " + var3);
            }
        }

        return value;
    }
    public static boolean getFlag(String key) {
        return Boolean.parseBoolean(getProperty(key));
    }
}
