package ru.spbstu.telematics.java;

import java.util.List;
import java.nio.file.Paths;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    public void testFileExists() {
        List<String> result = App.listFiles("test_dir/file1.txt");
        assertEquals(1, result.size());
        assertEquals(Paths.get("test_dir", "file1.txt").toString(), result.get(0));
    }

    public void testFileDoesNotExist() {
        List<String> result = App.listFiles("test_dir/notexisting.txt");
        assertEquals(1, result.size());
        assertEquals("Директория или файл не существует или путь указан неверно.", result.get(0));
    }

    public void testDirectoryExists() {
        List<String> result = App.listFiles("test_dir");
        assertEquals(1, result.size());
        assertEquals("file1.txt", result.get(0));
    }

    public void testPathDoesNotExist() {
        List<String> result = App.listFiles("notexisting");
        System.out.println(System.getProperty("user.dir"));
        System.out.println("FROM TEST: " + result.get(0));
        assertEquals(1, result.size());
        assertEquals("Директория или файл не существует или путь указан неверно.", result.get(0));
    }
}
