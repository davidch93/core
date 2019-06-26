package com.dch.core.dto;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

/**
 * An immutable key/value pair.
 *
 * @param <K> the type of the key
 * @param <V> the type of the value
 * @author david.christianto
 * @version 2.0.0
 */
public class KV<K, V> implements Serializable {

    private K key;
    private V value;

    private KV(K key, V value) {
        this.key = key;
        this.value = value;
    }

    /**
     * A convenience way to construct {@link KV}.
     *
     * @param key   Key of {@link KV}
     * @param value Value of {@link KV}
     * @param <K>   the type of the key
     * @param <V>   the type of the value
     * @return A {@link KV} with the given key and value.
     */
    public static <K, V> KV<K, V> of(K key, V value) {
        return new KV<>(key, value);
    }

    /**
     * @return the key of this {@link KV}
     */
    public K getKey() {
        return key;
    }

    /**
     * @return the value of this {@link KV}
     */
    public V getValue() {
        return value;
    }

    @Override
    public int hashCode() {
        // Objects.deepEquals requires Arrays.deepHashCode for correctness
        return Arrays.deepHashCode(new Object[]{key, value});
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof KV)) {
            return false;
        }
        KV<?, ?> otherKv = (KV<?, ?>) other;
        // Arrays are very common as values and keys, so deepEquals is mandatory
        return Objects.deepEquals(this.key, otherKv.key) && Objects.deepEquals(this.value, otherKv.value);
    }

    /**
     * A {@link Comparator} that orders {@link KV KVs} by the natural ordering of their keys.
     *
     * <p>A {@code null} key is less than any non-{@code null} key.
     *
     * @param <K> the type of the key
     * @param <V> the type of the value
     * @see java.util.Comparator
     * @see java.io.Serializable
     */
    public static class OrderByKey<K extends Comparable<? super K>, V> implements Comparator<KV<K, V>>, Serializable {

        @Override
        public int compare(KV<K, V> a, KV<K, V> b) {
            if (a.key == null) {
                return b.key == null ? 0 : -1;
            } else if (b.key == null) {
                return 1;
            } else {
                return a.key.compareTo(b.key);
            }
        }
    }

    /**
     * A {@link Comparator} that orders {@link KV KVs} by the natural ordering of their values.
     *
     * <p>A {@code null} value is less than any non-{@code null} value.
     *
     * @param <K> the type of the key
     * @param <V> the type of the value
     * @see java.util.Comparator
     * @see java.io.Serializable
     */
    public static class OrderByValue<K, V extends Comparable<? super V>> implements Comparator<KV<K, V>>, Serializable {

        @Override
        public int compare(KV<K, V> a, KV<K, V> b) {
            if (a.value == null) {
                return b.value == null ? 0 : -1;
            } else if (b.value == null) {
                return 1;
            } else {
                return a.value.compareTo(b.value);
            }
        }
    }
}
