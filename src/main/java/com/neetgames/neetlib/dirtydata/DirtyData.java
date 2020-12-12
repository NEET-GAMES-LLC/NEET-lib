package com.neetgames.neetlib.dirtydata;

import com.google.common.base.Objects;
import com.neetgames.neetlib.mutableprimitives.MutableBoolean;
import org.jetbrains.annotations.NotNull;

public class DirtyData<T> implements Dirty {

    private final @NotNull MutableBoolean dirtyFlag; //Can be pointed at a reference
    private @NotNull T data;


    public DirtyData(@NotNull T data, @NotNull MutableBoolean referenceFlag) {
        this.data = data;
        this.dirtyFlag = referenceFlag;
    }

    public boolean isDirty() {
        return dirtyFlag.getImmutableCopy();
    }

    public void setDirty(boolean newDirtyValue) {
        dirtyFlag.setBoolean(newDirtyValue);
    }

    public void setDirty() {
        setDirty(true);
    }

    public @NotNull T getData() {
        return data;
    }

    public T getData(boolean newDirty) {
        setDirty(newDirty);
        return data;
    }

    public void setData(@NotNull T data) {
        this.data = data;
        setDirty(true);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DirtyData<?> dirtyData = (DirtyData<?>) o;
        return Objects.equal(getData(), dirtyData.getData());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getData());
    }
}
