package com.av8242.reassemble.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Sets {

    private Set<Integer> first = new HashSet<>();
    private Set<Integer> second = new HashSet<>();

    private void initialise() {
        first.addAll(Arrays.asList(2,4,6,8,10));
        second.addAll(Arrays.asList(1,2, 3, 4, 5));
    }

    public Set<Integer> findUnion() {
        //A u B = A + B
        initialise();
        first.addAll(second);
        System.out.println(first);
        return first;
    }

    public Set<Integer> findIntersection() {
        //A n B = Common in A and B
        initialise();
        Set<Integer> intersection = new HashSet<>();
        intersection.addAll(first);
        intersection.retainAll(second);
        System.out.println(intersection);
        return intersection;
    }

    public Set<Integer> findDifference() {
        // A-B = In A but not in B
        initialise();
        Set<Integer> diff = new HashSet<>();
        first.removeIf(a -> second.contains(a));
        System.out.println(first);
        diff.addAll(first);
        return diff;

        // Efficient way diff.addAll(a).removeAll(b)
    }

    public Set<Integer> complement() {
        initialise();
        Set<Integer> diff = new HashSet<>();
        diff.addAll(second);
        diff.removeAll(first);
        System.out.println(diff);
        return diff;
    }


}
