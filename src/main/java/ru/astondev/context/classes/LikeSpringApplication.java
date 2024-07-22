package ru.astondev.context.classes;

import ru.astondev.context.context.ConfigurableApplicationContext;
import ru.astondev.context.factories.ApplicationContextFactory;
import ru.astondev.context.interfaces.ApplicationStartup;
import ru.astondev.context.interfaces.ResourceLoader;

import java.time.Duration;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

public class LikeSpringApplication {
    private ApplicationContextFactory applicationContextFactory;
    private WebApplicationType webApplicationType;
    private Class<?> mainApplicationClass;
    private ApplicationStartup applicationStartup;

    public LikeSpringApplication(Class<?>... primarySources) {
        this((ResourceLoader)null, primarySources);
    }

    public LikeSpringApplication(ResourceLoader resourceLoader, Class<?>... primarySources) {
        this.applicationContextFactory = ApplicationContextFactory.DEFAULT;
        Assert.notNull(primarySources, "PrimarySources must not be null");
        this.webApplicationType = WebApplicationType.deduceFromClasspath();
        this.mainApplicationClass = this.deduceMainApplicationClass();
        this.applicationStartup = ApplicationStartup.DEFAULT;
    }

    private Class<?> deduceMainApplicationClass() {
        return (Class)((Optional)StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE).walk(this::findMainClass)).orElse((Object)null);
    }

    private Optional<Class<?>> findMainClass(Stream<StackWalker.StackFrame> stack) {
        return stack.filter((frame) -> {
            return Objects.equals(frame.getMethodName(), "main");
        }).findFirst().map(StackWalker.StackFrame::getDeclaringClass);
    }

    public ConfigurableApplicationContext run(String... args) {
        long startTime = System.nanoTime();
        ConfigurableApplicationContext context = null;

        try {
            context = this.createApplicationContext();
            context.setApplicationStartup(this.applicationStartup);

            try {
                if (context.isRunning()) {
                    Duration timeTakenToReady = Duration.ofNanos(System.nanoTime() - startTime);
                }
                return context;
            } catch (Throwable var11) {
                throw new IllegalStateException(var11);
            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
        return context;
    }
    protected ConfigurableApplicationContext createApplicationContext () {
            return applicationContextFactory.create(webApplicationType);
    }
    public static ConfigurableApplicationContext run(Class<?> primarySource, String... args) {
        return run(new Class[]{primarySource}, args);
    }

    public static ConfigurableApplicationContext run(Class<?>[] primarySources, String[] args) {
        return (new LikeSpringApplication(primarySources)).run(args);
    }
    public static void main(String[] args) {
        run(new Class[0], args);
    }
}
