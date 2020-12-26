package com.dch.core.common.util;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test class used to test all methods in the {@link ObjectUtil} class.
 *
 * @author David.Christianto
 * @version 2.0.0
 * @since 1.0.0
 */
public class ObjectUtilTest {

    /**
     * Test method for
     * {@link ObjectUtil#convertFileToBytes(File)}.
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
     * {@link ObjectUtil#convertFileToBytes(File)} with null parameter.
     */
    @Test
    public void testConvertFileToBytes_withNullParam_thenExpectThrowException() {
        Exception ex = assertThrows(Exception.class, () -> ObjectUtil.convertFileToBytes(null));
        assertThat(ex.getMessage(), equalTo("File can't be null"));
    }

    /**
     * Test method for
     * {@link ObjectUtil#convertBytesToFile(byte[], String)}.
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
     * {@link ObjectUtil#convertBytesToFile(byte[], String)} with null byte array.
     */
    @Test
    public void testConvertBytesToFileByteArrayString_withNullByteArray_thenExpectThrowException() {
        Exception ex = assertThrows(Exception.class, () ->
                ObjectUtil.convertBytesToFile(null, "src/test/resources/test_convert_bytes_to_file1.txt"));
        assertThat(ex.getMessage(), equalTo("Array of bytes can't be null"));
    }

    /**
     * Test method for
     * {@link ObjectUtil#convertBytesToFile(byte[], String)} with empty destination path.
     */
    @Test
    public void testConvertBytesToFileByteArrayString_withEmptyDestinationPath_thenExpectThrowException() {
        byte[] byteFile = ObjectUtil.convertFileToBytes(new File("src/test/resources/test.txt"));
        Exception ex = assertThrows(Exception.class, () -> ObjectUtil.convertBytesToFile(byteFile, ""));
        assertThat(ex.getMessage(), equalTo("Destination path can't be empty"));
    }

    /**
     * Test method for
     * {@link ObjectUtil#convertBytesToFile(byte[], File)}.
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
     * {@link ObjectUtil#convertBytesToFile(byte[], File)} with null byte array.
     */
    @Test
    public void testConvertBytesToFileByteArrayFile_withNullByteArray_thenExpectThrowException() {
        Exception ex = assertThrows(Exception.class, () ->
                ObjectUtil.convertBytesToFile(null, new File("src/test/resources/test_convert_bytes_to_file2.txt")));
        assertThat(ex.getMessage(), equalTo("Array of bytes can't be null"));
    }

    @Test
    public void testGetFieldValue() {
        Data data = new Data(1, "data");
        int actualId = (Integer) ObjectUtil.getFieldValue(data, "id");
        String actualUser = (String) ObjectUtil.getFieldValue(data, "user");

        assertThat(actualId, equalTo(1));
        assertThat(actualUser, equalTo("data"));
    }

    @Test
    public void testGetFieldValue_withNotExistField_thenExpectThrowException() {
        Data data = new Data(1, "data");
        RuntimeException ex = assertThrows(RuntimeException.class, () -> ObjectUtil.getFieldValue(data, "address"));
        assertThat(ex.getMessage(), equalTo("Error occurred while reflect object"));
    }

    /**
     * Class that represent model data for testing purposes.
     */
    static class Data {

        private int id;
        private String user;

        Data(int id, String user) {
            this.id = id;
            this.user = user;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }
    }
}
