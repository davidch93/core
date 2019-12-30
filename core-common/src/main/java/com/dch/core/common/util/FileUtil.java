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
     * Check whether file is available or not in path
     *
     * @param filePath the file path.
     * @return {@code boolean} true if file is available and vice versa.
     */
    public static boolean isAvailable(String filePath) {
        Assert.hasLength(filePath, "File path can't be empty");

        Path path = Paths.get(filePath);
        return Files.exists(path, LinkOption.NOFOLLOW_LINKS);
    }

    /**
     * Check whether file is read only or not.
     *
     * @param filePath the file path.
     * @return {@code boolean} true if file is read only and vice versa.
     */
    public static boolean isReadOnly(String filePath) {
        Assert.hasLength(filePath, "File path can't be empty");

        Path path = Paths.get(filePath);
        return !Files.isWritable(path);
    }

    /**
     * Copy file from sourcePath to destinationPath.
     *
     * @param sourcePath      the source path.
     * @param destinationPath the destination path.
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
     * Get path from File.
     *
     * @param file the {@link File}
     * @return the file path.
     */
    public static String getPath(File file) {
        Assert.notNull(file, "File can't be null");

        return file.toPath().toString();
    }

    /**
     * Get file from string path.
     *
     * @param filePath the file path.
     * @return the {@link File}
     */
    public static File pathToFile(String filePath) {
        Assert.hasLength(filePath, "File path can't be empty");

        Path path = Paths.get(filePath);
        return path.toFile();
    }

    /**
     * Get absolute file path from File.
     *
     * @param file the {@link File}
     * @return the absolute path.
     */
    public static String getAbsolutePath(File file) {
        Assert.notNull(file, "File can't be null");

        return file.getAbsolutePath();
    }

    /**
     * Get canonical file path from File.
     *
     * @param file the {@link File}
     * @return the canonical path.
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
     * Get file name from a file.
     *
     * @param file the {@link File}
     * @return the file name.
     */
    public static String getFileName(File file) {
        Assert.notNull(file, "File can't be null");

        return file.getName();
    }

    /**
     * Delete a file.
     *
     * @param filePath the file path.
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
