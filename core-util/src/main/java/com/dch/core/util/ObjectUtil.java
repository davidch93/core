package com.dch.core.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Field;

/**
 * Classes that provides function to manipulate Object.
 *
 * @author David.Christianto
 * @version 1.0.0
 * @updated Apr 22, 2017
 * @since 1.0.0-SNAPSHOT
 */
public class ObjectUtil {

    /**
     * Method used to convert file to array of bytes.
     *
     * @param file {@link File}
     * @return byte[] array of bytes.
     * @throws Exception File can't be null.
     */
    public static byte[] convertFileToBytes(File file) throws Exception {
        if (file == null)
            throw new Exception("File can't be null");

        FileInputStream fileInputStream = null;
        byte[] bFile = new byte[(int) file.length()];

        // Convert file into array of bytes
        fileInputStream = new FileInputStream(file);
        fileInputStream.read(bFile);
        fileInputStream.close();

        return bFile;
    }

    /**
     * Method used to convert array of bytes to file.
     *
     * @param bFile       byte file
     * @param destination {@link String} Destination path of file.
     * @throws Exception Array of bytes can't be null.
     */
    public static void convertBytesToFile(byte[] bFile, String destination) throws Exception {
        if (bFile == null)
            throw new Exception("Array of bytes can't be null");

        if (TextUtil.isEmpty(destination))
            throw new Exception("Destination path can't be empty");

        FileOutputStream fileOuputStream = new FileOutputStream(destination);
        fileOuputStream.write(bFile);
        fileOuputStream.close();
    }

    /**
     * Method used to convert array of bytes to file.
     *
     * @param bFile byte file
     * @param file  {@link File} new destination file.
     * @throws Exception Array of bytes and file can't be null.
     */
    public static void convertBytesToFile(byte[] bFile, File file) throws Exception {
        if (bFile == null)
            throw new Exception("Array of bytes can't be null");

        if (file == null)
            throw new Exception("File can't be null");

        FileOutputStream fileOuputStream = new FileOutputStream(file);
        fileOuputStream.write(bFile);
        fileOuputStream.close();
    }

    /**
     * Method used to get field value from Object using field name.
     *
     * @param object    {@link Object} Base object.
     * @param fieldName {@link String} Field name.
     * @return {@link Object} Field.
     * @throws Exception if there are error during reflect object.
     */
    public static Object getFieldValue(Object object, String fieldName) throws Exception {
        Class<?> clazz = object.getClass();
        Field declaredField = clazz.getDeclaredField(fieldName);
        declaredField.setAccessible(true);
        return declaredField.get(object);
    }
}
