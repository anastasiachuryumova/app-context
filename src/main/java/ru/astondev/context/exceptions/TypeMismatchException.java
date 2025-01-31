package ru.astondev.context.exceptions;

import ru.astondev.context.classes.Assert;
import ru.astondev.context.utils.ClassUtils;
import ru.astondev.context.annotations.Nullable;

import java.beans.PropertyChangeEvent;

public class TypeMismatchException extends PropertyAccessException {
    public static final String ERROR_CODE = "typeMismatch";
    @Nullable
    private String propertyName;
    @Nullable
    private final transient Object value;
    @Nullable
    private final Class<?> requiredType;

    public TypeMismatchException(PropertyChangeEvent propertyChangeEvent, Class<?> requiredType) {
        this((PropertyChangeEvent)propertyChangeEvent, requiredType, (Throwable)null);
    }

    public TypeMismatchException(PropertyChangeEvent propertyChangeEvent, @Nullable Class<?> requiredType, @Nullable Throwable cause) {
        super(propertyChangeEvent, "Failed to convert property value of type '" + ClassUtils.getDescriptiveType(propertyChangeEvent.getNewValue()) + "'" + (requiredType != null ? " to required type '" + ClassUtils.getQualifiedName(requiredType) + "'" : "") + (propertyChangeEvent.getPropertyName() != null ? " for property '" + propertyChangeEvent.getPropertyName() + "'" : "") + (cause != null ? "; " + cause.getMessage() : ""), cause);
        this.propertyName = propertyChangeEvent.getPropertyName();
        this.value = propertyChangeEvent.getNewValue();
        this.requiredType = requiredType;
    }

    public TypeMismatchException(@Nullable Object value, @Nullable Class<?> requiredType) {
        this((Object)value, requiredType, (Throwable)null);
    }

    public TypeMismatchException(@Nullable Object value, @Nullable Class<?> requiredType, @Nullable Throwable cause) {
        super("Failed to convert value of type '" + ClassUtils.getDescriptiveType(value) + "'" + (requiredType != null ? " to required type '" + ClassUtils.getQualifiedName(requiredType) + "'" : "") + (cause != null ? "; " + cause.getMessage() : ""), cause);
        this.value = value;
        this.requiredType = requiredType;
    }

    public void initPropertyName(String propertyName) {
        Assert.state(this.propertyName == null, "Property name already initialized");
        this.propertyName = propertyName;
    }

    @Nullable
    public String getPropertyName() {
        return this.propertyName;
    }

    @Nullable
    public Object getValue() {
        return this.value;
    }

    @Nullable
    public Class<?> getRequiredType() {
        return this.requiredType;
    }

    public String getErrorCode() {
        return "typeMismatch";
    }
}
