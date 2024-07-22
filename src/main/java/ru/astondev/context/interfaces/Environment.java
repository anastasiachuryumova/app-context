package ru.astondev.context.interfaces;

public interface Environment extends PropertyResolver {
    String[] getActiveProfiles();

    String[] getDefaultProfiles();

    default boolean matchesProfiles(String... profileExpressions) {
        return this.acceptsProfiles(Profiles.of(profileExpressions));
    }

    /** @deprecated */
    @Deprecated
    boolean acceptsProfiles(String... profiles);

    boolean acceptsProfiles(Profiles profiles);
}
