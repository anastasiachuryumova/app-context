package ru.astondev.context.classes;

import ru.astondev.context.annotations.Nullable;

import java.io.Serializable;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;

public class SerializableTypeWrapper {
    private static final Class<?>[] SUPPORTED_SERIALIZABLE_TYPES = new Class[]{GenericArrayType.class, ParameterizedType.class, TypeVariable.class, WildcardType.class};
    //static final ConcurrentReferenceHashMap<Type, Type> cache = new ConcurrentReferenceHashMap(256);

    private SerializableTypeWrapper() {
    }

    public static <T extends Type> T unwrap(T type) {
        Type unwrapped = null;
        if (type instanceof SerializableTypeWrapper.SerializableTypeProxy) {
            SerializableTypeWrapper.SerializableTypeProxy proxy = (SerializableTypeWrapper.SerializableTypeProxy) type;
            unwrapped = proxy.getTypeProvider().getType();
        }

        return unwrapped != null ? (T) unwrapped : type;
    }

    interface SerializableTypeProxy {
        SerializableTypeWrapper.TypeProvider getTypeProvider();

    }
    interface TypeProvider extends Serializable {
        @Nullable
        Type getType();

        @Nullable
        default Object getSource() {
            return null;
        }
    }
}
