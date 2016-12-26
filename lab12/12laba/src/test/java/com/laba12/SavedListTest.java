package com.laba12;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.Iterator;

public class SavedListTest {

    @Test
    public void test1() throws Exception {


        EmulateDatabase<String> list = new EmulateDatabase<String>("list1");
        list.add("One");
        list.add("Two");
        list.add("Three");
        for (int i = 0; i < 100; i++) {
            list.add(1, "Hello " + i);
        }
        list.remove("Two");

        // check contents
        Assert.assertTrue(list.contains("Hello 50"));
        Assert.assertEquals(102, list.size());
        Assert.assertEquals("One", list.get(0));
        Assert.assertEquals("Hello 99", list.get(1));
        Assert.assertEquals("Hello 0", list.get(100));


        EmulateDatabase<String> list2 = new EmulateDatabase<String>("list1a");
        list2.add("One");
        list2.add("Two");
        list2.add("Three");

        list.retainAll(list2);
        Assert.assertEquals(2, list.size());
    }

    @Test
    public void test2() throws Exception {


        EmulateDatabase<String> list = new EmulateDatabase<String>("list2");
        list.add("One");
        list.add("Two");
        list.add("Three");
        for (int i = 0; i < 100; i++) {
            list.add(1, "Hello " + i);
        }
        list.remove("Two");
        list = null;


        EmulateDatabase<String> loadedList = new EmulateDatabase<String>("list2");

        Assert.assertTrue(Database.tables.containsKey("list2"));
        Assert.assertTrue(loadedList.contains("Hello 50"));
        Assert.assertEquals(102, loadedList.size());
        Assert.assertEquals("One", loadedList.get(0));
        Assert.assertEquals("Hello 99", loadedList.get(1));
        Assert.assertEquals("Hello 0", loadedList.get(100));
    }

    @Test
    public void test3() throws Exception {

        EmulateDatabase<String> list = new EmulateDatabase<String>("list3");
        list.add("One");
        list.add("Two");
        list.add("Three");
        for (int i = 0; i < 100; i++) {
            list.add(1, "Hello " + i);
        }
        list.remove("Two");


        EmulateDatabase<String> loadedList = new EmulateDatabase<String>("list3");
        for (Iterator<String> iterator = loadedList.iterator(); iterator.hasNext(); ) {
            String next = iterator.next();
            if (next.contains("8")) {
                iterator.remove();
            }
        }
        Assert.assertEquals(83, loadedList.size());

        // reload
        list.reload();
        Assert.assertFalse(list.isEmpty());
        //list = new EmulateDatabase<String>("table3");
        Assert.assertEquals(83, list.size());

        // remove file
        Database.tables.remove("list3");

        // reload
        list = new EmulateDatabase<String>("list3");
        Assert.assertTrue(list.isEmpty());
        Assert.assertEquals(0, list.size());
    }


    @Test
    public void test4() throws Exception {


        EmulateDatabase<Integer> list = new EmulateDatabase<Integer>("list4");
        list.add(1);
        list.add(2);
        list.add(3);
        list.set(0, 999);
        list = null;

        EmulateDatabase<Integer> loadedList = new EmulateDatabase<Integer>("list4");

        Assert.assertTrue(Database.tables.containsKey("list4"));
        Assert.assertEquals(Integer.valueOf(999), loadedList.get(0));
        Assert.assertEquals(Integer.valueOf(2), loadedList.get(1));
        Assert.assertEquals(Integer.valueOf(3), loadedList.get(2));
        Assert.assertEquals(3, loadedList.size());
    }
}
