package com.av8242.reassemble;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

class InvalidInputException extends Throwable {
    public InvalidInputException(String message) {
        super(message);
    }
}


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

    private static String reassemble3(String line) {
        String text = line;

        try {

            if (line.isEmpty()) {
                throw new InvalidInputException("Empty input line. Please check input");
            }

            if (line.split(";").length == 0) {
                throw new InvalidInputException("Incorrect format. Excepted delimiter missing in file");
            }

            if (line.split(";").length == 1) {
                LOGGER.info("Only one word in the fragment");
                return line;
            }

            Comparator<String> compareByLength = Comparator.comparingInt(String::length);
            List<String> wordsAsList = Arrays.stream(line.split(";"))
                    .sorted(compareByLength.reversed())
                    .distinct()
                    .collect(Collectors.toList());
            LOGGER.info("Initial sorted list " + wordsAsList);


            if (wordsAsList.size() == 1) {
                return wordsAsList.get(0);
            }

            List<String> lines = wordsAsList;

            int index = 0, maxOverlap = 0, matchedIndex = 0;
            String first, second, toMatch;

            // Remove all phrases that are contained
            List<String> dups = new ArrayList<>();
            for (int i = 0; i < lines.size() - 1; i++) {
                for (int j = i + 1; j < lines.size(); j++) {
                    if (lines.get(i).contains(lines.get(j))) {

                        LOGGER.info("Adding line " + lines.get(j));
                        dups.add(lines.get(j));
                    }
                }
            }

            lines.removeAll(dups);
            if (lines.size() == 1) {
                return lines.get(0);
            }

            int i = 0;

            while (lines.size() > 1) {
                first = lines.get(0);
                maxOverlap = 0;
                matchedIndex = -1;

                for (int z = 1 ; z < lines.size(); z++) {

                    second = lines.get(z);

                    System.out.println("Comparing now " + first +  " --- " + second);

                    for (int sec_index = 0; sec_index < second.length(); sec_index++) {

                        if (second.charAt(sec_index) == ' ')
                            continue;

                        index = first.indexOf(second.charAt(sec_index));


                        if (index == 0) {
                            // Matched at the beginning
                            //LOGGER.info("Matched at the beginning" + first + ".." + second);
                            toMatch = second.substring(sec_index);
                            if (toMatch.length() > maxOverlap) {

                                if (first.regionMatches(0, toMatch, 0, toMatch.length())) {
                                    text = lines.get(i);
                                    maxOverlap = toMatch.length();
                                    matchedIndex = z;
                                    text = second.substring(0, sec_index) + first;
                                    break;
                                } else {
                                    // double check needed
                                    int new_index = first.lastIndexOf(second.charAt(sec_index));
                                    if (new_index > 0) {
                                        toMatch = first.substring(new_index);
                                        if (second.regionMatches(0, toMatch, 0, toMatch.length())) {
                                            text = lines.get(i);
                                            maxOverlap = toMatch.length();
                                            matchedIndex = z;
                                            text = first.substring(0, new_index) + second;
                                            break;
                                        }

                                    }

                                }
                            }
                        } else if (index == first.length() - 1 && sec_index == 0) {
                            // Matched at the end
                            //LOGGER.info("Matched at the end " + first + ".." + second);
                            toMatch = second;
                            if (toMatch.length() > maxOverlap) {
                                maxOverlap = toMatch.length();
                                matchedIndex = z;
                                text = first.concat(second.substring(1));
                                break;
                            }
                        } else if (index > 0 && sec_index == 0) {
                            // Matched at suffix
                            //LOGGER.info("Matched somewhere in the middle"  + first + ".." + second);
                            toMatch = first.substring(index);
                            if (second.length() > maxOverlap) {


                                if (second.regionMatches(sec_index, toMatch, 0, toMatch.length())) {
                                    text = lines.get(i);
                                    maxOverlap = toMatch.length();
                                    matchedIndex = z;
                                    text = first.substring(0, index) + second;
                                    break;
                                } else {
                                    // Something wrong.. should be another match or possibly no match
                                    int new_index = first.lastIndexOf(second.charAt(sec_index));
                                    if (new_index > 0) {
                                        toMatch = first.substring(new_index);

                                        if (second.regionMatches(sec_index, toMatch, 0, toMatch.length())) {
                                            text = lines.get(i);
                                            maxOverlap = toMatch.length();
                                            matchedIndex = z;
                                            text = first.substring(0, new_index) + second;
                                            break;
                                        }
                                    }
                                }
                            }
                        } else {
                            //TODO
                            //LOGGER.severe("Condition not handled for " + first + " and " + second + " for sec_index " + sec_index);
                        }
                    }
                }

                if (matchedIndex < 0) {
                    throw new InvalidInputException("Error parsing lines.");
                }

                System.out.println("Matched at index :: " + matchedIndex);
                lines.set(0, text);
                lines.remove(matchedIndex);
                System.out.println("Final String :: " + text);
                System.out.println("Lines to be iterated now :: " + lines);
            }


            System.out.println("Lines..." + lines);


        } catch (InvalidInputException exception) {
            LOGGER.severe("Empty input line. Please check input");
        }
        return text;
    }


    public String fixLines(String[] args) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(args[0]))) {
            List<String> collect = bufferedReader.lines()
                    .map(Main::reassemble3)
                    .collect(Collectors.toList());
            return collect.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.severe(e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.fixLines(args);
    }
}
