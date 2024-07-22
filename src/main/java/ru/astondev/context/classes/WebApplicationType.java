package ru.astondev.context.classes;

import ru.astondev.context.utils.ClassUtils;

public enum WebApplicationType {
    NONE,
    SERVLET,
    REACTIVE;

    private static final String[] SERVLET_INDICATOR_CLASSES = new String[]{"jakarta.servlet.Servlet", "org.springframework.web.context.ConfigurableWebApplicationContext"};

    static WebApplicationType deduceFromClasspath() {
        if (ClassUtils.isPresent("org.springframework.web.reactive.DispatcherHandler", (ClassLoader)null) && !ClassUtils.isPresent("org.springframework.web.servlet.DispatcherServlet", (ClassLoader)null) && !ClassUtils.isPresent("org.glassfish.jersey.servlet.ServletContainer", (ClassLoader)null)) {
            return REACTIVE;
        } else {
            String[] var0 = SERVLET_INDICATOR_CLASSES;
            int var1 = var0.length;

            for(int var2 = 0; var2 < var1; ++var2) {
                String className = var0[var2];
                if (!ClassUtils.isPresent(className, (ClassLoader)null)) {
                    return NONE;
                }
            }

            return SERVLET;
        }
    }
}
