/**
 * Class: COMP 2071
 * Assignment: Lab 4
 * Due Date: 3/17/16
 * Group #: 21
 * Group Members:   Andrew Corp
 *                  Eddie Penta
 *                  Jacob Ollila
 */

import static org.junit.Assert.*;

public class ListTest {

    @org.junit.Test
    public void testGetCurrentSize() throws Exception {
        List<String> test = new List<>();
        test.add("one");
        test.add("two");
        test.add("three");

        assertEquals(test.getCurrentSize(), 3);

        test.add("four");
        test.add("five");
        assertEquals(test.getCurrentSize(), 5);
    }

    @org.junit.Test
    public void testIsEmpty() throws Exception {
        List<String> test = new List<>();
        assertEquals(test.isEmpty(), true);

        test.add("one");
        test.add("two");
        test.add("three");
        assertEquals(test.isEmpty(), false);
    }

    @org.junit.Test
    public void testIsFull() throws Exception {
        List<String> test = new List<>();
        assertEquals(test.isFull(), false);

        test.add("one");
        test.add("two");
        test.add("three");
        assertEquals(test.isFull(), false);
    }

    @org.junit.Test
    public void testAdd() throws Exception {
        List<String> test = new List<>();
        test.add("one");
        test.add("two");
        test.add("three");

        assertEquals(test.getEntry(0), "one");
        assertEquals(test.getEntry(1), "two");
        assertEquals(test.getEntry(2), "three");
    }

    @org.junit.Test
    public void testAdd1() throws Exception {
        List<String> test = new List<>();
        test.add("one");
        test.add("two");
        test.add("three");

        assertEquals(test.getEntry(0), "one");
        assertEquals(test.getEntry(1), "two");
        assertEquals(test.getEntry(2), "three");

        test.add(1, "four");
        assertEquals(test.getEntry(0), "one");
        assertEquals(test.getEntry(1), "four");
        assertEquals(test.getEntry(2), "two");
        assertEquals(test.getEntry(3), "three");
    }

    @org.junit.Test
    public void testRemove() throws Exception {
        List<String> test = new List<>();
        test.add("one");
        test.add("two");
        test.add("three");

        assertEquals(test.getEntry(0), "one");
        assertEquals(test.getEntry(1), "two");
        assertEquals(test.getEntry(2), "three");

        test.remove(0);

        assertEquals(test.getEntry(0), "two");
        assertEquals(test.getEntry(1), "three");

        test.remove(1);
        assertEquals(test.getEntry(0), "two");
    }

    @org.junit.Test
    public void testRemove1() throws Exception {
        List<String> test = new List<>();
        test.add("one");
        test.add("two");
        test.add("three");

        assertEquals(test.getEntry(0), "one");
        assertEquals(test.getEntry(1), "two");
        assertEquals(test.getEntry(2), "three");

        test.remove("one");

        assertEquals(test.getEntry(0), "two");
        assertEquals(test.getEntry(1), "three");

        test.remove("two");
        assertEquals(test.getEntry(0), "three");
    }

    @org.junit.Test
    public void testClear() throws Exception {
        List<String> test = new List<>();
        test.add("one");
        test.add("two");
        test.add("three");

        assertEquals(test.getEntry(0), "one");
        assertEquals(test.getEntry(1), "two");
        assertEquals(test.getEntry(2), "three");

        test.clear();

        assertEquals(test.getCurrentSize(), 0);
        assertEquals(test.isEmpty(), true);
    }

    @org.junit.Test
    public void testReplace() throws Exception {
        List<String> test = new List<>();
        test.add("one");
        test.add("two");
        test.add("three");

        assertEquals(test.getEntry(0), "one");
        assertEquals(test.getEntry(1), "two");
        assertEquals(test.getEntry(2), "three");

        test.replace(0, "four");
        assertEquals(test.getEntry(0), "four");

        test.replace(1, "five");
        assertEquals(test.getEntry(1), "five");

        test.replace(2, "six");
        assertEquals(test.getEntry(2), "six");
    }

    @org.junit.Test
    public void testGetEntry() throws Exception {
        List<String> test = new List<>();
        test.add("one");
        test.add("two");
        test.add("three");

        assertEquals(test.getEntry(0), "one");
        assertEquals(test.getEntry(1), "two");
        assertEquals(test.getEntry(2), "three");
    }

    @org.junit.Test
    public void testContains() throws Exception {
        List<String> test = new List<>();
        test.add("one");
        test.add("two");
        test.add("three");

        assertEquals(test.contains("one"), true);
        assertEquals(test.contains("two"), true);
        assertEquals(test.contains("four"), false);
    }

    @org.junit.Test
    public void testGetLength() throws Exception {
        List<String> test = new List<>();
        test.add("one");
        test.add("two");
        test.add("three");

        assertEquals(test.getCurrentSize(), 3);
    }

    @org.junit.Test
    public void sortTest() throws Exception {
        List<Integer> test = new List<>();
        test.add(3);
        test.add(1);
        test.add(8);
        test.add(5);

        test.sort();

        assertEquals(test.getEntry(0), new Integer(1));
        assertEquals(test.getEntry(1), new Integer(3));
        assertEquals(test.getEntry(2), new Integer(5));
        assertEquals(test.getEntry(3), new Integer(8));
    }
}