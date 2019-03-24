package com.av8242.reassemble.Service;

import com.av8242.reassemble.Exceptions.FileFormatException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.List;

import static org.junit.Assert.*;

public class FileUtilityTest {

    FileUtility fileUtility;

    @Rule
    public ExpectedException exceptedException = ExpectedException.none();

    @Test(expected = NoSuchFileException.class)
    public void exception_thrown_when_incorrect_filename_readLinesFromPath() throws IOException, FileFormatException {
        this.fileUtility = new FileUtility();
        List<String> lines = this.fileUtility.readLinesFromPath("doesnotexist");
        exceptedException.expect(NoSuchFileException.class);
        exceptedException.expectMessage("doesnotexist");
    }

    @Test
    public void empty_list_when_incorrect_filename_readLinesFromReader()  {
        this.fileUtility = new FileUtility();
        List<String> lines = this.fileUtility.readLinesUsingBufferedReader("doesnotexist");
        assertEquals(lines.size(), 0);
    }
}