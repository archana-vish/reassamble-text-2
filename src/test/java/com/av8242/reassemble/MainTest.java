package com.av8242.reassemble;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MainTest {

    Main main ;


    public void getFileNames() {
        this.main = new Main();
        this.main.fixLines(new String[]{"src/test/resources/alliswell.txt"});
        this.main.fixLines(new String[]{"src/test/resources/contains.txt"});
        this.main.fixLines(new String[]{"src/test/resources/same.txt"});
        this.main.fixLines(new String[]{"src/test/resources/simple.txt"});
        this.main.fixLines(new String[]{"src/test/resources/input.txt"});

    }
    
    @Test
    public void when_empty_file() {
        this.main.fixLines(new String[]{"/src/test/resources/empty.txt"});
    }

    @Test
    public void test_input() {
        this.main.fixLines(new String[]{"src/test/resources/input.txt"});
    }

    @Test
    public void when_same_word() {
        assertEquals("abc", this.main.fixLines(new String[]{"src/test/resources/same.txt"}));
    }

    @Test
    public void when_contains_word() {
        assertEquals("frodometer", this.main.fixLines(new String[]{"src/test/resources/contains.txt"}));
    }

    @Test
    public void when_simple_line() {
        assertEquals("abcdyz", this.main.fixLines(new String[]{"src/test/resources/simple.txt"}));
    }

    @Test
    public void when_latin_line1() {
        assertEquals("O draconian devil! Oh lame saint!", this.main.fixLines(new String[]{"src/test/resources/latin1.txt"}));
    }

    @Test
    public void when_all_is_well() {
        assertEquals("all is well that ends well", this.main.fixLines(new String[]{"src/test/resources/alliswell.txt"}));
    }

    @Test
    public void test_repeat1() {
        assertEquals("repeat, now repeat!", this.main.fixLines(new String[]{"src/test/resources/repeat1.txt"}));
    }

    @Test
    public void test_repeat2() {
        assertEquals("repeat, now let's repeat now!", this.main.fixLines(new String[]{"src/test/resources/repeat2.txt"}));
    }

    @Test
    public void test_clown() {
        assertEquals("He owns a clown", this.main.fixLines(new String[]{"src/test/resources/clown.txt"}));
    }

    @Test
    public void test_star() {
        assertEquals("target star cluster", this.main.fixLines(new String[]{"src/test/resources/star.txt"}));
    }

    @Test
    public void test_mnop() {
        assertEquals("mnoxabcdefghijklmnop", this.main.fixLines(new String[]{"src/test/resources/mnop.txt"}));
    }

    @Test
    public void test_latin2() {
        assertEquals(
                "Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, " +
                        "sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem.",
                this.main.fixLines(new String[]{"src/test/resources/latin2.txt"}));
    }


    public void testSubstring() {

    }


    public void testIndexOf() {
        String one = "odometer";
        String two = "Frodo";
        System.out.println(one.indexOf('o'));
    }


    public void testRegionMatches() {
        String one = "that end";
        String two = "t ends";
        System.out.println(one.regionMatches(3, two, 0, 3));
    }


    public void testSecondMatch() {
        String one = "ttat end";
        String two = "t ends";
        System.out.println(one.indexOf(two.substring(0, 2), 1));
        System.out.println(one.substring(3));
    }


    public void findMax() {
        this.main = new Main();
//        main.findMaxOverlap("all is well", "ell that en");
//        main.findMaxOverlap("abc", "abc");
//        main.findMaxOverlap("frodo", "odometer");
//        assertEquals(main.findMaxOverlap("odometer", "frodo"), "frodometer");
//        assertEquals(main.findMaxOverlap("frodo", "od"), "frodo");
//        assertEquals( main.findMaxOverlap("all is well", "ell that en"), "all is well that en");
    }
}