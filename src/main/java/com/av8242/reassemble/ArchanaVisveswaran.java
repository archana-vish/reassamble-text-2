package com.av8242.reassemble;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * User defined exception
 */
class InvalidInputException extends Throwable {
    public InvalidInputException(String message) {
        super(message);
    }
}

public class ArchanaVisveswaran {

    private static final Logger LOGGER = Logger.getLogger(ArchanaVisveswaran.class.getName());


    /**
     * Method to reassamble text
     * @param line input line
     * @return final merged line
     */
    public static String reassemble(String line) {
        String text = line;

        try {
            if (line.split(";").length == 0) {
                throw new InvalidInputException("Incorrect format. Excepted delimiter missing in file. Check line:" + line);
            }

            if (line.split(";").length == 1) {
                LOGGER.info("Only one word in the fragment. Check line:" + line);
                return line;
            }

            // Sort given lines by length and then store them in a list
            Comparator<String> compareByLength = Comparator.comparingInt(String::length);
            List<String> lines = Arrays.stream(line.split(";"))
                    .map(String::trim)
                    .sorted(compareByLength.reversed())
                    .distinct()
                    .collect(Collectors.toList());


            if (lines.size() == 1) {
                return lines.get(0);
            }


            int index = 0, maxOverlap = 0, matchedIndex = 0;
            String first, second, toMatch;

            while(lines.size() > 1) {

                first = lines.get(0);
                maxOverlap = 0;
                matchedIndex = -1;

                for (int nextInList = 1 ; nextInList < lines.size(); nextInList++) {

                    second = lines.get(nextInList).trim();

                    // Remove contained lines
                    if (first.contains(second)) {
                        lines.remove(nextInList);
                        continue;
                    }

                    for (int sec_index = 0; sec_index < second.length(); sec_index++) {

                        if (second.charAt(sec_index) == ' ')
                            continue;

                        // Find where the character from second string matches with the first
                        // Traverse right to left for first string
                        // Traverse left to right for the second string
                        index = first.indexOf(second.charAt(sec_index));

                        if (index == 0) {
                            // Matched at the beginning
                            toMatch = second.substring(sec_index);
                            if (toMatch.length() > maxOverlap) {
                                if (first.regionMatches(0, toMatch, 0, toMatch.length())) {
                                    text = lines.get(0);
                                    maxOverlap = toMatch.length();
                                    matchedIndex = nextInList;
                                    text = second.substring(0, sec_index) + first;
                                    break;
                                } else {
                                    // double check needed
                                    int new_index = first.lastIndexOf(second.charAt(sec_index));
                                    if (new_index > 0) {
                                        toMatch = first.substring(new_index);
                                        if (second.regionMatches(0, toMatch, 0, toMatch.length())) {
                                            text = lines.get(0);
                                            maxOverlap = toMatch.length();
                                            matchedIndex = nextInList;
                                            text = first.substring(0, new_index) + second;
                                            break;
                                        }
                                    }
                                }
                            }
                        } else if (index == first.length() - 1 && sec_index == 0) {
                            // Matched at the end
                            toMatch = second;
                            if (toMatch.length() > maxOverlap) {
                                maxOverlap = toMatch.length();
                                matchedIndex = nextInList;
                                text = first.concat(second.substring(1));
                                break;
                            }
                        } else if (index > 0 && sec_index == 0) {
                            // Matched at suffix
                            toMatch = first.substring(index);
                            if (second.length() > maxOverlap) {


                                if (second.regionMatches(sec_index, toMatch, 0, toMatch.length())) {
                                    text = lines.get(0);
                                    maxOverlap = toMatch.length();
                                    matchedIndex = nextInList;
                                    text = first.substring(0, index) + second;
                                    break;
                                } else {
                                    int new_index = first.lastIndexOf(second.charAt(sec_index));
                                    if (new_index > 0) {
                                        toMatch = first.substring(new_index);

                                        if (second.regionMatches(sec_index, toMatch, 0, toMatch.length())) {
                                            text = lines.get(0);
                                            maxOverlap = toMatch.length();
                                            matchedIndex = nextInList;
                                            text = first.substring(0, new_index) + second;
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                if (lines.size() == 1) {
                    text = lines.get(0);
                    break;
                }

                if (matchedIndex < 0) {
                    String temp = first;
                    lines.set(0, lines.get(1));
                    lines.set(1, temp);
                } else {
                    lines.set(0, text);
                    lines.remove(matchedIndex);
                }
            }

        } catch (InvalidInputException exception) {
            LOGGER.severe(exception.getMessage());
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
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(args[0]))) {
            bufferedReader.lines()
                    .map(ArchanaVisveswaran::reassemble)
                    .forEach(System.out::println);
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
        }
    }
}
