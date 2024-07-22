package ru.astondev.context.utils;

import ru.astondev.context.classes.Assert;
import ru.astondev.context.annotations.Nullable;

import java.lang.reflect.Array;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.StringJoiner;

public abstract class ClassUtils {
    public static final String ARRAY_SUFFIX = "[]";
    private static final String INTERNAL_ARRAY_PREFIX = "[";
    private static final String NON_PRIMITIVE_ARRAY_PREFIX = "[L";
    private static final Class<?>[] EMPTY_CLASS_ARRAY = new Class[0];
    private static final char PACKAGE_SEPARATOR = '.';
    private static final char PATH_SEPARATOR = '/';
    private static final char NESTED_CLASS_SEPARATOR = '$';
    public static final String CGLIB_CLASS_SEPARATOR = "$$";
    public static final String CLASS_FILE_SUFFIX = ".class";
    private static final Map<Class<?>, Class<?>> primitiveWrapperTypeMap = new IdentityHashMap(9);
    private static final Map<Class<?>, Class<?>> primitiveTypeToWrapperMap = new IdentityHashMap(9);
    private static final Map<String, Class<?>> primitiveTypeNameMap = new HashMap(32);
    private static final Map<String, Class<?>> commonClassCache = new HashMap(64);
    //private static final Set<Class<?>> javaLanguageInterfaces;
    //private static final Map<Method, Method> interfaceMethodCache = new ConcurrentReferenceHashMap(256);



    @Nullable
    public static String getDescriptiveType(@Nullable Object value) {
        if (value == null) {
            return null;
        } else {
            Class<?> clazz = value.getClass();
            if (!Proxy.isProxyClass(clazz)) {
                return clazz.getTypeName();
            } else {
                String prefix = clazz.getName() + " implementing ";
                StringJoiner result = new StringJoiner(",", prefix, "");
                Class[] var4 = clazz.getInterfaces();
                int var5 = var4.length;

                for(int var6 = 0; var6 < var5; ++var6) {
                    Class<?> ifc = var4[var6];
                    result.add(ifc.getName());
                }

                return result.toString();
            }
        }
    }
    public static String getQualifiedName(Class<?> clazz) {
        Assert.notNull(clazz, "Class must not be null");
        return clazz.getTypeName();
    }
    public static boolean isPresent(String className, @Nullable ClassLoader classLoader) {
        try {
            forName(className, classLoader);
            return true;
        } catch (IllegalAccessError var3) {
            throw new IllegalStateException("Readability mismatch in inheritance hierarchy of class [" + className + "]: " + var3.getMessage(), var3);
        } catch (Throwable var4) {
            return false;
        }
    }
    public static Class<?> forName(String name, @Nullable ClassLoader classLoader) throws ClassNotFoundException, LinkageError {
        Assert.notNull(name, "Name must not be null");
        Class<?> clazz = resolvePrimitiveClassName(name);
        if (clazz == null) {
            clazz = (Class)commonClassCache.get(name);
        }

        if (clazz != null) {
            return clazz;
        } else {
            Class elementClass;
            String elementName;
            if (name.endsWith("[]")) {
                elementName = name.substring(0, name.length() - "[]".length());
                elementClass = forName(elementName, classLoader);
                return Array.newInstance(elementClass, 0).getClass();
            } else if (name.startsWith("[L") && name.endsWith(";")) {
                elementName = name.substring("[L".length(), name.length() - 1);
                elementClass = forName(elementName, classLoader);
                return Array.newInstance(elementClass, 0).getClass();
            } else if (name.startsWith("[")) {
                elementName = name.substring("[".length());
                elementClass = forName(elementName, classLoader);
                return Array.newInstance(elementClass, 0).getClass();
            } else {
                ClassLoader clToUse = classLoader;
                if (classLoader == null) {
                    clToUse = getDefaultClassLoader();
                }

                try {
                    return Class.forName(name, false, clToUse);
                } catch (ClassNotFoundException var9) {
                    int lastDotIndex = name.lastIndexOf(46);
                    if (lastDotIndex != -1) {
                        String var10000 = name.substring(0, lastDotIndex);
                        String nestedClassName = var10000 + "$" + name.substring(lastDotIndex + 1);

                        try {
                            return Class.forName(nestedClassName, false, clToUse);
                        } catch (ClassNotFoundException var8) {
                        }
                    }

                    throw var9;
                }
            }
        }
    }
    @Nullable
    public static Class<?> resolvePrimitiveClassName(@Nullable String name) {
        Class<?> result = null;
        if (name != null && name.length() <= 7) {
            result = (Class)primitiveTypeNameMap.get(name);
        }

        return result;
    }
    @Nullable
    public static ClassLoader getDefaultClassLoader() {
        ClassLoader cl = null;

        try {
            cl = Thread.currentThread().getContextClassLoader();
        } catch (Throwable var3) {
        }

        if (cl == null) {
            cl = ClassUtils.class.getClassLoader();
            if (cl == null) {
                try {
                    cl = ClassLoader.getSystemClassLoader();
                } catch (Throwable var2) {
                }
            }
        }

        return cl;
    }
}
