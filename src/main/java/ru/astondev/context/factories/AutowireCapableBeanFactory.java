package ru.astondev.context.factories;

import ru.astondev.context.annotations.Nullable;

public interface AutowireCapableBeanFactory extends BeanFactory {
    @Nullable
    BeanFactory getParentBeanFactory();

    boolean containsLocalBean(String name);
}
