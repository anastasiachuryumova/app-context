package ru.astondev.context.factories;

import ru.astondev.context.interfaces.ApplicationStartup;
import ru.astondev.context.interfaces.BeanDefinition;
import ru.astondev.context.interfaces.BeanExpressionResolver;
import ru.astondev.context.interfaces.BeanPostProcessor;
import ru.astondev.context.interfaces.Scope;
import ru.astondev.context.interfaces.SingletonBeanRegistry;
import ru.astondev.context.interfaces.StringValueResolver;
import ru.astondev.context.interfaces.TypeConverter;
import ru.astondev.context.annotations.Nullable;
import ru.astondev.context.exceptions.BeanDefinitionStoreException;
import ru.astondev.context.exceptions.NoSuchBeanDefinitionException;

public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {
    String SCOPE_SINGLETON = "singleton";
    String SCOPE_PROTOTYPE = "prototype";

    void setParentBeanFactory(BeanFactory parentBeanFactory) throws IllegalStateException;

    void setBeanClassLoader(@Nullable ClassLoader beanClassLoader);

    @Nullable
    ClassLoader getBeanClassLoader();

    void setTempClassLoader(@Nullable ClassLoader tempClassLoader);

    @Nullable
    ClassLoader getTempClassLoader();

    void setCacheBeanMetadata(boolean cacheBeanMetadata);

    boolean isCacheBeanMetadata();

    void setBeanExpressionResolver(@Nullable BeanExpressionResolver resolver);

    @Nullable
    BeanExpressionResolver getBeanExpressionResolver();

//    void setConversionService(@Nullable ConversionService conversionService);
//
//    @Nullable
//    ConversionService getConversionService();

//    void addPropertyEditorRegistrar(PropertyEditorRegistrar registrar);
//
//    void registerCustomEditor(Class<?> requiredType, Class<? extends PropertyEditor> propertyEditorClass);
//
//    void copyRegisteredEditorsTo(PropertyEditorRegistry registry);

    void setTypeConverter(TypeConverter typeConverter);

    TypeConverter getTypeConverter();

    void addEmbeddedValueResolver(StringValueResolver valueResolver);

    boolean hasEmbeddedValueResolver();

    @Nullable
    String resolveEmbeddedValue(String value);

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    int getBeanPostProcessorCount();

    void registerScope(String scopeName, Scope scope);

    String[] getRegisteredScopeNames();

    @Nullable
    Scope getRegisteredScope(String scopeName);

    void setApplicationStartup(ApplicationStartup applicationStartup);

    ApplicationStartup getApplicationStartup();

    void copyConfigurationFrom(ConfigurableBeanFactory otherFactory);

    void registerAlias(String beanName, String alias) throws BeanDefinitionStoreException;

    void resolveAliases(StringValueResolver valueResolver);

    BeanDefinition getMergedBeanDefinition(String beanName) throws NoSuchBeanDefinitionException;

    boolean isFactoryBean(String name) throws NoSuchBeanDefinitionException;

    void setCurrentlyInCreation(String beanName, boolean inCreation);

    boolean isCurrentlyInCreation(String beanName);

    void registerDependentBean(String beanName, String dependentBeanName);

    String[] getDependentBeans(String beanName);

    String[] getDependenciesForBean(String beanName);

    void destroyBean(String beanName, Object beanInstance);

    void destroyScopedBean(String beanName);

    void destroySingletons();
}
