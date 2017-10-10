package com.dch.core.util;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * Classes that provides function to manipulate File I/O.
 * 
 * @author David.Christianto
 * @version 1.0.0
 * @since 1.0.0-SNAPSHOT
 * @updated Apr 22, 2017
 */
public class FileUtil {

	/**
	 * Method used to check file is available or not in path
	 * 
	 * @param path
	 *            {@link String} file path.
	 * @return {@link boolean} true if file is available and vice versa.
	 * @throws Exception
	 *             Path can't be empty.
	 */
	public static boolean isAvailable(String filePath) throws Exception {
		if (TextUtil.isEmpty(filePath))
			throw new Exception("File path can't be empty");

		Path path = Paths.get(filePath);
		return Files.exists(path, new LinkOption[] { LinkOption.NOFOLLOW_LINKS });
	}

	/**
	 * Method used to check file is read only or not.
	 * 
	 * @param path
	 *            {@link String} file path.
	 * @return {@link boolean} true if file is read only and vice versa.
	 * @throws Exception
	 *             File path can't be empty.
	 */
	public static boolean isReadOnly(String filePath) throws Exception {
		if (TextUtil.isEmpty(filePath))
			throw new Exception("File path can't be empty");

		Path path = Paths.get(filePath);
		return !Files.isWritable(path);
	}

	/**
	 * Method used to copy file from sourcePath to destinationPath.
	 * 
	 * @param sourcePath
	 *            {@link String} source path.
	 * @param destinationPath
	 *            {@link String} destination path.
	 * @throws Exception
	 */
	public static void copyFile(String sourcePath, String destinationPath) throws Exception {
		if (TextUtil.isEmpty(sourcePath))
			throw new Exception("Source path can't be empty");

		if (TextUtil.isEmpty(destinationPath))
			throw new Exception("Destination path can't be empty");

		Path pathSource = Paths.get(sourcePath);
		Path pathDestination = Paths.get(destinationPath);
		Files.copy(pathSource, pathDestination, StandardCopyOption.REPLACE_EXISTING);
	}

	/**
	 * Method used to get path from File.
	 * 
	 * @param file
	 *            {@link File}
	 * @return {@link string} path of file.
	 * @throws Exception
	 *             File is null.
	 */
	public static String getPath(File file) throws Exception {
		if (file == null)
			throw new Exception("File can't be null");

		return file.toPath().toString();
	}

	/**
	 * Method used to get file from string path.
	 * 
	 * @param filePath
	 *            {@link String}
	 * @return {@link File}
	 * @throws Exception
	 *             File path can't be empty.
	 */
	public static File pathToFile(String filePath) throws Exception {
		if (TextUtil.isEmpty(filePath))
			throw new Exception("File path can't be empty");

		Path path = Paths.get(filePath);
		return path.toFile();
	}

	/**
	 * Method used to get absolute file path from File.
	 * 
	 * @param file
	 *            {@link File}
	 * @return {@link String} absolute path.
	 * @throws Exception
	 *             File can't be null.
	 */
	public static String getAbsolutePath(File file) throws Exception {
		if (file == null)
			throw new Exception("File can't be null");

		return file.getAbsolutePath();
	}

	/**
	 * Method used to get canonical file path from File.
	 * 
	 * @param file
	 *            {@link File}
	 * @return {@link String} canonical path.
	 * @throws Exception
	 *             File can't be null.
	 */
	public static String getCanonicalPath(File file) throws Exception {
		if (file == null)
			throw new Exception("File can't be null");

		return file.getCanonicalPath();

	}

	/**
	 * Method used to get file name from a file.
	 * 
	 * @param file
	 *            {@link File}
	 * @return {@link String} file name.
	 * @throws Exception
	 *             File can't be null
	 */
	public static String getFileName(File file) throws Exception {
		if (file == null)
			throw new Exception("File can't be null");

		return file.getName();
	}

	/**
	 * Method used to delete a file.
	 * 
	 * @param filepath
	 *            {@link String} file path.
	 * @throws Exception
	 *             File path can't be empty
	 */
	public static void deleteFile(String filePath) throws Exception {
		if (TextUtil.isEmpty(filePath))
			throw new Exception("File path can't be empty");

		Path path = Paths.get(filePath);
		Files.deleteIfExists(path);
	}
}
