package ru.astondev.context.interfaces;

import java.util.Collections;
import java.util.Iterator;
import java.util.function.Supplier;

public class DefaultApplicationStartup implements ApplicationStartup {
    private static final DefaultApplicationStartup.DefaultStartupStep DEFAULT_STARTUP_STEP = new DefaultApplicationStartup.DefaultStartupStep();

    DefaultApplicationStartup() {
    }

    public DefaultApplicationStartup.DefaultStartupStep start(String name) {
        return DEFAULT_STARTUP_STEP;
    }

    static class DefaultStartupStep implements StartupStep {
        private final DefaultApplicationStartup.DefaultStartupStep.DefaultTags TAGS = new DefaultApplicationStartup.DefaultStartupStep.DefaultTags();

        DefaultStartupStep() {
        }

        public String getName() {
            return "default";
        }

        public long getId() {
            return 0L;
        }

        public Long getParentId() {
            return null;
        }

        public Tags getTags() {
            return this.TAGS;
        }

        public StartupStep tag(String key, String value) {
            return this;
        }

        public StartupStep tag(String key, Supplier<String> value) {
            return this;
        }

        public void end() {
        }

        static class DefaultTags implements Tags {
            DefaultTags() {
            }

            public Iterator<Tag> iterator() {
                return Collections.emptyIterator();
            }
        }
    }
}
