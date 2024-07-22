package ru.astondev.context.context;

import ru.astondev.context.interfaces.ApplicationListener;
import ru.astondev.context.interfaces.ApplicationStartup;
import ru.astondev.context.interfaces.BeanFactoryPostProcessor;
import ru.astondev.context.interfaces.ConfigurableEnvironment;
import ru.astondev.context.interfaces.Lifecycle;
import ru.astondev.context.interfaces.ProtocolResolver;
import ru.astondev.context.annotations.Nullable;
import ru.astondev.context.exceptions.BeansException;
import ru.astondev.context.factories.ConfigurableListableBeanFactory;

import java.io.Closeable;

public interface ConfigurableApplicationContext extends ApplicationContext, Lifecycle, Closeable {
    String CONFIG_LOCATION_DELIMITERS = ",; \t\n";
    String CONVERSION_SERVICE_BEAN_NAME = "conversionService";
    String LOAD_TIME_WEAVER_BEAN_NAME = "loadTimeWeaver";
    String ENVIRONMENT_BEAN_NAME = "environment";
    String SYSTEM_PROPERTIES_BEAN_NAME = "systemProperties";
    String SYSTEM_ENVIRONMENT_BEAN_NAME = "systemEnvironment";
    String APPLICATION_STARTUP_BEAN_NAME = "applicationStartup";
    String SHUTDOWN_HOOK_THREAD_NAME = "SpringContextShutdownHook";

    void setId(String id);

    void setParent(@Nullable ApplicationContext parent);

    void setEnvironment(ConfigurableEnvironment environment);

    ConfigurableEnvironment getEnvironment();

    void setApplicationStartup(ApplicationStartup applicationStartup);

    ApplicationStartup getApplicationStartup();

    void addBeanFactoryPostProcessor(BeanFactoryPostProcessor postProcessor);

    void addApplicationListener(ApplicationListener<?> listener);

    void removeApplicationListener(ApplicationListener<?> listener);

    void setClassLoader(ClassLoader classLoader);

    void addProtocolResolver(ProtocolResolver resolver);

    void refresh() throws BeansException, IllegalStateException;

    void registerShutdownHook();

    void close();

    boolean isActive();

    ConfigurableListableBeanFactory getBeanFactory() throws IllegalStateException;
}
