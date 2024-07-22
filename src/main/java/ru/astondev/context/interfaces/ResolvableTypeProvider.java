package ru.astondev.context.interfaces;

import ru.astondev.context.classes.ResolvableType;
import ru.astondev.context.annotations.Nullable;

public interface ResolvableTypeProvider {
    @Nullable
    ResolvableType getResolvableType();
}
