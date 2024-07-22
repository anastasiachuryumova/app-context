package ru.astondev.context.context;

import ru.astondev.context.classes.Assert;
import ru.astondev.context.interfaces.Scope;
import ru.astondev.context.annotations.Nullable;
import ru.astondev.context.exceptions.BeansException;
import ru.astondev.context.factories.ConfigurableBeanFactory;

public class BeanExpressionContext {
    private final ConfigurableBeanFactory beanFactory;
    @Nullable
    private final Scope scope;

    public BeanExpressionContext(ConfigurableBeanFactory beanFactory, @Nullable Scope scope) {
        Assert.notNull(beanFactory, "BeanFactory must not be null");
        this.beanFactory = beanFactory;
        this.scope = scope;
    }

    public final ConfigurableBeanFactory getBeanFactory() {
        return this.beanFactory;
    }

    @Nullable
    public final Scope getScope() {
        return this.scope;
    }

    public boolean containsObject(String key) {
        return this.beanFactory.containsBean(key) || this.scope != null && this.scope.resolveContextualObject(key) != null;
    }

    @Nullable
    public Object getObject(String key) throws BeansException {
        if (this.beanFactory.containsBean(key)) {
            return this.beanFactory.getBean(key);
        } else {
            return this.scope != null ? this.scope.resolveContextualObject(key) : null;
        }
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        } else if (!(other instanceof BeanExpressionContext)) {
            return false;
        } else {
            BeanExpressionContext otherContext = (BeanExpressionContext)other;
            return this.beanFactory == otherContext.beanFactory && this.scope == otherContext.scope;
        }
    }

    public int hashCode() {
        return this.beanFactory.hashCode();
    }
}
