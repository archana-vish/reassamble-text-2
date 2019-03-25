package com.av8242.reassemble.Service;

public class Reassembler {

    public static void main(String[] args) {

        String first = "that ends";
        String second = "t en";
        String overlap = "";
        int maxOverlap = 0;
        int mtchIndex = -1;
        String text = "";

        for (int sec_index = 0; sec_index < second.length(); sec_index++) {
            if (first.regionMatches(0, second, sec_index, second.length() - sec_index)) {
                if (second.substring(sec_index).length() > maxOverlap) {
                    maxOverlap = second.substring(sec_index).length();
                    overlap = second.substring(sec_index);
                    mtchIndex = sec_index;
                }
            }
        }

        if (mtchIndex == -1) {

            String temp = first;
            first = second;
            second = temp;

            for (int sec_index = 0; sec_index < second.length(); sec_index++) {
                if (first.regionMatches(0, second, sec_index, second.length() - sec_index)) {
                    if (second.substring(sec_index).length() > maxOverlap) {
                        maxOverlap = second.substring(sec_index).length();
                        overlap = second.substring(sec_index);
                        mtchIndex = sec_index;
                    }
                }
            }
        }
        System.out.println(overlap + ".." + mtchIndex);

         if (mtchIndex == second.length() - 1) {

        } else if (mtchIndex >= 0) {
            text = second.substring(0, mtchIndex) + first;
        }

        System.out.println(text);
    }
}
