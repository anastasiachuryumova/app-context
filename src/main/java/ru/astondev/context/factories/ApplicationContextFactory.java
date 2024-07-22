package ru.astondev.context.factories;

import ru.astondev.context.context.ConfigurableApplicationContext;
import ru.astondev.context.interfaces.ConfigurableEnvironment;
import ru.astondev.context.classes.WebApplicationType;

import java.util.function.Supplier;

@FunctionalInterface
public interface ApplicationContextFactory {
    ApplicationContextFactory DEFAULT = new DefaultApplicationContextFactory();

    default Class<? extends ConfigurableEnvironment> getEnvironmentType(WebApplicationType webApplicationType) {
        return null;
    }

    default ConfigurableEnvironment createEnvironment(WebApplicationType webApplicationType) {return null;
    }

    ConfigurableApplicationContext create(WebApplicationType webApplicationType);

    static ApplicationContextFactory of(Supplier<ConfigurableApplicationContext> supplier) {
        return (webApplicationType) -> {
            return (ConfigurableApplicationContext)supplier.get();
        };
    }
}
