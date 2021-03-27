package com.neetgames.neetlib.dirtydata;

import com.google.common.base.Objects;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

//TODO: Test other kinds of maps? Not even sure if necessary
//TODO: Implement all tests
class DirtyMapTest {

    static DirtyMap<HomeAddress, Creature> creatureDirectory;
    static DirtyMap<HomeAddress, Creature> unalteredDirectory;
    static DirtyMap<HomeAddress, Creature> modifiedDirtyMap; //Should be dirty before each test
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

        modifiedDirtyMap = new DirtyMap<>(new HashMap<>());
        modifiedDirtyMap.put(new HomeAddress("Unique Street"), new Creature("Unique Creature", 7, 8));
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
        assertTrue(modifiedDirtyMap.isDirty());

        int hashBefore = creatureDirectory.unwrapMap().hashCode();

        creatureDirectory.put(derpStreet, dave);
        assertTrue(creatureDirectory.isDirty());

        assertNotEquals(hashBefore, creatureDirectory.unwrapMap().hashCode());

        creatureDirectory.resetDirty();
        assertFalse(creatureDirectory.isDirty());
    }

    @Test
    void resetDirty() {
        assertTrue(modifiedDirtyMap.isDirty());
        modifiedDirtyMap.resetDirty();
        assertFalse(modifiedDirtyMap.isDirty());
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