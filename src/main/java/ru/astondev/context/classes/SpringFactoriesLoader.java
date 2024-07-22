package ru.astondev.context.classes;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import ru.astondev.context.annotations.Nullable;
import ru.astondev.context.utils.ClassUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class SpringFactoriesLoader {
    private static final SpringFactoriesLoader.FailureHandler THROWING_FAILURE_HANDLER = SpringFactoriesLoader.FailureHandler.throwing();
    static final Map<ClassLoader, Map<String, SpringFactoriesLoader>> cache = new HashMap();
    private static final Log logger = LogFactory.getLog(SpringFactoriesLoader.class);
    @Nullable
    private final ClassLoader classLoader;
    private final Map<String, List<String>> factories;

    protected SpringFactoriesLoader(@Nullable ClassLoader classLoader, Map<String, List<String>> factories) {
        this.classLoader = classLoader;
        this.factories = factories;
    }

    public static <T> List<T> loadFactories(Class<T> factoryType, @Nullable ClassLoader classLoader) {
        return forDefaultResourceLocation(classLoader).load(factoryType);
    }
    public <T> List<T> load(Class<T> factoryType) {
        return this.load(factoryType, (SpringFactoriesLoader.ArgumentResolver)null, (SpringFactoriesLoader.FailureHandler)null);
    }
    public <T> List<T> load(Class<T> factoryType, @Nullable SpringFactoriesLoader.ArgumentResolver argumentResolver) {
        return this.load(factoryType, argumentResolver, (SpringFactoriesLoader.FailureHandler)null);
    }

    public <T> List<T> load(Class<T> factoryType, @Nullable SpringFactoriesLoader.FailureHandler failureHandler) {
        return this.load(factoryType, (SpringFactoriesLoader.ArgumentResolver)null, failureHandler);
    }

    public <T> List<T> load(Class<T> factoryType, @Nullable SpringFactoriesLoader.ArgumentResolver argumentResolver, @Nullable SpringFactoriesLoader.FailureHandler failureHandler) {
        Assert.notNull(factoryType, "'factoryType' must not be null");
        List<String> implementationNames = this.loadFactoryNames(factoryType);
        logger.trace(LogMessage.format("Loaded [%s] names: %s", factoryType.getName(), implementationNames));
        List<T> result = new ArrayList(implementationNames.size());
        SpringFactoriesLoader.FailureHandler failureHandlerToUse = failureHandler != null ? failureHandler : THROWING_FAILURE_HANDLER;
        Iterator var7 = implementationNames.iterator();

        while(var7.hasNext()) {
            String implementationName = (String)var7.next();
            T factory = this.instantiateFactory(implementationName, factoryType, argumentResolver, failureHandlerToUse);
            if (factory != null) {
                result.add(factory);
            }
        }
        return result;
    }
    private List<String> loadFactoryNames(Class<?> factoryType) {
        return (List)this.factories.getOrDefault(factoryType.getName(), Collections.emptyList());
    }

    public static SpringFactoriesLoader forDefaultResourceLocation(@Nullable ClassLoader classLoader) {
        return forResourceLocation("META-INF/spring.factories", classLoader);
    }
    public static SpringFactoriesLoader forResourceLocation(String resourceLocation, @Nullable ClassLoader classLoader) {
        ClassLoader resourceClassLoader = classLoader != null ? classLoader : SpringFactoriesLoader.class.getClassLoader();
        Map<String, SpringFactoriesLoader> loaders = (Map)cache;
        return (SpringFactoriesLoader)loaders.computeIfAbsent(resourceLocation, (key) -> {
            return new SpringFactoriesLoader(classLoader, loadFactoriesResource(resourceClassLoader, resourceLocation));
        });
    }
    protected static Map<String, List<String>> loadFactoriesResource(ClassLoader classLoader, String resourceLocation) {
        LinkedHashMap result = new LinkedHashMap();

        try {
            Enumeration urls = classLoader.getResources(resourceLocation);

            result.replaceAll(SpringFactoriesLoader::toDistinctUnmodifiableList);
            return Collections.unmodifiableMap(result);
        } catch (IOException var6) {
            throw new IllegalArgumentException("Unable to load factories from location [" + resourceLocation + "]", var6);
        }
    }

    private static Object toDistinctUnmodifiableList(Object o, Object o1) {
        return null;
    }
    @FunctionalInterface
    public interface ArgumentResolver {
        @Nullable
        <T> T resolve(Class<T> type);

        default <T> SpringFactoriesLoader.ArgumentResolver and(Class<T> type, T value) {
            return this.and(of(type, value));
        }

        default <T> SpringFactoriesLoader.ArgumentResolver andSupplied(Class<T> type, Supplier<T> valueSupplier) {
            return this.and(ofSupplied(type, valueSupplier));
        }

        default SpringFactoriesLoader.ArgumentResolver and(SpringFactoriesLoader.ArgumentResolver argumentResolver) {
            return from((type) -> {
                Object resolved = this.resolve(type);
                return resolved != null ? resolved : argumentResolver.resolve(type);
            });
        }

        static SpringFactoriesLoader.ArgumentResolver none() {
            return from((type) -> {
                return null;
            });
        }

        static <T> SpringFactoriesLoader.ArgumentResolver of(Class<T> type, T value) {
            return ofSupplied(type, () -> {
                return value;
            });
        }

        static <T> SpringFactoriesLoader.ArgumentResolver ofSupplied(Class<T> type, Supplier<T> valueSupplier) {
            return from((candidateType) -> {
                return candidateType.equals(type) ? valueSupplier.get() : null;
            });
        }

        static SpringFactoriesLoader.ArgumentResolver from(Function<Class<?>, Object> function) {
            return new SpringFactoriesLoader.ArgumentResolver() {
                public <T> T resolve(Class<T> type) {
                    return (T) function.apply(type);
                }
            };
        }
    }
    @FunctionalInterface
    public interface FailureHandler {
        void handleFailure(Class<?> factoryType, String factoryImplementationName, Throwable failure);

        static SpringFactoriesLoader.FailureHandler throwing() {
            return throwing(IllegalArgumentException::new);
        }

        static SpringFactoriesLoader.FailureHandler throwing(BiFunction<String, Throwable, ? extends RuntimeException> exceptionFactory) {
            return handleMessage((messageSupplier, failure) -> {
                throw (RuntimeException)exceptionFactory.apply((String)messageSupplier.get(), failure);
            });
        }


        static SpringFactoriesLoader.FailureHandler handleMessage(BiConsumer<Supplier<String>, Throwable> messageHandler) {
            return (factoryType, factoryImplementationName, failure) -> {
                Supplier<String> messageSupplier = () -> {
                    return "Unable to instantiate factory class [%s] for factory type [%s]".formatted(new Object[]{factoryImplementationName, factoryType.getName()});
                };
                messageHandler.accept(messageSupplier, failure);
            };
        }
    }
    @Nullable
    protected <T> T instantiateFactory(String implementationName, Class<T> type, @Nullable SpringFactoriesLoader.ArgumentResolver argumentResolver, SpringFactoriesLoader.FailureHandler failureHandler) {
        try {
            Class<?> factoryImplementationClass = ClassUtils.forName(implementationName, this.classLoader);
            Assert.isTrue(type.isAssignableFrom(factoryImplementationClass), () -> {
                return "Class [%s] is not assignable to factory type [%s]".formatted(new Object[]{implementationName, type.getName()});
            });
            return (T) SpringFactoriesLoader.loadFactories(factoryImplementationClass, classLoader);
        } catch (Throwable var7) {
            failureHandler.handleFailure(type, implementationName, var7);
            return null;
        }
    }

}
