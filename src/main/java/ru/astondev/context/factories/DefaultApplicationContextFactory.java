package ru.astondev.context.factories;

import ru.astondev.context.context.AnnotationConfigApplicationContext;
import ru.astondev.context.classes.AotDetector;
import ru.astondev.context.context.ConfigurableApplicationContext;
import ru.astondev.context.interfaces.ConfigurableEnvironment;
import ru.astondev.context.context.GenericApplicationContext;
import ru.astondev.context.classes.SpringFactoriesLoader;
import ru.astondev.context.classes.WebApplicationType;

import java.util.Iterator;
import java.util.function.BiFunction;
import java.util.function.Supplier;

public class DefaultApplicationContextFactory implements ApplicationContextFactory {
    DefaultApplicationContextFactory() {
    }

    public Class<? extends ConfigurableEnvironment> getEnvironmentType(WebApplicationType webApplicationType) {
        return (Class)this.getFromSpringFactories(webApplicationType, ApplicationContextFactory::getEnvironmentType, (Supplier)null);
    }

    public ConfigurableEnvironment createEnvironment(WebApplicationType webApplicationType) {
        return (ConfigurableEnvironment)this.getFromSpringFactories(webApplicationType, ApplicationContextFactory::createEnvironment, (Supplier)null);
    }

    public ConfigurableApplicationContext create(WebApplicationType webApplicationType) {
        try {
            return (ConfigurableApplicationContext)this.getFromSpringFactories(webApplicationType, ApplicationContextFactory::create, this::createDefaultApplicationContext);
        } catch (Exception var3) {
            throw new IllegalStateException("Unable create a default ApplicationContext instance, you may need a custom ApplicationContextFactory", var3);
        }
    }

    private ConfigurableApplicationContext createDefaultApplicationContext() {
        return (ConfigurableApplicationContext)(!AotDetector.useGeneratedArtifacts() ? new AnnotationConfigApplicationContext() : new GenericApplicationContext());
    }

    private <T> T getFromSpringFactories(WebApplicationType webApplicationType, BiFunction<ApplicationContextFactory, WebApplicationType, T> action, Supplier<T> defaultResult) {
        Iterator var4 = SpringFactoriesLoader.loadFactories(ApplicationContextFactory.class, this.getClass().getClassLoader()).iterator();

        Object result;
        do {
            if (!var4.hasNext()) {
                return defaultResult != null ? defaultResult.get() : null;
            }

            ApplicationContextFactory candidate = (ApplicationContextFactory)var4.next();
            result = action.apply(candidate, webApplicationType);
        } while(result == null);

        return (T) result;
    }
}
