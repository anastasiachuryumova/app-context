package ru.astondev.context.utils;

import ru.astondev.context.classes.Assert;
import ru.astondev.context.annotations.Nullable;

import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.file.Files;

public abstract class FileCopyUtils {
    public static final int BUFFER_SIZE = 8192;

    public FileCopyUtils() {
    }

    public static int copy(File in, File out) throws IOException {
        Assert.notNull(in, "No input File specified");
        Assert.notNull(out, "No output File specified");
        return copy(Files.newInputStream(in.toPath()), Files.newOutputStream(out.toPath()));
    }

    public static void copy(byte[] in, File out) throws IOException {
        Assert.notNull(in, "No input byte array specified");
        Assert.notNull(out, "No output File specified");
        copy((InputStream)(new ByteArrayInputStream(in)), (OutputStream)Files.newOutputStream(out.toPath()));
    }

    public static byte[] copyToByteArray(File in) throws IOException {
        Assert.notNull(in, "No input File specified");
        return copyToByteArray(Files.newInputStream(in.toPath()));
    }

    public static int copy(InputStream in, OutputStream out) throws IOException {
        Assert.notNull(in, "No InputStream specified");
        Assert.notNull(out, "No OutputStream specified");
        InputStream var2 = in;

        int var5;
        try {
            OutputStream var3 = out;

            try {
                int count = (int)in.transferTo(out);
                out.flush();
                var5 = count;
            } catch (Throwable var8) {
                if (out != null) {
                    try {
                        var3.close();
                    } catch (Throwable var7) {
                        var8.addSuppressed(var7);
                    }
                }

                throw var8;
            }

            if (out != null) {
                out.close();
            }
        } catch (Throwable var9) {
            if (in != null) {
                try {
                    var2.close();
                } catch (Throwable var6) {
                    var9.addSuppressed(var6);
                }
            }

            throw var9;
        }

        if (in != null) {
            in.close();
        }

        return var5;
    }

    public static void copy(byte[] in, OutputStream out) throws IOException {
        Assert.notNull(in, "No input byte array specified");
        Assert.notNull(out, "No OutputStream specified");

        try {
            out.write(in);
        } finally {
            close(out);
        }

    }

    public static byte[] copyToByteArray(@Nullable InputStream in) throws IOException {
        if (in == null) {
            return new byte[0];
        } else {
            InputStream var1 = in;

            byte[] var2;
            try {
                var2 = in.readAllBytes();
            } catch (Throwable var5) {
                if (in != null) {
                    try {
                        var1.close();
                    } catch (Throwable var4) {
                        var5.addSuppressed(var4);
                    }
                }

                throw var5;
            }

            if (in != null) {
                in.close();
            }

            return var2;
        }
    }

    public static int copy(Reader in, Writer out) throws IOException {
        Assert.notNull(in, "No Reader specified");
        Assert.notNull(out, "No Writer specified");

        try {
            int charCount = 0;

            int charsRead;
            for(char[] buffer = new char[8192]; (charsRead = in.read(buffer)) != -1; charCount += charsRead) {
                out.write(buffer, 0, charsRead);
            }

            out.flush();
            int var5 = charCount;
            return var5;
        } finally {
            close(in);
            close(out);
        }
    }

    public static void copy(String in, Writer out) throws IOException {
        Assert.notNull(in, "No input String specified");
        Assert.notNull(out, "No Writer specified");

        try {
            out.write(in);
        } finally {
            close(out);
        }

    }

    public static String copyToString(@Nullable Reader in) throws IOException {
        if (in == null) {
            return "";
        } else {
            StringWriter out = new StringWriter(8192);
            copy((Reader)in, (Writer)out);
            return out.toString();
        }
    }

    private static void close(Closeable closeable) {
        try {
            closeable.close();
        } catch (IOException var2) {
        }

    }
}
