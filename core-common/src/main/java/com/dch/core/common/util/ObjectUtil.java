package com.dch.core.common.util;

import org.springframework.util.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;

/**
 * Classes that provides function to manipulate Object.
 *
 * @author David.Christianto
 * @version 2.0.0
 * @since 1.0.0
 */
public class ObjectUtil {

    /**
     * Convert file to array of bytes.
     *
     * @param file the {@link File}
     * @return the byte[] array of bytes.
     */
    public static byte[] convertFileToBytes(File file) {
        Assert.notNull(file, "File can't be null");

        // Convert file into array of bytes
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            byte[] bFile = new byte[(int) file.length()];
            fileInputStream.read(bFile);
            return bFile;
        } catch (IOException ex) {
            throw new RuntimeException("Input stream failed", ex);
        }
    }

    /**
     * Convert array of bytes to file.
     *
     * @param bFile       the byte file
     * @param destination the destination path of file.
     */
    public static void convertBytesToFile(byte[] bFile, String destination) {
        Assert.notNull(bFile, "Array of bytes can't be null");
        Assert.hasLength(destination, "Destination path can't be empty");

        try (FileOutputStream fileOutputStream = new FileOutputStream(destination)) {
            fileOutputStream.write(bFile);
        } catch (IOException ex) {
            throw new RuntimeException("Output stream failed", ex);
        }
    }

    /**
     * Convert array of bytes to file.
     *
     * @param bFile the byte file.
     * @param file  the {@link File new destination file}.
     */
    public static void convertBytesToFile(byte[] bFile, File file) {
        Assert.notNull(bFile, "Array of bytes can't be null");
        Assert.notNull(file, "File can't be null");

        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            fileOutputStream.write(bFile);
        } catch (IOException ex) {
            throw new RuntimeException("Output stream failed", ex);
        }
    }

    /**
     * Get field value from Object using field name.
     *
     * @param object    the object.
     * @param fieldName the field name.
     * @return the field.
     */
    public static Object getFieldValue(Object object, String fieldName) {
        try {
            Class<?> clazz = object.getClass();
            Field declaredField = clazz.getDeclaredField(fieldName);
            declaredField.setAccessible(true);
            return declaredField.get(object);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Error occurred while reflect object", e);
        }
    }
}
