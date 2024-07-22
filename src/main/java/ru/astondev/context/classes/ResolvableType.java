package ru.astondev.context.classes;

import ru.astondev.context.annotations.Nullable;
import ru.astondev.context.interfaces.ResolvableTypeProvider;
import ru.astondev.context.utils.ObjectUtils;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;
import java.util.StringJoiner;

public class ResolvableType implements Serializable {
    public static ResolvableType NONE;

    public static ResolvableType forClassWithGenerics(Class<?> clazz, ResolvableType... generics) {
        Assert.notNull(clazz, "Class must not be null");
        Assert.notNull(generics, "Generics array must not be null");
        TypeVariable<?>[] variables = clazz.getTypeParameters();
        Assert.isTrue(variables.length == generics.length, () -> {
            return "Mismatched number of generics specified for " + clazz.toGenericString();
        });
        Type[] arguments = new Type[generics.length];

        for(int i = 0; i < generics.length; ++i) {
            ResolvableType generic = generics[i];
            Type argument = generic != null ? generic.getType() : null;
            arguments[i] = (Type)(argument != null && !(argument instanceof TypeVariable) ? argument : variables[i]);
        }

        ParameterizedType syntheticType = new ResolvableType.SyntheticParameterizedType(clazz, arguments);
        return forType(syntheticType, (ResolvableType.VariableResolver)(new ResolvableType.TypeVariablesVariableResolver(variables, generics)));
    }

    public static ResolvableType forInstance(@Nullable Object instance) {
        if (instance instanceof ResolvableTypeProvider) {
            ResolvableTypeProvider resolvableTypeProvider = (ResolvableTypeProvider)instance;
            ResolvableType type = resolvableTypeProvider.getResolvableType();
            if (type != null) {
                return type;
            }
        }

        return instance != null ? forClass(instance.getClass()) : NONE;
    }

    private static ResolvableType forClass(Class<?> aClass) {
        return null;
    }

    public Type getType() {
        return Type.class.getClass();
    }

    static ResolvableType forType(@Nullable Type type, @Nullable ResolvableType.VariableResolver variableResolver) {
        return forType(type, (SerializableTypeWrapper.TypeProvider)null, variableResolver);
    }

    private static ResolvableType forType(Type type, SerializableTypeWrapper.TypeProvider typeProvider, VariableResolver variableResolver) {
        return null;
    }

//    public static ResolvableType forClass(Class<?> baseType) {
//        Assert.notNull(baseType, "Base type must not be null");
//        ResolvableType asType = forType((Type)implementationClass).as(baseType);
//        return asType == NONE ? forType((Type)baseType) : asType;
//    }
    interface VariableResolver extends Serializable {
        Object getSource();

        @Nullable
        ResolvableType resolveVariable(TypeVariable<?> variable);
    }

    private static final class SyntheticParameterizedType implements ParameterizedType, Serializable {
        private final Type rawType;
        private final Type[] typeArguments;

        public SyntheticParameterizedType(Type rawType, Type[] typeArguments) {
            this.rawType = rawType;
            this.typeArguments = typeArguments;
        }

        public String getTypeName() {
            String typeName = this.rawType.getTypeName();
            if (this.typeArguments.length <= 0) {
                return typeName;
            } else {
                StringJoiner stringJoiner = new StringJoiner(", ", "<", ">");
                Type[] var3 = this.typeArguments;
                int var4 = var3.length;

                for(int var5 = 0; var5 < var4; ++var5) {
                    Type argument = var3[var5];
                    stringJoiner.add(argument.getTypeName());
                }

                return typeName + stringJoiner;
            }
        }

        @Nullable
        public Type getOwnerType() {
            return null;
        }

        public Type getRawType() {
            return this.rawType;
        }

        public Type[] getActualTypeArguments() {
            return this.typeArguments;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            } else if (!(other instanceof ParameterizedType)) {
                return false;
            } else {
                ParameterizedType otherType = (ParameterizedType)other;
                return otherType.getOwnerType() == null && this.rawType.equals(otherType.getRawType()) && Arrays.equals(this.typeArguments, otherType.getActualTypeArguments());
            }
        }

        public int hashCode() {
            return this.rawType.hashCode() * 31 + Arrays.hashCode(this.typeArguments);
        }

        public String toString() {
            return this.getTypeName();
        }
    }

    private static class TypeVariablesVariableResolver implements ResolvableType.VariableResolver {
        private final TypeVariable<?>[] variables;
        private final ResolvableType[] generics;

        public TypeVariablesVariableResolver(TypeVariable<?>[] variables, ResolvableType[] generics) {
            this.variables = variables;
            this.generics = generics;
        }

        @Nullable
        public ResolvableType resolveVariable(TypeVariable<?> variable) {
            TypeVariable<?> variableToCompare = (TypeVariable)SerializableTypeWrapper.unwrap(variable);

            for(int i = 0; i < this.variables.length; ++i) {
                TypeVariable<?> resolvedVariable = (TypeVariable)SerializableTypeWrapper.unwrap(this.variables[i]);
                if (ObjectUtils.nullSafeEquals(resolvedVariable, variableToCompare)) {
                    return this.generics[i];
                }
            }

            return null;
        }

        public Object getSource() {
            return this.generics;
        }
    }

}
