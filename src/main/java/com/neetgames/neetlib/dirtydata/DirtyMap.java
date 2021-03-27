package com.neetgames.neetlib.dirtydata;

import com.google.common.base.Objects;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

public class DirtyMap<K, V> implements Map<K, V>, Dirty {

    private @NotNull Map<K, V> map;
    private int dataHashCache;

    public DirtyMap(@NotNull Map<K, V> data) {
        this.map = data;
        this.dataHashCache = data.hashCode();
    }

    @Override
    public boolean isDirty() {
        return dataHashCache != unwrapMap().hashCode();
    }

    @Override
    public void resetDirty() {
        resetHashCache();
    }

    /**
     * Change the map contained in this wrapper
     *
     * @param dataMap the map to wrap around instead of the current map
     */
    public void setMap(@NotNull Map<K, V> dataMap) {
        this.map = dataMap;
    }

    /**
     * Get the inner map that this DirtyMap is wrapping
     *
     * @return the inner map of this DirtyMap
     */
    public @NotNull Map<K, V> unwrapMap() {
        return map;
    }

    /* Map Interface Delegates */

    @Override
    public V get(Object key) {
        return map.get(key);
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    @Override
    public V put(K key, V value) {
        return map.put(key, value);
    }

    @Override
    public V remove(Object key) {
        return map.remove(key);
    }

    @Override
    public void putAll(@NotNull Map<? extends K, ? extends V> m) {
        map.putAll(m);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public @NotNull Set<K> keySet() {
        return map.keySet();
    }

    @Override
    public @NotNull Collection<V> values() {
        return map.values();
    }

    @Override
    public @NotNull Set<Entry<K, V>> entrySet() {
        return map.entrySet();
    }

    @Override
    public V getOrDefault(Object key, V defaultValue) {
        return map.getOrDefault(key, defaultValue);
    }

    @Override
    public void forEach(BiConsumer<? super K, ? super V> action) {
        map.forEach(action);
    }

    @Override
    public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function) {
        map.replaceAll(function);
    }

    @Override
    public V putIfAbsent(K key, V value) {
        return map.putIfAbsent(key, value);
    }

    @Override
    public boolean remove(Object key, Object value) {
        return map.remove(key, value);
    }

    @Override
    public boolean replace(K key, V oldValue, V newValue) {
        return map.replace(key, oldValue, newValue);
    }

    @Override
    public V replace(K key, V value) {
        return map.replace(key, value);
    }

    @Override
    public V computeIfAbsent(K key, @NotNull Function<? super K, ? extends V> mappingFunction) {
        return map.computeIfAbsent(key, mappingFunction);
    }

    @Override
    public V computeIfPresent(K key, @NotNull BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        return map.computeIfPresent(key, remappingFunction);
    }

    @Override
    public V compute(K key, @NotNull BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        return map.compute(key, remappingFunction);
    }

    @Override
    public V merge(K key, @NotNull V value, @NotNull BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
        return map.merge(key, value, remappingFunction);
    }

    private void resetHashCache() {
        dataHashCache = unwrapMap().hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DirtyMap<?, ?> dirtyMap = (DirtyMap<?, ?>) o;
        return dataHashCache == dirtyMap.dataHashCache && Objects.equal(map, dirtyMap.map);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(map, dataHashCache);
    }
}
