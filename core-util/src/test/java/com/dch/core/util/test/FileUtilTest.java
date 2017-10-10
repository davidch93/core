/**
 * 
 */
package com.dch.core.util.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.File;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.dch.core.util.FileUtil;

/**
 * Test class used to test all methods in the {@link FileUtil} class.
 * 
 * @author David.Christianto
 * @version 1.0.0-SNAPSHOT
 * @since 1.0.0-SNAPSHOT
 * @updated Apr 22, 2017
 */
public class FileUtilTest {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	/**
	 * Test method for
	 * {@link com.dch.core.util.FileUtil#isAvailable(java.lang.String)}.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testIsAvaliable() throws Exception {
		boolean actual = FileUtil.isAvailable("src/test/resources/test.txt");
		assertThat(actual, is(true));
	}

	/**
	 * Test method for
	 * {@link com.dch.core.util.FileUtil#isAvaliable(java.lang.String)} with
	 * empty file path.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testIsAvaliableWithEmptyPath() throws Exception {
		expectedException.expect(Exception.class);
		expectedException.expectMessage("File path can't be empty");
		FileUtil.isAvailable(null);
	}

	/**
	 * Test method for
	 * {@link com.dch.core.util.FileUtil#isReadOnly(java.lang.String)}.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testIsReadOnly() throws Exception {
		boolean actual = FileUtil.isReadOnly("src/test/resources/test.txt");
		assertThat(actual, is(false));
	}

	/**
	 * Test method for
	 * {@link com.dch.core.util.FileUtil#isReadOnly(java.lang.String)} with empty
	 * file path.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testIsReadOnlyWithEmptyPath() throws Exception {
		expectedException.expect(Exception.class);
		expectedException.expectMessage("File path can't be empty");
		FileUtil.isReadOnly(null);
	}

	/**
	 * Test method for
	 * {@link com.dch.core.util.FileUtil#copyFile(java.lang.String, java.lang.String)}.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCopyFile() throws Exception {
		FileUtil.copyFile("src/test/resources/test.txt", "src/test/resources/test(copy).txt");

		boolean actual = FileUtil.isAvailable("src/test/resources/test(copy).txt");
		assertThat(actual, is(true));
	}

	/**
	 * Test method for
	 * {@link com.dch.core.util.FileUtil#copyFile(java.lang.String, java.lang.String)}
	 * with empty source path.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCopyFileWithEmptySource() throws Exception {
		expectedException.expect(Exception.class);
		expectedException.expectMessage("Source path can't be empty");
		FileUtil.copyFile(null, "src/test/resources/test(copy).txt");
	}

	/**
	 * Test method for
	 * {@link com.dch.core.util.FileUtil#copyFile(java.lang.String, java.lang.String)}
	 * with empty destination path.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCopyFileWithEmptyDestination() throws Exception {
		expectedException.expect(Exception.class);
		expectedException.expectMessage("Destination path can't be empty");
		FileUtil.copyFile("src/test/resources/test.txt", null);
	}

	/**
	 * Test method for {@link com.dch.core.util.FileUtil#getPath(java.io.File)}.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetPath() throws Exception {
		File file = new File("src/test/resources/test.txt");
		String actual = FileUtil.getPath(file);
		assertThat(actual, is(equalTo(file.toPath().toString())));
	}

	/**
	 * Test method for {@link com.dch.core.util.FileUtil#getPath(java.io.File)}
	 * with null path.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetPathWithNullPath() throws Exception {
		expectedException.expect(Exception.class);
		expectedException.expectMessage("File can't be null");
		FileUtil.getPath(null);
	}

	/**
	 * Test method for
	 * {@link com.dch.core.util.FileUtil#pathToFile(java.lang.String)}.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testPathToFile() throws Exception {
		File actualFile = FileUtil.pathToFile("src/test/resources/test.txt");
		File expectedFile = new File("src/test/resources/test.txt");
		assertThat(actualFile, is(equalTo(expectedFile)));
	}

	/**
	 * Test method for
	 * {@link com.dch.core.util.FileUtil#pathToFile(java.lang.String)} with empty
	 * file path.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testPathToFileWithEmptyPath() throws Exception {
		expectedException.expect(Exception.class);
		expectedException.expectMessage("File path can't be empty");
		FileUtil.pathToFile(null);
	}

	/**
	 * Test method for
	 * {@link com.dch.core.util.FileUtil#getAbsolutePath(java.io.File)}.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetAbsolutePath() throws Exception {
		File file = new File("src/test/resources/test.txt");
		String actual = FileUtil.getAbsolutePath(file);
		assertThat(actual, is(equalTo(file.getAbsolutePath())));
	}

	/**
	 * Test method for
	 * {@link com.dch.core.util.FileUtil#getAbsolutePath(java.io.File)} with null
	 * file.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetAbsolutePathWithNullFile() throws Exception {
		expectedException.expect(Exception.class);
		expectedException.expectMessage("File can't be null");
		FileUtil.getAbsolutePath(null);
	}

	/**
	 * Test method for
	 * {@link com.dch.core.util.FileUtil#getCanonicalPath(java.io.File)}.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetCanonicalPath() throws Exception {
		File file = new File("src/test/resources/test.txt");
		String actual = FileUtil.getCanonicalPath(file);
		assertThat(actual, is(equalTo(file.getCanonicalPath())));
	}

	/**
	 * Test method for
	 * {@link com.dch.core.util.FileUtil#getCanonicalPath(java.io.File)} with
	 * null file.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetCanonicalPathWithNullFile() throws Exception {
		expectedException.expect(Exception.class);
		expectedException.expectMessage("File can't be null");
		FileUtil.getCanonicalPath(null);
	}

	/**
	 * Test method for
	 * {@link com.dch.core.util.FileUtil#getFileName(java.io.File)}.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetFileName() throws Exception {
		File file = new File("src/test/resources/test.txt");
		String actual = FileUtil.getFileName(file);
		assertThat(actual, is(equalTo(file.getName())));
	}

	/**
	 * Test method for
	 * {@link com.dch.core.util.FileUtil#getFileName(java.io.File)} with null
	 * file.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetFileNameWithNullFile() throws Exception {
		expectedException.expect(Exception.class);
		expectedException.expectMessage("File can't be null");
		FileUtil.getFileName(null);
	}

	/**
	 * Test method for
	 * {@link com.dch.core.util.FileUtil#deleteFile(java.lang.String)}.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDeleteFile() throws Exception {
		FileUtil.copyFile("src/test/resources/test.txt", "src/test/resources/test(copy).txt");
		FileUtil.deleteFile("src/test/resources/test(copy).txt");

		boolean actual = FileUtil.isAvailable("src/test/resources/test(copy).txt");
		assertThat(actual, is(false));
	}

	/**
	 * Test method for
	 * {@link com.dch.core.util.FileUtil#deleteFile(java.lang.String)} with empty
	 * file path.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDeleteFileWithEmptyPath() throws Exception {
		expectedException.expect(Exception.class);
		expectedException.expectMessage("File path can't be empty");
		FileUtil.deleteFile(null);
	}

}
