/**
 * 
 */
package com.dch.core.util.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.dch.core.util.TextUtil;

/**
 * Test class used to test all methods in the {@link TextUtil} class.
 * 
 * @author David.Christianto
 * @version 1.0.0-SNAPSHOT
 * @since 1.0.0-SNAPSHOT
 * @updated Apr 22, 2017
 */
public class TextUtilTest {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	/**
	 * Test method for
	 * {@link com.dch.core.util.TextUtil#isExist(java.lang.Object)}.
	 */
	@Test
	public void testIsExist() {
		boolean actual = TextUtil.isExist("test");
		assertThat(actual, is(true));
	}

	/**
	 * Test method for
	 * {@link com.dch.core.util.TextUtil#isExist(java.lang.Object)} with null
	 * parameter.
	 */
	@Test
	public void testIsExistWithNullParam() {
		boolean actual = TextUtil.isExist(null);
		assertThat(actual, is(false));
	}

	/**
	 * Test method for
	 * {@link com.dch.core.util.TextUtil#isEmpty(java.lang.Object)}.
	 */
	@Test
	public void testIsEmpty() {
		boolean actual = TextUtil.isEmpty("");
		assertThat(actual, is(true));
	}

	/**
	 * Test method for
	 * {@link com.dch.core.util.TextUtil#isEmpty(java.lang.Object)}.
	 */
	@Test
	public void testIsNotEmpty() {
		boolean actual = TextUtil.isEmpty("test");
		assertThat(actual, is(false));
	}

	/**
	 * Test method for
	 * {@link com.dch.core.util.TextUtil#parseToString(java.lang.Object)} with
	 * double value.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testParseDoubleToString() throws Exception {
		String actual = TextUtil.parseToString(new Double(1000));
		assertThat(actual, is(equalTo("1000.0")));
	}

	/**
	 * Test method for
	 * {@link com.dch.core.util.TextUtil#parseToString(java.lang.Object)} with
	 * integer value.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testParseIntegerToString() throws Exception {
		String actual = TextUtil.parseToString(new Integer(1000));
		assertThat(actual, is(equalTo("1000")));
	}

	/**
	 * Test method for
	 * {@link com.dch.core.util.TextUtil#parseToString(java.lang.Object)} with
	 * boolean value.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testParseBooleanToString() throws Exception {
		String actual = TextUtil.parseToString(true);
		assertThat(actual, is(equalTo("true")));
	}

	/**
	 * Test method for
	 * {@link com.dch.core.util.TextUtil#parseToString(java.lang.Object)} with
	 * bigdecimal value.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testParseBigDecimalToString() throws Exception {
		String actual = TextUtil.parseToString(new BigDecimal(1000));
		assertThat(actual, is(equalTo("1000")));
	}

	/**
	 * Test method for
	 * {@link com.dch.core.util.TextUtil#parseToString(java.lang.Object)} with
	 * empty parameter.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testParseToStringWithEmptyParameter() throws Exception {
		expectedException.expect(Exception.class);
		expectedException.expectMessage("Parameter can't be null");
		TextUtil.parseToString(null);
	}
}
