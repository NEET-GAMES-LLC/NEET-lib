package com.neetgames.neetlib.dirtydata;

public interface Dirty {
    /**
     * If data has changed it is considered dirty
     *
     * @return true if data is dirty
     */
    boolean isDirty();

    /**
     * Mark data as not dirty, ideally this is done after you have saved or synced data
     */
    void resetDirty();
}
