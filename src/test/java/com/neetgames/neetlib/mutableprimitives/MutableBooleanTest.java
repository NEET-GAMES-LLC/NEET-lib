package com.neetgames.neetlib.mutableprimitives;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MutableBooleanTest {

    static MutableBoolean mutableBoolean;

    @BeforeEach
    void setUp() {
        System.out.println("Setting up mutable boolean");
        assertNull(mutableBoolean);
        mutableBoolean = new MutableBoolean(false);
        assertFalse(mutableBoolean.getImmutableCopy()); //Should always be false at this point
    }

    @AfterEach
    void tearDown() {
        mutableBoolean = null;
    }

    @Test
    void testGetImmutableCopy() {
        BooleanHolder booleanHolder = new BooleanHolder(mutableBoolean);
        boolean shouldBeFalse = mutableBoolean.getImmutableCopy();
        assertFalse(shouldBeFalse);
        booleanHolder.wrappedMutableBoolean.setBoolean(true);
        boolean shouldBeTrue = mutableBoolean.getImmutableCopy();
        assertTrue(shouldBeTrue);
    }

    @Test
    void testSetBoolean() {
        MutableBoolean falseBoolean = new MutableBoolean(false);
        assertFalse(falseBoolean.getImmutableCopy());
        falseBoolean.setBoolean(true);
        assertTrue(falseBoolean.getImmutableCopy());
    }

    @Test
    void testEquals() {
        MutableBoolean otherMutableBoolean = new MutableBoolean(true);
        assertNotEquals(mutableBoolean, otherMutableBoolean);

        otherMutableBoolean = mutableBoolean;
        assertEquals(mutableBoolean, otherMutableBoolean);
    }

    static class BooleanHolder {

        final @NotNull MutableBoolean wrappedMutableBoolean;

        public BooleanHolder(@NotNull MutableBoolean wrappedMutableBoolean) {
            this.wrappedMutableBoolean = wrappedMutableBoolean;
        }

    }

}