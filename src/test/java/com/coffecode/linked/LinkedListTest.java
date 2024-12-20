package com.coffecode.linked;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.coffecode.data.Data;
import com.coffecode.node.Node;

public class LinkedListTest {
    private LinkedList linkedList;

    @BeforeEach
    public void setUp() {
        linkedList = new LinkedList();
    }

    @Test
    public void testIsiData() {
        linkedList.isiData("123", "Alice", 90);
        assertTrue(linkedList.findData("Alice"));
    }

    @Test
    public void testFindData() {
        linkedList.isiData("123", "Alice", 90);
        linkedList.isiData("124", "Bob", 85);
        assertTrue(linkedList.findData("Alice"));
        assertTrue(linkedList.findData("Bob"));
        assertFalse(linkedList.findData("Charlie"));
    }

    @Test
    public void testHapusData() {
        linkedList.isiData("123", "Alice", 90);
        linkedList.isiData("124", "Bob", 85);
        assertTrue(linkedList.hapusData("Alice"));
        assertFalse(linkedList.findData("Alice"));
        assertTrue(linkedList.findData("Bob"));
        assertFalse(linkedList.hapusData("Charlie"));
    }

    @Test
    public void testTampilkan() {
        linkedList.isiData("123", "Alice", 90);
        linkedList.isiData("124", "Bob", 85);
        linkedList.tampilkan(); // This will print the data to the console
    }
}