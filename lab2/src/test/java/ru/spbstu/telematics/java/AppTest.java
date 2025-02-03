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
}
