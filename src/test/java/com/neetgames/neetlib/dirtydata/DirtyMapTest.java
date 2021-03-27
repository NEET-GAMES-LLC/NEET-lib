package com.neetgames.neetlib.dirtydata;

import com.google.common.base.Objects;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class DirtyMapTest {

    static DirtyMap<HomeAddress, Creature> creatureDirectory;
    static DirtyMap<HomeAddress, Creature> unalteredDirectory;
    static Creature futan, mochi, dave; //Name of my cats <3
    static HomeAddress derpStreet, funkyRoad;

    @BeforeAll
    static void beforeAll() {
        derpStreet = new HomeAddress("123 derp street");
        funkyRoad = new HomeAddress("1337 funky road");
    }

    @BeforeEach
    void beforeEach() {
        assertNull(creatureDirectory);
        assertNull(unalteredDirectory);

        assertNotNull(derpStreet);
        assertNotNull(funkyRoad);

        futan = new Creature("Futan", 4, 2);
        mochi = new Creature("Mochi", 3, 1);
        dave = new Creature("Dave", 2, 0);

        HashMap<HomeAddress, Creature> directoryBeforeDirty = new HashMap<>();

        directoryBeforeDirty.put(derpStreet, futan);
        directoryBeforeDirty.put(funkyRoad, mochi);

        assertEquals(2, directoryBeforeDirty.size());

        creatureDirectory = new DirtyMap<>(directoryBeforeDirty); //New registry
        unalteredDirectory = new DirtyMap<>(new HashMap<>());
    }

    @AfterEach
    void afterEach() {
        creatureDirectory = null;
        unalteredDirectory = null;
    }

    @Test
    void isDirty() {
        assertFalse(unalteredDirectory.isDirty());
        assertFalse(creatureDirectory.isDirty());

        int hashBefore = creatureDirectory.unwrapMap().hashCode();

        creatureDirectory.put(derpStreet, dave);
        assertTrue(creatureDirectory.isDirty());

        assertNotEquals(hashBefore, creatureDirectory.unwrapMap().hashCode());

        creatureDirectory.resetDirty();
        assertFalse(creatureDirectory.isDirty());
    }

    @Test
    void resetDirty() {
    }

    @Test
    void setDirty() {
    }

    @Test
    void setMap() {
    }

    @Test
    void unwrapMap() {
    }

    @Test
    void get() {
    }

    @Test
    void size() {
    }

    @Test
    void isEmpty() {
    }

    @Test
    void containsKey() {
    }

    @Test
    void containsValue() {
    }

    @Test
    void put() {
    }

    @Test
    void remove() {
    }

    @Test
    void putAll() {
    }

    @Test
    void clear() {
    }

    @Test
    void keySet() {
    }

    @Test
    void values() {
    }

    @Test
    void entrySet() {
    }

    @Test
    void getOrDefault() {
    }

    @Test
    void forEach() {
    }

    @Test
    void replaceAll() {
    }

    @Test
    void putIfAbsent() {
    }

    @Test
    void testRemove() {
    }

    @Test
    void replace() {
    }

    @Test
    void testReplace() {
    }

    @Test
    void computeIfAbsent() {
    }

    @Test
    void computeIfPresent() {
    }

    @Test
    void compute() {
    }

    @Test
    void merge() {
    }

    @Test
    void testEquals() {
    }

    @Test
    void testHashCode() {
    }

    static class Creature {
        String name;
        int legs, eyes;

        public Creature(String name, int legs, int eyes) {
            this.name = name;
            this.legs = legs;
            this.eyes = eyes;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Creature creature = (Creature) o;
            return legs == creature.legs && eyes == creature.eyes && Objects.equal(name, creature.name);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(name, legs, eyes);
        }
    }

    static class HomeAddress {
        String address;

        public HomeAddress(String address) {
            this.address = address;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            HomeAddress that = (HomeAddress) o;
            return Objects.equal(address, that.address);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(address);
        }
    }
}