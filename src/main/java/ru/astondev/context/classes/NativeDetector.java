package ru.astondev.context.classes;

import ru.astondev.context.annotations.Nullable;

public abstract class NativeDetector {
    @Nullable
    private static final String imageCode = System.getProperty("org.graalvm.nativeimage.imagecode");
    private static final boolean inNativeImage;

    public NativeDetector() {
    }

    public static boolean inNativeImage() {
        return inNativeImage;
    }

    public static boolean inNativeImage(NativeDetector.Context... contexts) {
        NativeDetector.Context[] var1 = contexts;
        int var2 = contexts.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            NativeDetector.Context context = var1[var3];
            if (context.key.equals(imageCode)) {
                return true;
            }
        }

        return false;
    }

    static {
        inNativeImage = imageCode != null;
    }

    public static enum Context {
        BUILD("buildtime"),
        RUN("runtime");

        private final String key;

        private Context(final String key) {
            this.key = key;
        }

        public String toString() {
            return this.key;
        }
    }
}
