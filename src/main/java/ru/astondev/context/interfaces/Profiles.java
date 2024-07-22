package ru.astondev.context.interfaces;

import java.util.function.Predicate;

@FunctionalInterface
public interface Profiles {
    boolean matches(Predicate<String> activeProfiles);

    static Profiles of(String... profileExpressions) {
        return ProfilesParser.parse(profileExpressions);
    }
}
