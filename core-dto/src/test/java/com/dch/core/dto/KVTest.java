package com.dch.core.dto;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Test class used to test all methods in the {@link KV} class.
 *
 * @author David.Christianto
 * @version 2.0.0
 */
public class KVTest {

    private static final Integer[] TEST_VALUES = {null, Integer.MIN_VALUE, -1, 0, 1, Integer.MAX_VALUE};

    // Wrapper around Integer.compareTo() to support null values.
    private int compareInt(Integer a, Integer b) {
        if (a == null) {
            return b == null ? 0 : -1;
        } else {
            return b == null ? 1 : a.compareTo(b);
        }
    }

    @Test
    public void testEquals() {
        // Neither position are arrays
        assertThat(KV.of(1, 2), equalTo(KV.of(1, 2)));

        // Key is array
        assertThat(KV.of(new int[]{1, 2}, 3), equalTo(KV.of(new int[]{1, 2}, 3)));

        // Value is array
        assertThat(KV.of(1, new int[]{2, 3}), equalTo(KV.of(1, new int[]{2, 3})));

        // Both are arrays
        assertThat(KV.of(new int[]{1, 2}, new int[]{3, 4}), equalTo(KV.of(new int[]{1, 2}, new int[]{3, 4})));

        // Unfortunately, deep equals only goes so far
        assertThat(
                KV.of(ImmutableList.of(new int[]{1, 2}), 3),
                not(equalTo(KV.of(ImmutableList.of(new int[]{1, 2}), 3))));
        assertThat(
                KV.of(1, ImmutableList.of(new int[]{2, 3})),
                not(equalTo(KV.of(1, ImmutableList.of(new int[]{2, 3})))));

        // Key is array and differs
        assertThat(KV.of(new int[]{1, 2}, 3), not(equalTo(KV.of(new int[]{1, 37}, 3))));

        // Key is non-array and differs
        assertThat(KV.of(1, new int[]{2, 3}), not(equalTo(KV.of(37, new int[]{1, 2}))));

        // Value is array and differs
        assertThat(KV.of(1, new int[]{2, 3}), not(equalTo(KV.of(1, new int[]{37, 3}))));

        // Value is non-array and differs
        assertThat(KV.of(new byte[]{1, 2}, 3), not(equalTo(KV.of(new byte[]{1, 2}, 37))));
    }

    @Test
    public void testOrderByKey() {
        Comparator<KV<Integer, Integer>> orderByKey = new KV.OrderByKey<>();
        for (Integer key1 : TEST_VALUES) {
            for (Integer val1 : TEST_VALUES) {
                for (Integer key2 : TEST_VALUES) {
                    for (Integer val2 : TEST_VALUES) {
                        assertEquals(
                                compareInt(key1, key2), orderByKey.compare(KV.of(key1, val1), KV.of(key2, val2)));
                    }
                }
            }
        }
    }

    @Test
    public void testOrderByKey2() {
        List<KV<Integer, String>> kvs = Arrays.asList(KV.of(2, "value 2"), KV.of(1, "value 1"), KV.of(3, "value3"));
        kvs.sort(new KV.OrderByKey<>());

        assertThat(kvs, not(equalTo(Arrays.asList(KV.of(2, "value 2"), KV.of(1, "value 1"), KV.of(3, "value3")))));
        assertThat(kvs, equalTo(Arrays.asList(KV.of(1, "value 1"), KV.of(2, "value 2"), KV.of(3, "value3"))));
    }

    @Test
    public void testOrderByValue() {
        Comparator<KV<Integer, Integer>> orderByValue = new KV.OrderByValue<>();
        for (Integer key1 : TEST_VALUES) {
            for (Integer val1 : TEST_VALUES) {
                for (Integer key2 : TEST_VALUES) {
                    for (Integer val2 : TEST_VALUES) {
                        assertEquals(
                                compareInt(val1, val2), orderByValue.compare(KV.of(key1, val1), KV.of(key2, val2)));
                    }
                }
            }
        }
    }

    @Test
    public void testOrderByValue2() {
        List<KV<String, Integer>> kvs = Arrays.asList(KV.of("value 2", 2), KV.of("value 1", 1), KV.of("value3", 3));
        kvs.sort(new KV.OrderByValue<>());

        assertThat(kvs, not(equalTo(Arrays.asList(KV.of("value 2", 2), KV.of("value 1", 1), KV.of("value3", 3)))));
        assertThat(kvs, equalTo(Arrays.asList(KV.of("value 1", 1), KV.of("value 2", 2), KV.of("value3", 3))));
    }
}
