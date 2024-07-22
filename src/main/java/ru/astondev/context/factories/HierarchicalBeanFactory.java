package ru.astondev.context.factories;

import ru.astondev.context.annotations.Nullable;
import ru.astondev.context.factories.BeanFactory;

public interface HierarchicalBeanFactory extends BeanFactory {
    @Nullable
    BeanFactory getParentBeanFactory();

    boolean containsLocalBean(String name);
    }
