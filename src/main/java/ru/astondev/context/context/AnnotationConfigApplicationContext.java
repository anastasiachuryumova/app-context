package ru.astondev.context.context;

import ru.astondev.context.classes.AnnotatedBeanDefinitionReader;
import ru.astondev.context.interfaces.AnnotationConfigRegistry;
import ru.astondev.context.classes.ClassPathBeanDefinitionScanner;
import ru.astondev.context.interfaces.StartupStep;
import ru.astondev.context.exceptions.BeansException;

import java.util.Arrays;

public class AnnotationConfigApplicationContext extends GenericApplicationContext implements AnnotationConfigRegistry {
    private final AnnotatedBeanDefinitionReader reader;
    private final ClassPathBeanDefinitionScanner scanner;

    public AnnotationConfigApplicationContext() {
        StartupStep createAnnotatedBeanDefReader = this.getApplicationStartup().start("spring.context.annotated-bean-reader.create");
        this.reader = new AnnotatedBeanDefinitionReader(this);
        createAnnotatedBeanDefReader.end();
        this.scanner = new ClassPathBeanDefinitionScanner(this);
    }
    public AnnotationConfigApplicationContext(Class<?>... componentClasses) throws BeansException {
        this();
        this.register(componentClasses);
        this.refresh();
    }

    public AnnotationConfigApplicationContext(String... basePackages) throws BeansException {
        this();
        this.scan(basePackages);
        this.refresh();
    }

    public void register(Class<?>... componentClasses) {
        StartupStep registerComponentClass = this.getApplicationStartup().start("spring.context.component-classes.register").tag("classes", () -> {
            return Arrays.toString(componentClasses);
        });
        this.reader.register(componentClasses);
        registerComponentClass.end();
    }

    public void scan(String... basePackages) {
        StartupStep scanPackages = this.getApplicationStartup().start("spring.context.base-packages.scan").tag("packages", () -> {
            return Arrays.toString(basePackages);
        });
        this.scanner.scan(basePackages);
        scanPackages.end();
    }
}
