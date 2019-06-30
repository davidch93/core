package com.dch.core.common.util;

import org.springframework.util.Assert;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

/**
 * Classes that provides function to manipulate File I/O.
 *
 * @author David.Christianto
 * @version 2.0.0
 * @since 1.0.0
 */
public class FileUtil {

    /**
     * Method used to check file is available or not in path
     *
     * @param filePath {@code String} file path.
     * @return {@code boolean} true if file is available and vice versa.
     */
    public static boolean isAvailable(String filePath) {
        Assert.hasLength(filePath, "File path can't be empty");

        Path path = Paths.get(filePath);
        return Files.exists(path, LinkOption.NOFOLLOW_LINKS);
    }

    /**
     * Method used to check file is read only or not.
     *
     * @param filePath {@code String} file path.
     * @return {@code boolean} true if file is read only and vice versa.
     */
    public static boolean isReadOnly(String filePath) {
        Assert.hasLength(filePath, "File path can't be empty");

        Path path = Paths.get(filePath);
        return !Files.isWritable(path);
    }

    /**
     * Method used to copy file from sourcePath to destinationPath.
     *
     * @param sourcePath      {@link String} source path.
     * @param destinationPath {@link String} destination path.
     */
    public static void copyFile(String sourcePath, String destinationPath) {
        Assert.hasLength(sourcePath, "Source path can't be empty");
        Assert.hasLength(destinationPath, "Destination path can't be empty");

        try {
            Path pathSource = Paths.get(sourcePath);
            Path pathDestination = Paths.get(destinationPath);
            Files.copy(pathSource, pathDestination, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("I/O error occurred", e);
        }
    }

    /**
     * Method used to get path from File.
     *
     * @param file {@link File}
     * @return File path.
     */
    public static String getPath(File file) {
        Assert.notNull(file, "File can't be null");

        return file.toPath().toString();
    }

    /**
     * Method used to get file from string path.
     *
     * @param filePath {@code String} file path.
     * @return {@link File}
     */
    public static File pathToFile(String filePath) {
        Assert.hasLength(filePath, "File path can't be empty");

        Path path = Paths.get(filePath);
        return path.toFile();
    }

    /**
     * Method used to get absolute file path from File.
     *
     * @param file {@link File}
     * @return {@code String} absolute path.
     */
    public static String getAbsolutePath(File file) {
        Assert.notNull(file, "File can't be null");

        return file.getAbsolutePath();
    }

    /**
     * Method used to get canonical file path from File.
     *
     * @param file {@link File}
     * @return {@code String} canonical path.
     */
    public static String getCanonicalPath(File file) {
        Assert.notNull(file, "File can't be null");

        try {
            return file.getCanonicalPath();
        } catch (IOException e) {
            throw new RuntimeException("I/O error occurred", e);
        }
    }

    /**
     * Method used to get file name from a file.
     *
     * @param file {@link File}
     * @return {@code String} file name.
     */
    public static String getFileName(File file) {
        Assert.notNull(file, "File can't be null");

        return file.getName();
    }

    /**
     * Method used to delete a file.
     *
     * @param filePath {@code String} file path.
     */
    public static void deleteFile(String filePath) {
        Assert.hasLength(filePath, "File path can't be empty");

        try {
            Files.deleteIfExists(Paths.get(filePath));
        } catch (IOException e) {
            throw new RuntimeException("I/O error occurred", e);
        }
    }
}
