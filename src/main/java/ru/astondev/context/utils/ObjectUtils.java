package ru.astondev.context.utils;

import ru.astondev.context.annotations.Nullable;

import java.util.Arrays;

public class ObjectUtils {
    public static boolean nullSafeEquals(@Nullable Object o1, @Nullable Object o2) {
        if (o1 == o2) {
            return true;
        } else if (o1 != null && o2 != null) {
            if (o1.equals(o2)) {
                return true;
            } else {
                return o1.getClass().isArray() && o2.getClass().isArray() ? arrayEquals(o1, o2) : false;
            }
        } else {
            return false;
        }
    }

    private static boolean arrayEquals(Object o1, Object o2) {
        if (o1 instanceof Object[]) {
            Object[] objects1 = (Object[])o1;
            if (o2 instanceof Object[]) {
                Object[] objects2 = (Object[])o2;
                return Arrays.equals(objects1, objects2);
            }
        }

        if (o1 instanceof boolean[]) {
            boolean[] booleans1 = (boolean[])o1;
            if (o2 instanceof boolean[]) {
                boolean[] booleans2 = (boolean[])o2;
                return Arrays.equals(booleans1, booleans2);
            }
        }

        if (o1 instanceof byte[]) {
            byte[] bytes1 = (byte[])o1;
            if (o2 instanceof byte[]) {
                byte[] bytes2 = (byte[])o2;
                return Arrays.equals(bytes1, bytes2);
            }
        }

        if (o1 instanceof char[]) {
            char[] chars1 = (char[])o1;
            if (o2 instanceof char[]) {
                char[] chars2 = (char[])o2;
                return Arrays.equals(chars1, chars2);
            }
        }

        if (o1 instanceof double[]) {
            double[] doubles1 = (double[])o1;
            if (o2 instanceof double[]) {
                double[] doubles2 = (double[])o2;
                return Arrays.equals(doubles1, doubles2);
            }
        }

        if (o1 instanceof float[]) {
            float[] floats1 = (float[])o1;
            if (o2 instanceof float[]) {
                float[] floats2 = (float[])o2;
                return Arrays.equals(floats1, floats2);
            }
        }

        if (o1 instanceof int[]) {
            int[] ints1 = (int[])o1;
            if (o2 instanceof int[]) {
                int[] ints2 = (int[])o2;
                return Arrays.equals(ints1, ints2);
            }
        }

        if (o1 instanceof long[]) {
            long[] longs1 = (long[])o1;
            if (o2 instanceof long[]) {
                long[] longs2 = (long[])o2;
                return Arrays.equals(longs1, longs2);
            }
        }

        if (o1 instanceof short[]) {
            short[] shorts1 = (short[])o1;
            if (o2 instanceof short[]) {
                short[] shorts2 = (short[])o2;
                return Arrays.equals(shorts1, shorts2);
            }
        }

        return false;
    }
}
