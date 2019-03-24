package com.av8242.reassemble;

import org.junit.Test;

import static org.junit.Assert.*;

public class MainTest {

    Main main;


    public void getFileNames() {
        this.main = new Main();
        this.main.fixLines(new String[]{"src/main/resources/alliswell.txt"});
        this.main.fixLines(new String[]{"src/main/resources/contains.txt"});
        this.main.fixLines(new String[]{"src/main/resources/same.txt"});
        this.main.fixLines(new String[]{"src/main/resources/simple.txt"});
        this.main.fixLines(new String[]{"src/main/resources/input.txt"});

    }

    @Test
    public void when_same_word() {
        this.main = new Main();
        assertEquals("abc", this.main.fixLines(new String[]{"src/main/resources/same.txt"}));
    }

    @Test
    public void when_contains_word() {
        this.main = new Main();
        assertEquals("frodo", this.main.fixLines(new String[]{"src/main/resources/contains.txt"}));
    }

    @Test
    public void when_simple_line() {
        this.main = new Main();
        assertEquals("abcdyz", this.main.fixLines(new String[]{"src/main/resources/simple.txt"}));
    }

    @Test
    public void when_latin_line1() {
        this.main = new Main();
        assertEquals("O draconian devil! Oh lame saint!", this.main.fixLines(new String[]{"src/main/resources/latin1.txt"}));
    }

    @Test
    public void when_all_is_well() {
        this.main = new Main();
        assertEquals("all is well that ends well", this.main.fixLines(new String[]{"src/main/resources/alliswell.txt"}));
    }

    @Test
    public void testSubstring() {

    }

    @Test
    public void testIndexOf() {
        String one = "odometer";
        String two = "Frodo";
        System.out.println(one.indexOf('o'));
    }

    @Test
    public void testRegionMatches() {
        String one = "odometer";
        String two = "f";
        System.out.println(one.regionMatches(0, two, 0, 3));
    }


    public void findMax() {
        this.main = new Main();
//        Main.findMaxOverlap("all is well", "ell that en");
//        Main.findMaxOverlap("abc", "abc");
//        Main.findMaxOverlap("frodo", "odometer");
//        assertEquals(Main.findMaxOverlap("odometer", "frodo"), "frodometer");
//        assertEquals(Main.findMaxOverlap("frodo", "od"), "frodo");
//        assertEquals( Main.findMaxOverlap("all is well", "ell that en"), "all is well that en");
    }
}