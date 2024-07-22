package ru.astondev.context.interfaces;

import ru.astondev.context.annotations.Nullable;
import ru.astondev.context.exceptions.MissingRequiredPropertiesException;

public interface ConfigurablePropertyResolver extends PropertyResolver {

    void setPlaceholderPrefix(String placeholderPrefix);

    void setPlaceholderSuffix(String placeholderSuffix);

    void setValueSeparator(@Nullable String valueSeparator);

    void setIgnoreUnresolvableNestedPlaceholders(boolean ignoreUnresolvableNestedPlaceholders);

    void setRequiredProperties(String... requiredProperties);

    void validateRequiredProperties() throws MissingRequiredPropertiesException;
}
