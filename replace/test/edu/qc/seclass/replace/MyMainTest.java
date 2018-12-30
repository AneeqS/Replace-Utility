package edu.qc.seclass.replace;

import static org.junit.Assert.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

/**
 * Created by AneeqShah on 12/7/18.
 */
public class MyMainTest {

    private ByteArrayOutputStream outStream;
    private ByteArrayOutputStream errStream;
    private PrintStream outOrig;
    private PrintStream errOrig;
    private Charset charset = StandardCharsets.UTF_8;

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Before
    public void setUp() throws Exception {
        outStream = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(outStream);
        errStream = new ByteArrayOutputStream();
        PrintStream err = new PrintStream(errStream);
        outOrig = System.out;
        errOrig = System.err;
        System.setOut(out);
        System.setErr(err);
    }

    @After
    public void tearDown() throws Exception {
        System.setOut(outOrig);
        System.setErr(errOrig);
    }

    private File createTmpFile() throws IOException {
        File tmpfile = temporaryFolder.newFile();
        tmpfile.deleteOnExit();
        return tmpfile;
    }

    private File createInputFile1() throws Exception {
        File file1 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);

        fileWriter.write("Howdy Bill,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy bill\" again!");

        fileWriter.close();
        return file1;
    }

    private File createInputFile2() throws Exception {
        File file1 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);

        fileWriter.write("Howdy Bill,\n" +
                "This is another test file for the replace utility\n" +
                "that contains a list:\n" +
                "-a) Item 1\n" +
                "-b) Item 2\n" +
                "...\n" +
                "and says \"howdy Bill\" twice");

        fileWriter.close();
        return file1;
    }

    private File createInputFile3() throws Exception {
        File file1 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);

        fileWriter.write("Howdy Bill, have you learned your abc and 123?\n" +
                "It is important to know your abc and 123," +
                "so you should study it\n" +
                "and then repeat with me: abc and 123");

        fileWriter.close();
        return file1;
    }


    private File createInputFile4() throws Exception {
        File file1 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);

        fileWriter.write("my name is aneeq");

        fileWriter.close();
        return file1;
    }

    private File createInputFile5() throws Exception {
        File file1 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);

        fileWriter.write("my name is aneeq\n"+
                "I am taking cs 370\n"+
                "With name abc and def");

        fileWriter.close();
        return file1;
    }

    private File createInputFile6() throws Exception {
        File file1 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);

        fileWriter.write("my name is aneeq or ANEEQ\n"+
                "I am taking cs 370\n"+
                "you can call me Aneeq");

        fileWriter.close();
        return file1;
    }

    private String getFileContent(String filename) {
        String content = null;
        try {
            content = new String(Files.readAllBytes(Paths.get(filename)), charset);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    //Start of Actual Test Cases.

    //Implementation of Test Frame #6
    //Number of Files provided :  Does not Exist

    @Test
    public void mainTest0() throws Exception {

        File file1 = createInputFile1();
        String args[] = { "Bill", "aneeq", "bill", "mike", "--", file1.getPath()};
        Main.main(args);

        String expected1 ="Howdy aneeq,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy mike\" again!";

        String actual1 = getFileContent(file1.getPath());
        assertEquals("The files differ!", expected1, actual1);
        assertFalse(Files.exists(Paths.get(file1.getPath() + ".bck")));
    }

    //Implementation of Test Frame #3
    //Number of Files provided :  None

    @Test
    public void mainTest1()  {

        String args[] = {"-i", "howdy", "Hello", "--"};
        Main.main(args);
        assertEquals("Usage: Replace [-b] [-f] [-l] [-i] <from> <to> -- <filename> [<filename>]*", errStream.toString().trim());
    }

    //Implementation of Test Frame #7
    //Length of the from string :  Zero
    @Test
    public void mainTest2() throws Exception {

        File file1 = createInputFile1();
        String args[] = {"-i", "", "hello", "--", file1.getPath()};
        Main.main(args);
        assertEquals("Usage: Replace [-b] [-f] [-l] [-i] <from> <to> -- <filename> [<filename>]*", errStream.toString().trim());
    }

    //Implementation of Test Frame #8
    //Length of the from string :  One
    @Test
    public void mainTest3() throws Exception {

        File file1 = createInputFile1();
        String args[] = {"-i", "", "hello", "--", file1.getPath()};
        Main.main(args);
        assertEquals("Usage: Replace [-b] [-f] [-l] [-i] <from> <to> -- <filename> [<filename>]*", errStream.toString().trim());
    }

    //Implementation of Test Frame #9
    //Length of the to string :  Zero
    @Test
    public void mainTest4() throws Exception {

        File file1 = createInputFile1();
        String args[] = {"-i", "", "hello", "--", file1.getPath()};
        Main.main(args);
        assertEquals("Usage: Replace [-b] [-f] [-l] [-i] <from> <to> -- <filename> [<filename>]*", errStream.toString().trim());
    }

    //Implementation of Test Frame #10
    //Length of the to string :  One
    @Test
    public void mainTest5() throws Exception {

        File file1 = createInputFile1();
        String args[] = {"-i", "", "hello", "--", file1.getPath()};
        Main.main(args);
        assertEquals("Usage: Replace [-b] [-f] [-l] [-i] <from> <to> -- <filename> [<filename>]*", errStream.toString().trim());
    }

    //Implementation of Test Frame #11
    //Number of Arguments and Options :  Zero
    @Test
    public void mainTest6() throws Exception {

        File file1 = createInputFile1();
        String args[] = {"", file1.getPath()};
        Main.main(args);
        assertEquals("Usage: Replace [-b] [-f] [-l] [-i] <from> <to> -- <filename> [<filename>]*", errStream.toString().trim());
    }

    //Implementation of Test Frame #2
    //Presence of a file corresponding to the name :  Not present
    @Test
    public void mainTest7() throws Exception {

        File file1 = createInputFile1();
        file1.delete();
        String args[] = {"Bill", "John", "--", file1.getPath()};
        Main.main(args);
        assertEquals("File " + file1.getName() + " not found", errStream.toString().trim());
    }

    //Newly Created Test
    //Purpose: Test if the length of the from string, is longer than file.
    @Test
    public void mainTest8() throws Exception {

        File file1 = createInputFile4();
        String args[] = {"my name is aneeq and some other text", "ABC", "--",file1.getPath()};
        Main.main(args);
        String actual1 = getFileContent(file1.getPath());
        String expected1="my name is aneeq";
        assertEquals("The files differ!", expected1, actual1);
        assertFalse(Files.exists(Paths.get(file1.getPath() + ".bck")));
    }

    //Newly Created Test Case
    //Purpose: Test if the length of the to string, is longer than file.
    @Test
    public void mainTest9() throws Exception {

        File file1 = createInputFile4();
        String args[] = {"my", "ABC some other text", "--",file1.getPath()};
        Main.main(args);
        String actual1 = getFileContent(file1.getPath());
        String expected1="ABC some other text name is aneeq";
        assertEquals("The files differ!", expected1, actual1);
        assertFalse(Files.exists(Paths.get(file1.getPath() + ".bck")));
    }

    //Newly Created Test Case
    //Purpose: Test if the position of the from string is in the first line.
    @Test
    public void mainTest10() throws Exception {

        File file1 = createInputFile5();
        String args[] = { "-f","name", "nick-name", "--", file1.getPath()};
        Main.main(args);

        String expected1 = "my nick-name is aneeq\n"+
                "I am taking cs 370\n"+
                "With name abc and def";

        String actual1 = getFileContent(file1.getPath());
        assertEquals("The files differ!", expected1, actual1);
        assertFalse(Files.exists(Paths.get(file1.getPath() + ".bck")));
    }

    //Newly Created Test Case
    //Purpose: Test if the position of the from string is in the last line.
    @Test
    public void mainTest11() throws Exception {

        File file1 = createInputFile5();
        String args[] = {"-l", "name", "nick-name", "--", file1.getPath()};
        Main.main(args);

        String expected1 = "my name is aneeq\n"+
                "I am taking cs 370\n"+
                "With nick-name abc and def";

        String actual1 = getFileContent(file1.getPath());
        assertEquals("The files differ!", expected1, actual1);
        assertFalse(Files.exists(Paths.get(file1.getPath() + ".bck")));
    }

    //Newly Created Test Case
    //Purpose: Test if the position of the from string is in the middle line.
    @Test
    public void mainTest12() throws Exception {

        File file1 = createInputFile5();
        String args[] = { "abc", "ghi", "--", file1.getPath()};
        Main.main(args);

        String expected1 = "my name is aneeq\n"+
                "I am taking cs 370\n"+
                "With name ghi and def";

        String actual1 = getFileContent(file1.getPath());
        assertEquals("The files differ!", expected1, actual1);
        assertFalse(Files.exists(Paths.get(file1.getPath() + ".bck")));
    }

    //Newly Created Test Case
    //Purpose: Test for when there is no occurrence of the from string.
    @Test
    public void mainTest13() throws Exception {

        File file1 = createInputFile1();
        String args[] = { "abc", "def", "--", file1.getPath()};
        Main.main(args);

        String expected1 = "Howdy Bill,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy bill\" again!";

        String actual1 = getFileContent(file1.getPath());
        assertEquals("The files differ!", expected1, actual1);
        assertFalse(Files.exists(Paths.get(file1.getPath() + ".bck")));
    }

    //Newly Created Test Case
    //Purpose Test for Backup -b
    @Test
    public void mainTest14() throws Exception {

        File file1 = createInputFile1();
        File file2 = createInputFile2();
        String args[] = {"-b", "Bill", "William", "--", file1.getPath(), file2.getPath()};
        Main.main(args);

        String expected1 = "Howdy William,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy bill\" again!";
        String expected2 = "Howdy William,\n" +
                "This is another test file for the replace utility\n" +
                "that contains a list:\n" +
                "-a) Item 1\n" +
                "-b) Item 2\n" +
                "...\n" +
                "and says \"howdy William\" twice";

        String actual1 = getFileContent(file1.getPath());
        String actual2 = getFileContent(file2.getPath());

        assertEquals("The files differ!", expected1, actual1);
        assertEquals("The files differ!", expected2, actual2);

        assertTrue(Files.exists(Paths.get(file1.getPath() + ".bck")));
        assertTrue(Files.exists(Paths.get(file2.getPath() + ".bck")));
    }

    //Newly Created Test Case
    //Purpose Test for Backup and From -b -f
    @Test
    public void mainTest15() throws Exception {

        File file1 = createInputFile1();
        File file2 = createInputFile2();
        String args[] = {"-b", "-f", "Bill", "William", "--", file1.getPath(), file2.getPath()};
        Main.main(args);

        String expected1 = "Howdy William,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy bill\" again!";

        String expected2 = "Howdy William,\n" +
                "This is another test file for the replace utility\n" +
                "that contains a list:\n" +
                "-a) Item 1\n" +
                "-b) Item 2\n" +
                "...\n" +
                "and says \"howdy Bill\" twice";


        String actual1 = getFileContent(file1.getPath());
        String actual2 = getFileContent(file2.getPath());

        assertEquals("The files differ!", expected1, actual1);
        assertEquals("The files differ!", expected2, actual2);

        assertTrue(Files.exists(Paths.get(file1.getPath() + ".bck")));
        assertTrue(Files.exists(Paths.get(file2.getPath() + ".bck")));
    }

    //Newly Created Test Case
    //Purpose Test for Backup -b -l
    @Test
    public void mainTest16() throws Exception {

        File file1 = createInputFile2();
        File file2 = createInputFile3();
        String args[] = {"-b", "-l", "Bill", "William", "--", file1.getPath(), file2.getPath()};
        Main.main(args);

        String expected2 = "Howdy Bill,\n" +
                "This is another test file for the replace utility\n" +
                "that contains a list:\n" +
                "-a) Item 1\n" +
                "-b) Item 2\n" +
                "...\n" +
                "and says \"howdy William\" twice";

        String expected3 = "Howdy William, have you learned your abc and 123?\n" +
                "It is important to know your abc and 123," +
                "so you should study it\n" +
                "and then repeat with me: abc and 123";
        
        String actual2 = getFileContent(file1.getPath());
        String actual3 = getFileContent(file2.getPath());

        assertEquals("The files differ!", expected2, actual2);
        assertEquals("The files differ!", expected3, actual3);

        assertTrue(Files.exists(Paths.get(file1.getPath() + ".bck")));
        assertTrue(Files.exists(Paths.get(file2.getPath() + ".bck")));
    }


    //Newly Created Test Case
    //Purpose: Test for when Options are in different order. -i -l -f -b
    @Test
    public void mainTest18() throws Exception {

        File file1 = createInputFile1();
        File file2 = createInputFile2();
        String args[] = {"-i", "-l","-f", "-b", "Bill", "William", "--", file1.getPath(), file2.getPath()};
        Main.main(args);

        String expected1 = "Howdy William,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy William\" again!";

        String expected2= "Howdy William,\n" +
                "This is another test file for the replace utility\n" +
                "that contains a list:\n" +
                "-a) Item 1\n" +
                "-b) Item 2\n" +
                "...\n" +
                "and says \"howdy William\" twice";

        String actual1 = getFileContent(file1.getPath());
        String actual2 = getFileContent(file2.getPath());

        assertEquals("The files differ!", expected1, actual1);
        assertEquals("The files differ!", expected2, actual2);

        assertTrue(Files.exists(Paths.get(file1.getPath() + ".bck")));
        assertTrue(Files.exists(Paths.get(file2.getPath() + ".bck")));
    }

    //Newly Created Test Case.
    //Purpose: Test for when the Options are in different order. -i -f -b -l
    @Test
    public void mainTest19() throws Exception {

        File file1 = createInputFile1();
        File file2 = createInputFile2();
        String args[] = {"-i", "-f","-b", "-l", "Bill", "William", "--", file1.getPath(), file2.getPath()};
        Main.main(args);

        String expected1 = "Howdy William,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy William\" again!";

        String expected2= "Howdy William,\n" +
                "This is another test file for the replace utility\n" +
                "that contains a list:\n" +
                "-a) Item 1\n" +
                "-b) Item 2\n" +
                "...\n" +
                "and says \"howdy William\" twice";

        String actual1 = getFileContent(file1.getPath());
        String actual2 = getFileContent(file2.getPath());

        assertEquals("The files differ!", expected1, actual1);
        assertEquals("The files differ!", expected2, actual2);

        assertTrue(Files.exists(Paths.get(file1.getPath() + ".bck")));
        assertTrue(Files.exists(Paths.get(file2.getPath() + ".bck")));
    }

    //Newly Created Test Case.
    //Purpose: Test for when the Options are in different order. -f -b -l -i
    @Test
    public void mainTest20() throws Exception {

        File file1 = createInputFile1();
        String args[] = {"-f", "-b","-l", "-i", "Bill", "William", "--", file1.getPath()};
        Main.main(args);

        String expected1 = "Howdy William,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy William\" again!";

        String actual1 = getFileContent(file1.getPath());
        assertEquals("The files differ!", expected1, actual1);
        assertTrue(Files.exists(Paths.get(file1.getPath() + ".bck")));
    }

    //Newly Created Test Case.
    //Purpose: Test for when the Options are in different order. -f -b -i -l
    @Test
    public void mainTest21() throws Exception {

        File file1 = createInputFile1();
        String args[] = {"-f", "-b","-i", "-l", "Bill", "William", "--", file1.getPath()};
        Main.main(args);

        String expected1 = "Howdy William,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy William\" again!";

        String actual1 = getFileContent(file1.getPath());
        assertEquals("The files differ!", expected1, actual1);
        assertTrue(Files.exists(Paths.get(file1.getPath() + ".bck")));
    }

    //Newly Created Test Case
    //Purpose: Test for occurrence of -f -l
    @Test
    public void mainTest22() throws Exception {

        File file1 = createInputFile1();
        String args[] = {"-f","-l",  "Bill", "William", "--", file1.getPath()};
        Main.main(args);

        String expected1 = "Howdy William,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy bill\" again!";

        String actual1 = getFileContent(file1.getPath());
        assertEquals("The files differ!", expected1, actual1);
        assertFalse(Files.exists(Paths.get(file1.getPath() + ".bck")));
    }

    //Newly Created Test Case
    //Purpose: Test for occurrence of -f -i in that specific order.
    @Test
    public void mainTest23() throws Exception {

        File file1 = createInputFile6();
        String args[] = {"-f","-i",  "aneeq", "abc", "--", file1.getPath() };
        Main.main(args);

        String expected1 = "my name is abc or ANEEQ\n"+
                "I am taking cs 370\n"+
                "you can call me Aneeq";

        String actual1 = getFileContent(file1.getPath());
        assertEquals("The files differ!", expected1, actual1);
        assertFalse(Files.exists(Paths.get(file1.getPath() + ".bck")));
    }

    //Newly Created Test Case.
    //Purpose: Test for -l -i
    @Test
    public void mainTest24() throws Exception {

        File file1 = createInputFile6();
        String args[] = {"-l","-i",  "aneeq", "abc", "--", file1.getPath() };
        Main.main(args);

        String expected1 = "my name is aneeq or ANEEQ\n"+
                "I am taking cs 370\n"+
                "you can call me abc";

        String actual1 = getFileContent(file1.getPath());
        assertEquals("The files differ!", expected1, actual1);
        assertFalse(Files.exists(Paths.get(file1.getPath() + ".bck")));
    }

    //Newly Created Test Case.
    //Purpose: Test for -b -f -l
    @Test
    public void mainTest25() throws Exception {

        File file1 = createInputFile1();
        File file2 = createInputFile2();
        String args[] = {"-b", "-f","-l",  "Bill", "William", "--", file1.getPath(), file2.getPath()};
        Main.main(args);

        String expected1 = "Howdy William,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy bill\" again!";

        String expected2 = "Howdy William,\n" +
                "This is another test file for the replace utility\n" +
                "that contains a list:\n" +
                "-a) Item 1\n" +
                "-b) Item 2\n" +
                "...\n" +
                "and says \"howdy William\" twice";

        String actual1 = getFileContent(file1.getPath());
        String actual2 = getFileContent(file2.getPath());

        assertEquals("The files differ!", expected1, actual1);
        assertEquals("The files differ!", expected2, actual2);

        assertTrue(Files.exists(Paths.get(file1.getPath() + ".bck")));
        assertTrue(Files.exists(Paths.get(file2.getPath() + ".bck")));
    }

    //Newly Created Test Case.
    //Purpose: Test for -b -f -i.
    @Test
    public void mainTest26() throws Exception {

        File file1 = createInputFile1();
        String args[] = {"-b", "-f","-i",  "Bill", "William", "--", file1.getPath()};
        Main.main(args);

        String expected1 = "Howdy William,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy bill\" again!";

        String actual1 = getFileContent(file1.getPath());
        assertEquals("The files differ!", expected1, actual1);
        assertTrue(Files.exists(Paths.get(file1.getPath() + ".bck")));
    }

    //Newly Created Test Case.
    //Purpose: Test for Options in different order -l -f -i -b
    @Test
    public void mainTest27() throws Exception {

        File file1 = createInputFile1();
        File file2 = createInputFile2();
        String args[] = {"-l", "-f","-i", "-b", "Bill", "William", "--", file1.getPath(), file2.getPath()};
        Main.main(args);

        String expected1 = "Howdy William,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy William\" again!";

        String expected2= "Howdy William,\n" +
                "This is another test file for the replace utility\n" +
                "that contains a list:\n" +
                "-a) Item 1\n" +
                "-b) Item 2\n" +
                "...\n" +
                "and says \"howdy William\" twice";

        String actual1 = getFileContent(file1.getPath());
        String actual2 = getFileContent(file2.getPath());

        assertEquals("The files differ!", expected1, actual1);
        assertEquals("The files differ!", expected2, actual2);

        assertTrue(Files.exists(Paths.get(file1.getPath() + ".bck")));
        assertTrue(Files.exists(Paths.get(file2.getPath() + ".bck")));
    }

    //Newly Created Test Case.
    //Purpose: Test for Options in different order -l -f -b -i
    @Test
    public void mainTest28() throws Exception {

        File file1 = createInputFile1();
        File file2 = createInputFile2();
        String args[] = {"-l", "-f","-b", "-i", "Bill", "William", "--", file1.getPath(), file2.getPath()};
        Main.main(args);

        String expected1 = "Howdy William,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy William\" again!";

        String expected2= "Howdy William,\n" +
                "This is another test file for the replace utility\n" +
                "that contains a list:\n" +
                "-a) Item 1\n" +
                "-b) Item 2\n" +
                "...\n" +
                "and says \"howdy William\" twice";

        String actual1 = getFileContent(file1.getPath());
        String actual2 = getFileContent(file2.getPath());

        assertEquals("The files differ!", expected1, actual1);
        assertEquals("The files differ!", expected2, actual2);

        assertTrue(Files.exists(Paths.get(file1.getPath() + ".bck")));
        assertTrue(Files.exists(Paths.get(file2.getPath() + ".bck")));
    }

    //Newly Created Test Case.
    //Purpose: Test for Options in different order -l -b -f -i
    @Test
    public void mainTest29() throws Exception {

        File file1 = createInputFile1();
        String args[] = {"-l", "-b","-f", "-i", "Bill", "William", "--", file1.getPath()};
        Main.main(args);

        String expected1 = "Howdy William,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy William\" again!";

        String actual1 = getFileContent(file1.getPath());
        assertEquals("The files differ!", expected1, actual1);
        assertTrue(Files.exists(Paths.get(file1.getPath() + ".bck")));
    }

    //Newly Created Test Case.
    //Purpose: Test for Options -l -f -i
    @Test
    public void mainTest30() throws Exception {

        File file1 = createInputFile1();
        String args[] = {"-l","-f", "-i", "Bill", "William", "--", file1.getPath()};
        Main.main(args);

        String expected1 = "Howdy William,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy William\" again!";

        String actual1 = getFileContent(file1.getPath());
        assertEquals("The files differ!", expected1, actual1);
        assertFalse(Files.exists(Paths.get(file1.getPath() + ".bck")));
    }

    //Newly Created Test Case.
    //Purpose: Test for Options -l -i -f
    @Test
    public void mainTest31() throws Exception {

        File file1 = createInputFile1();
        String args[] = {"-l","-i", "-f", "Bill", "William", "--", file1.getPath()};
        Main.main(args);

        String expected1 = "Howdy William,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy William\" again!";

        String actual1 = getFileContent(file1.getPath());
        assertEquals("The files differ!", expected1, actual1);
        assertFalse(Files.exists(Paths.get(file1.getPath() + ".bck")));
    }

    //Newly Created Test Case.
    //Purpose: Test for Options -i -f -l
    @Test
    public void mainTest32() throws Exception {

        File file1 = createInputFile1();
        String args[] = {"-i","-f", "-l", "Bill", "William", "--", file1.getPath()};
        Main.main(args);

        String expected1 = "Howdy William,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy William\" again!";

        String actual1 = getFileContent(file1.getPath());
        assertEquals("The files differ!", expected1, actual1);
        assertFalse(Files.exists(Paths.get(file1.getPath() + ".bck")));
    }

    //Newly Created Test Case.
    //Purpose: Test for Options -f -i -l
    @Test
    public void mainTest33() throws Exception {

        File file1 = createInputFile1();
        String args[] = {"-f","-i", "-l", "Bill", "William", "--", file1.getPath()};
        Main.main(args);

        String expected1 = "Howdy William,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy William\" again!";

        String actual1 = getFileContent(file1.getPath());
        assertEquals("The files differ!", expected1, actual1);
        assertFalse(Files.exists(Paths.get(file1.getPath() + ".bck")));
    }

    //Newly Created Test Case.
    //Purpose: Test for Options -f -b -i
    @Test
    public void mainTest34() throws Exception {

        File file1 = createInputFile1();
        File file2 = createInputFile2();
        String args[] = {"-f", "-b","-i",  "Bill", "William", "--", file1.getPath(), file2.getPath()};
        Main.main(args);

        String expected1 = "Howdy William,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy bill\" again!";

        String expected2="Howdy William,\n" +
                "This is another test file for the replace utility\n" +
                "that contains a list:\n" +
                "-a) Item 1\n" +
                "-b) Item 2\n" +
                "...\n" +
                "and says \"howdy Bill\" twice";

        String actual1 = getFileContent(file1.getPath());
        String actual2 = getFileContent(file2.getPath());

        assertEquals("The files differ!", expected1, actual1);
        assertEquals("The files differ!", expected2, actual2);

        assertTrue(Files.exists(Paths.get(file1.getPath() + ".bck")));
        assertTrue(Files.exists(Paths.get(file2.getPath() + ".bck")));
    }

    //Newly Created Test Case.
    //Purpose: Test for -f -i -b
    @Test
    public void mainTest35() throws Exception {

        File file1 = createInputFile1();
        File file2 = createInputFile2();
        String args[] = {"-f", "-i","-b",  "Bill", "William", "--", file1.getPath(), file2.getPath()};
        Main.main(args);

        String expected1 = "Howdy William,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy bill\" again!";

        String expected2="Howdy William,\n" +
                "This is another test file for the replace utility\n" +
                "that contains a list:\n" +
                "-a) Item 1\n" +
                "-b) Item 2\n" +
                "...\n" +
                "and says \"howdy Bill\" twice";

        String actual1 = getFileContent(file1.getPath());
        String actual2 = getFileContent(file2.getPath());

        assertEquals("The files differ!", expected1, actual1);
        assertEquals("The files differ!", expected2, actual2);

        assertTrue(Files.exists(Paths.get(file1.getPath() + ".bck")));
        assertTrue(Files.exists(Paths.get(file2.getPath() + ".bck")));
    }

    //Newly Created Test Case.
    //Purpose: Test for -i -l -f
    @Test
    public void mainTest36() throws Exception {

        File file1 = createInputFile1();
        String args[] = {"-i","-l", "-f", "Bill", "William", "--", file1.getPath()};
        Main.main(args);

        String expected1 = "Howdy William,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy William\" again!";

        String actual1 = getFileContent(file1.getPath());
        assertEquals("The files differ!", expected1, actual1);
        assertFalse(Files.exists(Paths.get(file1.getPath() + ".bck")));
    }

    //Newly Created Test Case.
    //Purpose: Test for -i -b -f
    @Test
    public void mainTest37() throws Exception {

        File file1 = createInputFile1();
        String args[] = {"-i", "-b","-f",  "Bill", "William", "--", file1.getPath()};
        Main.main(args);

        String expected1 = "Howdy William,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy bill\" again!";

        String actual1 = getFileContent(file1.getPath());
        assertEquals("The files differ!", expected1, actual1);
        assertTrue(Files.exists(Paths.get(file1.getPath() + ".bck")));
    }

    //Newly Created Test Case.
    //Purpose: Test for -b -i -f
    @Test
    public void mainTest38() throws Exception {

        File file1 = createInputFile1();
        File file2 = createInputFile2();
        String args[] = {"-b", "-i","-f",  "Bill", "William", "--", file1.getPath(), file2.getPath()};
        Main.main(args);

        String expected1 = "Howdy William,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy bill\" again!";

        String expected2="Howdy William,\n" +
                "This is another test file for the replace utility\n" +
                "that contains a list:\n" +
                "-a) Item 1\n" +
                "-b) Item 2\n" +
                "...\n" +
                "and says \"howdy Bill\" twice";

        String actual1 = getFileContent(file1.getPath());
        String actual2 = getFileContent(file2.getPath());

        assertEquals("The files differ!", expected1, actual1);
        assertEquals("The files differ!", expected2, actual2);

        assertTrue(Files.exists(Paths.get(file1.getPath() + ".bck")));
        assertTrue(Files.exists(Paths.get(file2.getPath() + ".bck")));
    }

    //Newly Created Test Case.
    //Purpose: Test for -i -f -b
    @Test
    public void mainTest39() throws Exception {

        File file1 = createInputFile1();
        String args[] = {"-i", "-f","-b",  "Bill", "William", "--", file1.getPath()};
        Main.main(args);

        String expected1 = "Howdy William,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy bill\" again!";

        String actual1 = getFileContent(file1.getPath());
        assertEquals("The files differ!", expected1, actual1);
        assertTrue(Files.exists(Paths.get(file1.getPath() + ".bck")));
    }

    //Newly Created Test Case.
    //Purpose: Test for -f -l -b
    @Test
    public void mainTest40() throws Exception {

        File file1 = createInputFile1();
        File file2 = createInputFile2();
        String args[] = {"-f", "-l","-b",  "Bill", "William", "--", file1.getPath(), file2.getPath()};
        Main.main(args);

        String expected1 = "Howdy William,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy bill\" again!";

        String expected2 = "Howdy William,\n" +
                "This is another test file for the replace utility\n" +
                "that contains a list:\n" +
                "-a) Item 1\n" +
                "-b) Item 2\n" +
                "...\n" +
                "and says \"howdy William\" twice";

        String actual1 = getFileContent(file1.getPath());
        String actual2 = getFileContent(file2.getPath());

        assertEquals("The files differ!", expected1, actual1);
        assertEquals("The files differ!", expected2, actual2);

        assertTrue(Files.exists(Paths.get(file1.getPath() + ".bck")));
        assertTrue(Files.exists(Paths.get(file2.getPath() + ".bck")));
    }
}
