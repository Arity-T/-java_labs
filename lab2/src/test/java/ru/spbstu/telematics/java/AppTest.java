package ru.spbstu.telematics.java;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.TreeSet;

class MyTreeSetTests {
    MyTreeSet<Integer> myTreeSet;
    TreeSet<Integer> treeSet;

    @BeforeEach
    void setUp() {
        myTreeSet = new MyTreeSet<>();
        treeSet = new TreeSet<>();
    }

    @Test
    void testAdd() {
        assertThrows(NullPointerException.class, () -> myTreeSet.add(null));
        assertThrows(NullPointerException.class, () -> treeSet.add(null));

        assertEquals(myTreeSet.add(150), treeSet.add(150));
        assertEquals(myTreeSet.add(200), treeSet.add(200));
        assertEquals(myTreeSet.add(150), treeSet.add(150));

        assertThrows(NullPointerException.class, () -> myTreeSet.add(null));
        assertThrows(NullPointerException.class, () -> treeSet.add(null));
    }

    @Test
    void testContains() {
        assertEquals(myTreeSet.contains(15), treeSet.contains(15));
        treeSet.add(15);
        myTreeSet.add(15);
        assertEquals(myTreeSet.contains(15), treeSet.contains(15));
        assertEquals(myTreeSet.contains(50), treeSet.contains(50));
        treeSet.add(50);
        myTreeSet.add(50);
        assertEquals(myTreeSet.contains(50), treeSet.contains(50));
        
        assertThrows(NullPointerException.class, () -> myTreeSet.contains(null));
        assertThrows(NullPointerException.class, () -> treeSet.contains(null));
    }

    @Test
    void testRemove() {
        assertEquals(myTreeSet.remove(15), treeSet.remove(15));
        treeSet.add(15);
        myTreeSet.add(15);
        assertEquals(myTreeSet.remove(15), treeSet.remove(15));
        assertEquals(myTreeSet.remove(50), treeSet.remove(50));
        treeSet.add(50);
        myTreeSet.add(50);
        assertEquals(myTreeSet.remove(50), treeSet.remove(50));

        assertThrows(NullPointerException.class, () -> myTreeSet.remove(null));
        assertThrows(NullPointerException.class, () -> treeSet.remove(null));
    }

    @Test
    void testSize() {
        assertEquals(myTreeSet.size(), treeSet.size());
        treeSet.add(15);
        myTreeSet.add(15);
        assertEquals(myTreeSet.size(), treeSet.size());
        treeSet.add(50);
        myTreeSet.add(50);
        assertEquals(myTreeSet.size(), treeSet.size());
        assertEquals(myTreeSet.remove(50), treeSet.remove(50));
        assertEquals(myTreeSet.size(), treeSet.size());
    }
}
