package com.av8242.reassemble.Service;

import com.av8242.reassemble.Exceptions.FileFormatException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileUtility {

    private static final Logger LOGGER = Logger.getLogger(FileUtility.class.getName());

    public List<String> readLinesFromPath(String filename) throws IOException, FileFormatException {
        List<String> lines = Files.readAllLines(Paths.get(filename));
        if (lines.isEmpty()) {
            throw new FileFormatException("Empty File");
        } else {
            return lines;
        }
    }

    public List<String> readLinesUsingBufferedReader(String filename) {
        List<String> lines = new ArrayList<>();
       try(BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))) {
            lines = bufferedReader.lines().collect(Collectors.toList());
       } catch(Exception e) {

       }
       return  lines;
    }
}
