package com.av8242.reassemble.Service;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class SetsTest {

    Sets sets;

    @Test
    public void testUnion() {
        this.sets = new Sets();
        Set<Integer> union = this.sets.findUnion();
        Set<Integer> finalSet = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 8, 10));
        assertEquals(union, finalSet);
    }

    @Test
    public void testIntersection() {
        this.sets = new Sets();
        Set<Integer> intersection = this.sets.findIntersection();
        Set<Integer> finalSet = new HashSet<>(Arrays.asList(2, 4));
        assertEquals(intersection, finalSet);
    }

    @Test
    public void testDiff() {
        this.sets = new Sets();
        Set<Integer> intersection = this.sets.findDifference();
        Set<Integer> finalSet = new HashSet<>(Arrays.asList(6, 8, 10));
        assertEquals(intersection, finalSet);
    }

    @Test
    public void testComplement() {
        this.sets = new Sets();
        Set<Integer> complement = this.sets.complement();
        Set<Integer> finalSet = new HashSet<>(Arrays.asList(1, 3, 5));
        assertEquals(complement, finalSet);
    }

}