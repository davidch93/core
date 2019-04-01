/**
 *
 */
package com.dch.core.util.test;

import com.dch.core.util.ObjectUtil;
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
 * @version 1.0.0-SNAPSHOT
 * @since 1.0.0-SNAPSHOT
 * @updated Apr 22, 2017
 */
public class ObjectUtilTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    /**
     * Test method for
     * {@link com.dch.core.util.ObjectUtil#convertFileToBytes(java.io.File)}.
     *
     * @throws Exception
     */
    @Test
    public void testConvertFileToBytes() throws Exception {
        byte[] byteFile = ObjectUtil.convertFileToBytes(new File("src/test/resources/test.txt"));
        String actual = "";
        for (int i = 0; i < byteFile.length; i++) {
            actual += (char) byteFile[i];
        }
        assertThat(actual, is(equalTo("test")));
    }

    /**
     * Test method for
     * {@link com.dch.core.util.ObjectUtil#convertFileToBytes(java.io.File)} with
     * null parameter.
     *
     * @throws Exception
     */
    @Test
    public void testConvertFileToBytesWithNullParam() throws Exception {
        expectedException.expect(Exception.class);
        expectedException.expectMessage("File can't be null");
        ObjectUtil.convertFileToBytes(null);
    }

    /**
     * Test method for
     * {@link com.dch.core.util.ObjectUtil#convertBytesToFile(byte[], java.lang.String)}.
     *
     * @throws Exception
     */
    @Test
    public void testConvertBytesToFileByteArrayString() throws Exception {
        byte[] byteFile = ObjectUtil.convertFileToBytes(new File("src/test/resources/test.txt"));
        ObjectUtil.convertBytesToFile(byteFile, "src/test/resources/test_convert_bytes_to_file1.txt");

        byteFile = ObjectUtil.convertFileToBytes(new File("src/test/resources/test_convert_bytes_to_file1.txt"));
        String actual = "";
        for (int i = 0; i < byteFile.length; i++) {
            actual += (char) byteFile[i];
        }

        assertThat(actual, is(equalTo("test")));
    }

    /**
     * Test method for
     * {@link com.dch.core.util.ObjectUtil#convertBytesToFile(byte[], java.lang.String)}
     * with null byte array.
     *
     * @throws Exception
     */
    @Test
    public void testConvertBytesToFileByteArrayStringWithNullByteArray() throws Exception {
        expectedException.expect(Exception.class);
        expectedException.expectMessage("Array of bytes can't be null");
        ObjectUtil.convertBytesToFile(null, "src/test/resources/test_convert_bytes_to_file1.txt");
    }

    /**
     * Test method for
     * {@link com.dch.core.util.ObjectUtil#convertBytesToFile(byte[], java.lang.String)}
     * with empty destination path.
     *
     * @throws Exception
     */
    @Test
    public void testConvertBytesToFileByteArrayStringWithEmptyDestinationPath() throws Exception {
        expectedException.expect(Exception.class);
        expectedException.expectMessage("Destination path can't be empty");
        byte[] byteFile = ObjectUtil.convertFileToBytes(new File("src/test/resources/test.txt"));
        ObjectUtil.convertBytesToFile(byteFile, new String());
    }

    /**
     * Test method for
     * {@link com.dch.core.util.ObjectUtil#convertBytesToFile(byte[], java.io.File)}.
     *
     * @throws Exception
     */
    @Test
    public void testConvertBytesToFileByteArrayFile() throws Exception {
        byte[] byteFile = ObjectUtil.convertFileToBytes(new File("src/test/resources/test.txt"));
        ObjectUtil.convertBytesToFile(byteFile, new File("src/test/resources/test_convert_bytes_to_file2.txt"));

        byteFile = ObjectUtil.convertFileToBytes(new File("src/test/resources/test_convert_bytes_to_file2.txt"));
        String actual = "";
        for (int i = 0; i < byteFile.length; i++) {
            actual += (char) byteFile[i];
        }

        assertThat(actual, is(equalTo("test")));
    }

    /**
     * Test method for
     * {@link com.dch.core.util.ObjectUtil#convertBytesToFile(byte[], java.io.File)}
     * with null byte array.
     *
     * @throws Exception
     */
    @Test
    public void testConvertBytesToFileByteArrayFileWithNullByteArray() throws Exception {
        expectedException.expect(Exception.class);
        expectedException.expectMessage("Array of bytes can't be null");
        ObjectUtil.convertBytesToFile(null, new File("src/test/resources/test_convert_bytes_to_file2.txt"));
    }
}
