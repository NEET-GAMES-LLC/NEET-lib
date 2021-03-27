package com.neetgames.neetlib.dirtydata;

import com.google.common.base.Objects;
import org.jetbrains.annotations.NotNull;

public class DirtyData<T> implements Dirty {

    private @NotNull T data;
    private int dataHash;

    public DirtyData(@NotNull T data) {
        this.data = data;
        resetDataHash(data);
    }

    private void resetDataHash(@NotNull T data) {
        dataHash = data.hashCode();
    }

    public boolean isDirty() {
        return dataHash != data.hashCode();
    }

    public void resetDirty() {
        resetDataHash(data);
    }

    public @NotNull T getData() {
        return data;
    }

    public void setData(@NotNull T data) {
        this.data = data;
        resetDataHash(data);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DirtyData<?> dirtyData = (DirtyData<?>) o;
        return dataHash == dirtyData.dataHash && Objects.equal(data, dirtyData.data);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(data, dataHash);
    }
}
