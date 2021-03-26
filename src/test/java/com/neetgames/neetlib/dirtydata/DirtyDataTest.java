package com.neetgames.neetlib.dirtydata;

import com.google.common.base.Objects;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DirtyDataTest {

    Person robert;

    @BeforeEach
    void beforeEach() {
        assertNull(robert);
        robert = new Person("Robert", 31);
        assertEquals(robert.name, "Robert");
        assertEquals(robert.age, 31);
    }

    @AfterEach
    void afterEach() {
        robert = null;
    }

    @Test
    void testIsDirty() {
        DirtyData<Person> dirtyDataPerson = new DirtyData<>(robert);

        assertEquals(dirtyDataPerson.getData().name, "Robert");
        assertEquals(dirtyDataPerson.getData().age, 31);

        dirtyDataPerson.getData().age = 32; //Making it dirty by changing something about the stored value
        assertEquals(dirtyDataPerson.getData().age, 32); //Check to see if our value has changed
        assertTrue(dirtyDataPerson.isDirty()); //We should be dirty

        //Make clean
        dirtyDataPerson.resetDirty();

        //We shouldn't be dirty
        assertFalse(dirtyDataPerson.isDirty());
    }

    @Test
    void testGetData() {
        DirtyData<Person> dirtyDataPerson = new DirtyData<>(robert);
        Person robertRef = dirtyDataPerson.getData();
        assertEquals(robert, dirtyDataPerson.getData());
        assertEquals(robertRef, robert);
    }

    @Test
    void setData() {
        DirtyData<Person> dirtyDataPerson = new DirtyData<>(robert);
        Person tommy = new Person("Tommy", 33);

        assertEquals(dirtyDataPerson.getData(), robert);
        assertNotEquals(robert, tommy);

        dirtyDataPerson.setData(tommy);
        assertNotEquals(robert, dirtyDataPerson.getData());
        assertEquals(dirtyDataPerson.getData(), tommy);
    }

    @Test
    void testEquals() {
        DirtyData<Person> dirtyDataA = new DirtyData<>(robert);
        DirtyData<Person> dirtyDataB = new DirtyData<>(new Person("Riley", 22));

        assertNotEquals(dirtyDataA, dirtyDataB);

        dirtyDataB = new DirtyData<>(robert);
        assertEquals(dirtyDataA, dirtyDataB); //Should we consider them equal? Probably
    }

    static class Person {
        String name;
        int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Person person = (Person) o;
            return age == person.age && Objects.equal(name, person.name);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(name, age);
        }
    }
}