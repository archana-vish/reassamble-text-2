package com.av8242.reassemble;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

//class InvalidInputException extends Throwable {
//    public InvalidInputException(String message) {
//        super(message);
//    }
//}


public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());


    public static String reassemble(String line) {
        Comparator<String> compareByLength = Comparator.comparingInt(String::length);
        System.out.println("Analysing line :: " + line);
        List<String> words = Arrays.asList(line.split(";"));
        List<String> lines = words;
        words.sort(compareByLength.reversed());

        String first, next;
        int maxOverlap;

        while (lines.size() > 1) {
            first = lines.get(0);
            for (int i = 1; i < lines.size(); i++) {
                findMaxOverlap(first, lines.get(i));
            }
        }

        System.out.println("After sort :: " + words);
        return null;
    }

    public static String findMaxOverlap(String one, String two) {
        int index, overlap = 0;
        String overlapped;
        String finalString = "";

        if (one.contains(two)) {
            System.out.println("Complete match");
            finalString = one;
        } else {
            for (int i = 0; i < two.length(); i++) {
                System.out.println(two.charAt(i));
                System.out.println(one.indexOf(two.charAt(i)) + " " + one.length());
                index = one.indexOf(two.charAt(i));

                if (index == 0) {
                    LOGGER.info("Matched at the prefix " + index);
                    overlapped = two.substring(i);
                    LOGGER.info("Check if overlap is " + overlapped);
                    if (one.regionMatches(index, two, i, two.length() - i)) {
                        overlap = two.substring(i).length();
                        finalString = two.substring(0, i) + one;
                        break;
                    }
                } else if (index == one.length() - 1) {
                    if (i > 0) {
                        // Suffix and prefix match
                    }
                    LOGGER.info("Matched at the suffix " + index);
                } else if (index > 0) {
                    LOGGER.info("Matched at " + index);
                    overlapped = two.substring(i);
                    LOGGER.info("Check if overlap is " + overlapped);
                    System.out.println(one.regionMatches(index, two, i, two.length() - i));
                }
            }
        }

        return finalString;
    }

    /**
     * Assemble the fragments in the correct order.
     *
     * @param line String containing the fragments of the document.
     * @return the line in the correct order.
     */
    private static String reassemble2(String line) {

        // Each line contains text fragments separated by a semicolon.
        String[] fragments = line.split(";");
        List<String> list = new ArrayList<String>(Arrays.asList(fragments));

        // Sort the fragments by size
        Collections.sort(list, (o1, o2) -> o2.length() - o1.length());

        System.out.println(list);

        // Pick up the first fragment
        String text = list.get(0);
        list.remove(0);

        // Start the algorithm
        for (int i = list.size() - 1; i >= 0; i--) {
            int max = 0;
            int idx = 0;
            int match = 0;
            int m = 0;
            int n = 0;
            for (int j = list.size() - 1; j >= 0; j--) {
                String findMe = list.get(j);
                m = text.length();
                n = findMe.length();
                for (int k = 1 - findMe.length(); k < text.length(); k++) {
                    if (k < 0) { // Prefix
                        int l = n + k;
                        if (text.regionMatches(0, findMe, -k, l)) {
                            if (l > max) {
                                idx = k;
                                max = l;
                                match = j;
                            }
                        }
                    } else { // Suffix
                        int l = k + n <= m ? n : m - k;
                        if (text.regionMatches(k, findMe, 0, l)) {
                            if (l > max) {
                                idx = k;
                                max = l;
                                match = j;
                            }
                        }
                    }
                }
            }

            if (idx < 0) { // Prefix
                text = list.get(match).substring(0, -idx) + text;
            } else if (idx > m - list.get(match).length()) { // Suffix
                text = text + list.get(match).substring(m - idx);
            }
            list.remove(match);
        }
        return text;
    }




    public static String fixLines(String[] args) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(args[0]))) {
            List<String> lines = bufferedReader.lines()
                    .map(ArchanaVisveswaran::reassemble)
                    .peek(System.out::println)
                    .collect(Collectors.toList());

            if (lines.isEmpty()) {
                throw new InvalidInputException("Empty input line. Please check input");
            }
            return lines.get(0);
        } catch (InvalidInputException|Exception e) {
            LOGGER.severe(e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.fixLines(args);
    }
}
