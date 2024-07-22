package ru.astondev.context.interfaces;

import ru.astondev.context.classes.ResolvableType;
import ru.astondev.context.annotations.Nullable;

public interface BeanDefinition {
    String SCOPE_SINGLETON = "singleton";
    String SCOPE_PROTOTYPE = "prototype";
    int ROLE_APPLICATION = 0;
    int ROLE_SUPPORT = 1;
    int ROLE_INFRASTRUCTURE = 2;

    void setParentName(@Nullable String parentName);

    @Nullable
    String getParentName();

    void setBeanClassName(@Nullable String beanClassName);

    @Nullable
    String getBeanClassName();

    void setScope(@Nullable String scope);

    @Nullable
    String getScope();

    void setLazyInit(boolean lazyInit);

    boolean isLazyInit();

    void setDependsOn(@Nullable String... dependsOn);

    @Nullable
    String[] getDependsOn();

    void setAutowireCandidate(boolean autowireCandidate);

    boolean isAutowireCandidate();

    void setPrimary(boolean primary);

    boolean isPrimary();

    void setFactoryBeanName(@Nullable String factoryBeanName);

    @Nullable
    String getFactoryBeanName();

    void setFactoryMethodName(@Nullable String factoryMethodName);

    @Nullable
    String getFactoryMethodName();

    //ConstructorArgumentValues getConstructorArgumentValues();

//    default boolean hasConstructorArgumentValues() {
//        return !this.getConstructorArgumentValues().isEmpty();
//    }

    //MutablePropertyValues getPropertyValues();

//    default boolean hasPropertyValues() {
//        return !this.getPropertyValues().isEmpty();
//    }

    void setInitMethodName(@Nullable String initMethodName);

    @Nullable
    String getInitMethodName();

    void setDestroyMethodName(@Nullable String destroyMethodName);

    @Nullable
    String getDestroyMethodName();

    void setRole(int role);

    int getRole();

    void setDescription(@Nullable String description);

    @Nullable
    String getDescription();

    ResolvableType getResolvableType();

    boolean isSingleton();

    boolean isPrototype();

    boolean isAbstract();

    @Nullable
    String getResourceDescription();

    @Nullable
    BeanDefinition getOriginatingBeanDefinition();
}
