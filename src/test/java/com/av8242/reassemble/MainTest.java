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
    public void test_input() {
        this.main = new Main();
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
        assertEquals("frodometer", this.main.fixLines(new String[]{"src/main/resources/contains.txt"}));
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
    public void test_repeat1() {
        this.main = new Main();
        assertEquals("repeat, now repeat!", this.main.fixLines(new String[]{"src/main/resources/repeat1.txt"}));
    }

    @Test
    public void test_repeat2() {
        this.main = new Main();
        assertEquals("repeat, now let's repeat now!", this.main.fixLines(new String[]{"src/main/resources/repeat2.txt"}));
    }

    @Test
    public void test_clown() {
        this.main = new Main();
        assertEquals("He owns a clown", this.main.fixLines(new String[]{"src/main/resources/clown.txt"}));
    }

    @Test
    public void test_star() {
        this.main = new Main();
        assertEquals("target star cluster", this.main.fixLines(new String[]{"src/main/resources/star.txt"}));
    }

    @Test
    public void test_mnop() {
        this.main = new Main();
        assertEquals("mnoxabcdefghijklmnop", this.main.fixLines(new String[]{"src/main/resources/mnop.txt"}));
    }

    @Test
    public void test_latin2() {
        this.main = new Main();
        assertEquals("repeat, now repeat!", this.main.fixLines(new String[]{"src/main/resources/latin2.txt"}));
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
        String one = "that end";
        String two = "t ends";
        System.out.println(one.regionMatches(3, two, 0, 3));
    }

    @Test
    public void testSecondMatch() {
        String one = "ttat end";
        String two = "t ends";
        System.out.println(one.indexOf(two.substring(0, 2), 1));
        System.out.println(one.substring(3));
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