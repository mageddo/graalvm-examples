package com.mageddo.commons;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class Maps {
	private Maps() {
	}

	public static <K, V> Map<K, V> of() {
		return Collections.unmodifiableMap(new HashMap<>());
	}

	/**
	 * Returns an unmodifiable map containing a single mapping.
	 * See <a href="#unmodifiable">Unmodifiable Maps</a> for details.
	 *
	 * @param <K> the {@code Map}'s key type
	 * @param <V> the {@code Map}'s value type
	 * @param k1 the mapping's key
	 * @param v1 the mapping's value
	 * @return a {@code Map} containing the specified mapping
	 * @throws NullPointerException if the key or the value is {@code null}
	 *
	 * @since 9
	 */
	public static <K, V> Map<K, V> of(K k1, V v1) {
		final Map<K, V> m = new HashMap<>();
		m.put(k1, v1);
		return Collections.unmodifiableMap(m);
	}

	/**
	 * Returns an unmodifiable map containing two mappings.
	 * See <a href="#unmodifiable">Unmodifiable Maps</a> for details.
	 *
	 * @param <K> the {@code Map}'s key type
	 * @param <V> the {@code Map}'s value type
	 * @param k1 the first mapping's key
	 * @param v1 the first mapping's value
	 * @param k2 the second mapping's key
	 * @param v2 the second mapping's value
	 * @return a {@code Map} containing the specified mappings
	 * @throws IllegalArgumentException if the keys are duplicates
	 * @throws NullPointerException if any key or value is {@code null}
	 *
	 * @since 9
	 */
	public static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2) {
		final Map<K, V> m = new HashMap<>();
		m.put(k1, v1);
		m.put(k2, v2);
		return Collections.unmodifiableMap(m);
	}

	/**
	 * Returns an unmodifiable map containing three mappings.
	 * See <a href="#unmodifiable">Unmodifiable Maps</a> for details.
	 *
	 * @param <K> the {@code Map}'s key type
	 * @param <V> the {@code Map}'s value type
	 * @param k1 the first mapping's key
	 * @param v1 the first mapping's value
	 * @param k2 the second mapping's key
	 * @param v2 the second mapping's value
	 * @param k3 the third mapping's key
	 * @param v3 the third mapping's value
	 * @return a {@code Map} containing the specified mappings
	 * @throws IllegalArgumentException if there are any duplicate keys
	 * @throws NullPointerException if any key or value is {@code null}
	 *
	 * @since 9
	 */
	public static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3) {
		final Map<K, V> m = new HashMap<>();
		m.put(k1, v1);
		m.put(k2, v2);
		m.put(k3, v3);
		return Collections.unmodifiableMap(m);
	}

	/**
	 * Returns an unmodifiable map containing four mappings.
	 * See <a href="#unmodifiable">Unmodifiable Maps</a> for details.
	 *
	 * @param <K> the {@code Map}'s key type
	 * @param <V> the {@code Map}'s value type
	 * @param k1 the first mapping's key
	 * @param v1 the first mapping's value
	 * @param k2 the second mapping's key
	 * @param v2 the second mapping's value
	 * @param k3 the third mapping's key
	 * @param v3 the third mapping's value
	 * @param k4 the fourth mapping's key
	 * @param v4 the fourth mapping's value
	 * @return a {@code Map} containing the specified mappings
	 * @throws IllegalArgumentException if there are any duplicate keys
	 * @throws NullPointerException if any key or value is {@code null}
	 *
	 * @since 9
	 */
	public static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4) {
		final Map<K, V> m = new HashMap<>();
		m.put(k1, v1);
		m.put(k2, v2);
		m.put(k3, v3);
		m.put(k4, v4);
		return Collections.unmodifiableMap(m);
	}

	/**
	 * Returns an unmodifiable map containing five mappings.
	 * See <a href="#unmodifiable">Unmodifiable Maps</a> for details.
	 *
	 * @param <K> the {@code Map}'s key type
	 * @param <V> the {@code Map}'s value type
	 * @param k1 the first mapping's key
	 * @param v1 the first mapping's value
	 * @param k2 the second mapping's key
	 * @param v2 the second mapping's value
	 * @param k3 the third mapping's key
	 * @param v3 the third mapping's value
	 * @param k4 the fourth mapping's key
	 * @param v4 the fourth mapping's value
	 * @param k5 the fifth mapping's key
	 * @param v5 the fifth mapping's value
	 * @return a {@code Map} containing the specified mappings
	 * @throws IllegalArgumentException if there are any duplicate keys
	 * @throws NullPointerException if any key or value is {@code null}
	 *
	 * @since 9
	 */
	public static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5) {
		final Map<K, V> m = new HashMap<>();
		m.put(k1, v1);
		m.put(k2, v2);
		m.put(k3, v3);
		m.put(k4, v4);
		m.put(k5, v5);
		return Collections.unmodifiableMap(m);
	}

	/**
	 * Returns an unmodifiable map containing six mappings.
	 * See <a href="#unmodifiable">Unmodifiable Maps</a> for details.
	 *
	 * @param <K> the {@code Map}'s key type
	 * @param <V> the {@code Map}'s value type
	 * @param k1 the first mapping's key
	 * @param v1 the first mapping's value
	 * @param k2 the second mapping's key
	 * @param v2 the second mapping's value
	 * @param k3 the third mapping's key
	 * @param v3 the third mapping's value
	 * @param k4 the fourth mapping's key
	 * @param v4 the fourth mapping's value
	 * @param k5 the fifth mapping's key
	 * @param v5 the fifth mapping's value
	 * @param k6 the sixth mapping's key
	 * @param v6 the sixth mapping's value
	 * @return a {@code Map} containing the specified mappings
	 * @throws IllegalArgumentException if there are any duplicate keys
	 * @throws NullPointerException if any key or value is {@code null}
	 *
	 * @since 9
	 */
	public static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5,
														 K k6, V v6) {
		final Map<K, V> m = new HashMap<>();
		m.put(k1, v1);
		m.put(k2, v2);
		m.put(k3, v3);
		m.put(k4, v4);
		m.put(k5, v5);
		m.put(k6, v6);
		return Collections.unmodifiableMap(m);
	}

	/**
	 * Returns an unmodifiable map containing seven mappings.
	 * See <a href="#unmodifiable">Unmodifiable Maps</a> for details.
	 *
	 * @param <K> the {@code Map}'s key type
	 * @param <V> the {@code Map}'s value type
	 * @param k1 the first mapping's key
	 * @param v1 the first mapping's value
	 * @param k2 the second mapping's key
	 * @param v2 the second mapping's value
	 * @param k3 the third mapping's key
	 * @param v3 the third mapping's value
	 * @param k4 the fourth mapping's key
	 * @param v4 the fourth mapping's value
	 * @param k5 the fifth mapping's key
	 * @param v5 the fifth mapping's value
	 * @param k6 the sixth mapping's key
	 * @param v6 the sixth mapping's value
	 * @param k7 the seventh mapping's key
	 * @param v7 the seventh mapping's value
	 * @return a {@code Map} containing the specified mappings
	 * @throws IllegalArgumentException if there are any duplicate keys
	 * @throws NullPointerException if any key or value is {@code null}
	 *
	 * @since 9
	 */
	public static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5,
														 K k6, V v6, K k7, V v7) {
		final Map<K, V> m = new HashMap<>();
		m.put(k1, v1);
		m.put(k2, v2);
		m.put(k3, v3);
		m.put(k4, v4);
		m.put(k5, v5);
		m.put(k6, v6);
		m.put(k7, v7);
		return Collections.unmodifiableMap(m);
	}

	/**
	 * Returns an unmodifiable map containing eight mappings.
	 * See <a href="#unmodifiable">Unmodifiable Maps</a> for details.
	 *
	 * @param <K> the {@code Map}'s key type
	 * @param <V> the {@code Map}'s value type
	 * @param k1 the first mapping's key
	 * @param v1 the first mapping's value
	 * @param k2 the second mapping's key
	 * @param v2 the second mapping's value
	 * @param k3 the third mapping's key
	 * @param v3 the third mapping's value
	 * @param k4 the fourth mapping's key
	 * @param v4 the fourth mapping's value
	 * @param k5 the fifth mapping's key
	 * @param v5 the fifth mapping's value
	 * @param k6 the sixth mapping's key
	 * @param v6 the sixth mapping's value
	 * @param k7 the seventh mapping's key
	 * @param v7 the seventh mapping's value
	 * @param k8 the eighth mapping's key
	 * @param v8 the eighth mapping's value
	 * @return a {@code Map} containing the specified mappings
	 * @throws IllegalArgumentException if there are any duplicate keys
	 * @throws NullPointerException if any key or value is {@code null}
	 *
	 * @since 9
	 */
	public static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5,
														 K k6, V v6, K k7, V v7, K k8, V v8) {
		final Map<K, V> m = new HashMap<>();
		m.put(k1, v1);
		m.put(k2, v2);
		m.put(k3, v3);
		m.put(k4, v4);
		m.put(k5, v5);
		m.put(k6, v6);
		m.put(k7, v7);
		m.put(k8, v8);
		return Collections.unmodifiableMap(m);
	}

	/**
	 * Returns an unmodifiable map containing nine mappings.
	 * See <a href="#unmodifiable">Unmodifiable Maps</a> for details.
	 *
	 * @param <K> the {@code Map}'s key type
	 * @param <V> the {@code Map}'s value type
	 * @param k1 the first mapping's key
	 * @param v1 the first mapping's value
	 * @param k2 the second mapping's key
	 * @param v2 the second mapping's value
	 * @param k3 the third mapping's key
	 * @param v3 the third mapping's value
	 * @param k4 the fourth mapping's key
	 * @param v4 the fourth mapping's value
	 * @param k5 the fifth mapping's key
	 * @param v5 the fifth mapping's value
	 * @param k6 the sixth mapping's key
	 * @param v6 the sixth mapping's value
	 * @param k7 the seventh mapping's key
	 * @param v7 the seventh mapping's value
	 * @param k8 the eighth mapping's key
	 * @param v8 the eighth mapping's value
	 * @param k9 the ninth mapping's key
	 * @param v9 the ninth mapping's value
	 * @return a {@code Map} containing the specified mappings
	 * @throws IllegalArgumentException if there are any duplicate keys
	 * @throws NullPointerException if any key or value is {@code null}
	 *
	 * @since 9
	 */
	public static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5,
														 K k6, V v6, K k7, V v7, K k8, V v8, K k9, V v9) {
		final Map<K, V> m = new HashMap<>();
		m.put(k1, v1);
		m.put(k2, v2);
		m.put(k3, v3);
		m.put(k4, v4);
		m.put(k5, v5);
		m.put(k6, v6);
		m.put(k7, v7);
		m.put(k8, v8);
		m.put(k9, v9);
		return Collections.unmodifiableMap(m);
	}

	/**
	 * Returns an unmodifiable map containing ten mappings.
	 * See <a href="#unmodifiable">Unmodifiable Maps</a> for details.
	 *
	 * @param <K> the {@code Map}'s key type
	 * @param <V> the {@code Map}'s value type
	 * @param k1 the first mapping's key
	 * @param v1 the first mapping's value
	 * @param k2 the second mapping's key
	 * @param v2 the second mapping's value
	 * @param k3 the third mapping's key
	 * @param v3 the third mapping's value
	 * @param k4 the fourth mapping's key
	 * @param v4 the fourth mapping's value
	 * @param k5 the fifth mapping's key
	 * @param v5 the fifth mapping's value
	 * @param k6 the sixth mapping's key
	 * @param v6 the sixth mapping's value
	 * @param k7 the seventh mapping's key
	 * @param v7 the seventh mapping's value
	 * @param k8 the eighth mapping's key
	 * @param v8 the eighth mapping's value
	 * @param k9 the ninth mapping's key
	 * @param v9 the ninth mapping's value
	 * @param k10 the tenth mapping's key
	 * @param v10 the tenth mapping's value
	 * @return a {@code Map} containing the specified mappings
	 * @throws IllegalArgumentException if there are any duplicate keys
	 * @throws NullPointerException if any key or value is {@code null}
	 *
	 * @since 9
	 */
	public static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5,
														 K k6, V v6, K k7, V v7, K k8, V v8, K k9, V v9, K k10, V v10) {
		final Map<K, V> m = new HashMap<>();
		m.put(k1, v1);
		m.put(k2, v2);
		m.put(k3, v3);
		m.put(k4, v4);
		m.put(k5, v5);
		m.put(k6, v6);
		m.put(k7, v7);
		m.put(k8, v8);
		m.put(k9, v9);
		m.put(k10, v10);
		return Collections.unmodifiableMap(m);
	}
}
