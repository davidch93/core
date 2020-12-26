package com.dch.core.common.util;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test class used to test all methods in the {@link FileUtil} class.
 *
 * @author David.Christianto
 * @version 2.0.0
 * @since 1.0.0
 */
public class FileUtilTest {

    /**
     * Test method for
     * {@link FileUtil#isAvailable(String)}.
     */
    @Test
    public void testIsAvailable() {
        boolean actual = FileUtil.isAvailable("src/test/resources/test.txt");
        assertThat(actual, is(true));
    }

    /**
     * Test method for
     * {@link FileUtil#isAvailable(String)} with empty file path.
     */
    @Test
    public void testIsAvailable_withEmptyPath_thenExpectThrowException() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> FileUtil.isAvailable(null));
        assertThat(ex.getMessage(), equalTo("File path can't be empty"));
    }

    /**
     * Test method for
     * {@link FileUtil#isReadOnly(String)}.
     */
    @Test
    public void testIsReadOnly() {
        boolean actual = FileUtil.isReadOnly("src/test/resources/test.txt");
        assertThat(actual, is(false));
    }

    /**
     * Test method for
     * {@link FileUtil#isReadOnly(String)} with empty file path.
     */
    @Test
    public void testIsReadOnly_withEmptyPath_thenExpectThrowException() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> FileUtil.isReadOnly(null));
        assertThat(ex.getMessage(), equalTo("File path can't be empty"));
    }

    /**
     * Test method for
     * {@link FileUtil#copyFile(String, String)}.
     */
    @Test
    public void testCopyFile() {
        FileUtil.copyFile("src/test/resources/test.txt", "src/test/resources/test(copy).txt");

        boolean actual = FileUtil.isAvailable("src/test/resources/test(copy).txt");
        assertThat(actual, is(true));
    }

    /**
     * Test method for
     * {@link FileUtil#copyFile(String, String)} with empty source path.
     */
    @Test
    public void testCopyFile_withEmptySource_thenExpectThrowException() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                FileUtil.copyFile(null, "src/test/resources/test(copy).txt"));
        assertThat(ex.getMessage(), equalTo("Source path can't be empty"));
    }

    /**
     * Test method for
     * {@link FileUtil#copyFile(String, String)} with empty destination path.
     */
    @Test
    public void testCopyFile_withEmptyDestination_thenExpectThrowException() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                FileUtil.copyFile("src/test/resources/test.txt", null));
        assertThat(ex.getMessage(), equalTo("Destination path can't be empty"));
    }

    /**
     * Test method for {@link FileUtil#getPath(File)}.
     */
    @Test
    public void testGetPath() {
        File file = new File("src/test/resources/test.txt");
        String actual = FileUtil.getPath(file);
        assertThat(actual, is(equalTo(file.toPath().toString())));
    }

    /**
     * Test method for {@link FileUtil#getPath(File)} with null path.
     */
    @Test
    public void testGetPath_withNullPath_thenExpectThrowException() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> FileUtil.getPath(null));
        assertThat(ex.getMessage(), equalTo("File can't be null"));
    }

    /**
     * Test method for
     * {@link FileUtil#pathToFile(String)}.
     */
    @Test
    public void testPathToFile() {
        File actualFile = FileUtil.pathToFile("src/test/resources/test.txt");
        File expectedFile = new File("src/test/resources/test.txt");
        assertThat(actualFile, is(equalTo(expectedFile)));
    }

    /**
     * Test method for
     * {@link FileUtil#pathToFile(String)} with empty file path.
     */
    @Test
    public void testPathToFile_withEmptyPath_thenExpectThrowException() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> FileUtil.pathToFile(null));
        assertThat(ex.getMessage(), equalTo("File path can't be empty"));
    }

    /**
     * Test method for
     * {@link FileUtil#getAbsolutePath(File)}.
     */
    @Test
    public void testGetAbsolutePath() {
        File file = new File("src/test/resources/test.txt");
        String actual = FileUtil.getAbsolutePath(file);
        assertThat(actual, is(equalTo(file.getAbsolutePath())));
    }

    /**
     * Test method for
     * {@link FileUtil#getAbsolutePath(File)} with null file.
     */
    @Test
    public void testGetAbsolutePath_withNullFile_thenExpectThrowException() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                FileUtil.getAbsolutePath(null));
        assertThat(ex.getMessage(), equalTo("File can't be null"));
    }

    /**
     * Test method for
     * {@link FileUtil#getCanonicalPath(File)}.
     */
    @Test
    public void testGetCanonicalPath() throws Exception {
        File file = new File("src/test/resources/test.txt");
        String actual = FileUtil.getCanonicalPath(file);
        assertThat(actual, is(equalTo(file.getCanonicalPath())));
    }

    /**
     * Test method for
     * {@link FileUtil#getCanonicalPath(File)} with null file.
     */
    @Test
    public void testGetCanonicalPath_withNullFile_thenExpectThrowException() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> FileUtil.getCanonicalPath(null));
        assertThat(ex.getMessage(), equalTo("File can't be null"));
    }

    /**
     * Test method for
     * {@link FileUtil#getFileName(File)}.
     */
    @Test
    public void testGetFileName() {
        File file = new File("src/test/resources/test.txt");
        String actual = FileUtil.getFileName(file);
        assertThat(actual, is(equalTo(file.getName())));
    }

    /**
     * Test method for
     * {@link FileUtil#getFileName(File)} with null file.
     */
    @Test
    public void testGetFileName_withNullFile_thenExpectThrowException() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> FileUtil.getFileName(null));
        assertThat(ex.getMessage(), equalTo("File can't be null"));
    }

    /**
     * Test method for
     * {@link FileUtil#deleteFile(String)}.
     */
    @Test
    public void testDeleteFile() {
        FileUtil.copyFile("src/test/resources/test.txt", "src/test/resources/test(copy).txt");
        FileUtil.deleteFile("src/test/resources/test(copy).txt");

        boolean actual = FileUtil.isAvailable("src/test/resources/test(copy).txt");
        assertThat(actual, is(false));
    }

    /**
     * Test method for
     * {@link FileUtil#deleteFile(String)} with empty file path.
     */
    @Test
    public void testDeleteFile_withEmptyPath_thenExpectThrowException() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> FileUtil.deleteFile(null));
        assertThat(ex.getMessage(), equalTo("File path can't be empty"));
    }
}
