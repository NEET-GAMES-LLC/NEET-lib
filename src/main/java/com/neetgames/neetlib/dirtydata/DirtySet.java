package com.neetgames.neetlib.dirtydata;

import com.google.common.base.Objects;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class DirtySet<E> implements Set<E>, Dirty {

    private int hashCodeCache;
    private @NotNull Set<E> data;

    public DirtySet(@NotNull Set<E> data) {
        this.data = data;
        this.hashCodeCache = data.hashCode();
    }

    @Override
    public boolean isDirty() {
        return hashCodeCache != data.hashCode();
    }

    @Override
    public void resetDirty() {
        this.hashCodeCache = data.hashCode();
    }

    /**
     * Assign the inner wrapped set to a new value
     * @param dataSet the new value to assign the inner wrapped set
     */
    public void setSet(@NotNull Set<E> dataSet) {
        this.data = dataSet;
    }

    /**
     * Get the wrapped set of this DirtySet
     * @return the inner wrapped Set of this DirtySet
     */
    public @NotNull Set<E> unwrapSet() {
        return data;
    }

    /* Set Interface Delegates */

    @Override
    public int size() {
        return data.size();
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return data.contains(o);
    }

    @Override
    public @NotNull Iterator<E> iterator() {
        return data.iterator();
    }

    @Override
    public Object[] toArray() {
        return data.toArray();
    }

    @Override
    public <T> T[] toArray(@NotNull T[] ts) {
        return data.toArray(ts);
    }

    @Override
    public boolean add(E e) {
        return data.add(e);
    }

    @Override
    public boolean remove(Object o) {
        return data.remove(o);
    }

    @Override
    public boolean containsAll(@NotNull Collection<?> collection) {
        return data.containsAll(collection);
    }

    @Override
    public boolean addAll(@NotNull Collection<? extends E> collection) {
        return data.addAll(collection);
    }

    @Override
    public boolean retainAll(@NotNull Collection<?> collection) {
        return data.retainAll(collection);
    }

    @Override
    public boolean removeAll(@NotNull Collection<?> collection) {
        return data.removeAll(collection);
    }

    @Override
    public void clear() {
        data.clear();
    }

    @Override
    public Spliterator<E> spliterator() {
        return data.spliterator();
    }

    @Override
    public boolean removeIf(Predicate<? super E> filter) {
        return data.removeIf(filter);
    }

    @Override
    public Stream<E> stream() {
        return data.stream();
    }

    @Override
    public Stream<E> parallelStream() {
        return data.parallelStream();
    }

    @Override
    public void forEach(Consumer<? super E> action) {
        data.forEach(action);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DirtySet<?> dirtySet = (DirtySet<?>) o;
        return Objects.equal(data, dirtySet.data);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(data);
    }
}
