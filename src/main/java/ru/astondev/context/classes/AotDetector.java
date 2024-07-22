package ru.astondev.context.classes;

import ru.astondev.context.classes.NativeDetector.Context;

public abstract class AotDetector {
    public static final String AOT_ENABLED = "spring.aot.enabled";
    private static final boolean inNativeImage;

    public AotDetector() {
    }

    public static boolean useGeneratedArtifacts() {
        return inNativeImage || SpringProperties.getFlag("spring.aot.enabled");
    }

    static {
        inNativeImage = NativeDetector.inNativeImage(new Context[]{Context.RUN, Context.BUILD});
    }
}
