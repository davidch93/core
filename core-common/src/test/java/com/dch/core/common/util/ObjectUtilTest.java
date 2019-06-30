package com.dch.core.common.util;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Test class used to test all methods in the {@link ObjectUtil} class.
 *
 * @author David.Christianto
 * @version 2.0.0
 * @since 1.0.0
 */
public class ObjectUtilTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    /**
     * Test method for
     * {@link ObjectUtil#convertFileToBytes(java.io.File)}.
     */
    @Test
    public void testConvertFileToBytes() {
        byte[] byteFile = ObjectUtil.convertFileToBytes(new File("src/test/resources/test.txt"));
        StringBuilder actual = new StringBuilder();
        for (byte b : byteFile) {
            actual.append((char) b);
        }
        assertThat(actual.toString(), is(equalTo("test")));
    }

    /**
     * Test method for
     * {@link ObjectUtil#convertFileToBytes(java.io.File)} with
     * null parameter.
     */
    @Test
    public void testConvertFileToBytesWithNullParam() {
        expectedException.expect(Exception.class);
        expectedException.expectMessage("File can't be null");
        ObjectUtil.convertFileToBytes(null);
    }

    /**
     * Test method for
     * {@link ObjectUtil#convertBytesToFile(byte[], java.lang.String)}.
     */
    @Test
    public void testConvertBytesToFileByteArrayString() {
        byte[] byteFile = ObjectUtil.convertFileToBytes(new File("src/test/resources/test.txt"));
        ObjectUtil.convertBytesToFile(byteFile, "src/test/resources/test_convert_bytes_to_file1.txt");

        byteFile = ObjectUtil.convertFileToBytes(new File("src/test/resources/test_convert_bytes_to_file1.txt"));
        StringBuilder actual = new StringBuilder();
        for (byte b : byteFile) {
            actual.append((char) b);
        }

        assertThat(actual.toString(), is(equalTo("test")));
    }

    /**
     * Test method for
     * {@link ObjectUtil#convertBytesToFile(byte[], java.lang.String)}
     * with null byte array.
     */
    @Test
    public void testConvertBytesToFileByteArrayStringWithNullByteArray() {
        expectedException.expect(Exception.class);
        expectedException.expectMessage("Array of bytes can't be null");
        ObjectUtil.convertBytesToFile(null, "src/test/resources/test_convert_bytes_to_file1.txt");
    }

    /**
     * Test method for
     * {@link ObjectUtil#convertBytesToFile(byte[], java.lang.String)}
     * with empty destination path.
     */
    @Test
    public void testConvertBytesToFileByteArrayStringWithEmptyDestinationPath() {
        expectedException.expect(Exception.class);
        expectedException.expectMessage("Destination path can't be empty");
        byte[] byteFile = ObjectUtil.convertFileToBytes(new File("src/test/resources/test.txt"));
        ObjectUtil.convertBytesToFile(byteFile, "");
    }

    /**
     * Test method for
     * {@link ObjectUtil#convertBytesToFile(byte[], java.io.File)}.
     */
    @Test
    public void testConvertBytesToFileByteArrayFile() {
        byte[] byteFile = ObjectUtil.convertFileToBytes(new File("src/test/resources/test.txt"));
        ObjectUtil.convertBytesToFile(byteFile, new File("src/test/resources/test_convert_bytes_to_file2.txt"));

        byteFile = ObjectUtil.convertFileToBytes(new File("src/test/resources/test_convert_bytes_to_file2.txt"));
        StringBuilder actual = new StringBuilder();
        for (byte b : byteFile) {
            actual.append((char) b);
        }

        assertThat(actual.toString(), is(equalTo("test")));
    }

    /**
     * Test method for
     * {@link ObjectUtil#convertBytesToFile(byte[], java.io.File)}
     * with null byte array.
     */
    @Test
    public void testConvertBytesToFileByteArrayFileWithNullByteArray() {
        expectedException.expect(Exception.class);
        expectedException.expectMessage("Array of bytes can't be null");
        ObjectUtil.convertBytesToFile(null, new File("src/test/resources/test_convert_bytes_to_file2.txt"));
    }
}
