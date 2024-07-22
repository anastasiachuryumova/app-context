package ru.astondev.context.context;

import ru.astondev.context.interfaces.ApplicationStartup;
import ru.astondev.context.classes.Assert;
import ru.astondev.context.interfaces.ConfigurableEnvironment;
import ru.astondev.context.interfaces.Environment;
import ru.astondev.context.classes.StandardEnvironment;
import ru.astondev.context.annotations.Nullable;

public abstract class AbstractApplicationContext implements ConfigurableApplicationContext {
    private ApplicationStartup applicationStartup;
    @Nullable
    private ApplicationContext parent;
    @Nullable
    private ConfigurableEnvironment environment;
    public AbstractApplicationContext() {
        this.applicationStartup = ApplicationStartup.DEFAULT;
    }
    public AbstractApplicationContext(@Nullable ApplicationContext parent) {
        this();
        this.setParent(parent);
    }
    public void setApplicationStartup(ApplicationStartup applicationStartup) {
        Assert.notNull(applicationStartup, "ApplicationStartup must not be null");
        this.applicationStartup = applicationStartup;
    }
    public ApplicationStartup getApplicationStartup() {
        return this.applicationStartup;
    }

    public void setParent(@Nullable ApplicationContext parent) {
        this.parent = parent;
        if (parent != null) {
            Environment parentEnvironment = parent.getEnvironment();
            if (parentEnvironment instanceof ConfigurableEnvironment) {
                ConfigurableEnvironment configurableEnvironment = (ConfigurableEnvironment)parentEnvironment;
                this.getEnvironment().merge(configurableEnvironment);
            }
        }

    }
    public ConfigurableEnvironment getEnvironment() {
        if (this.environment == null) {
            this.environment = this.createEnvironment();
        }

        return this.environment;
    }
    protected ConfigurableEnvironment createEnvironment() {
        return new StandardEnvironment();
    }
}
