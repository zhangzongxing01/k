package test.learn.guava.basic.io;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import com.google.common.io.Files;


public class TestFiles {

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        testToString();

    }
    public static void testToString() throws IOException{
       System.out.println( Files.toString(new File("D:\\testFile\\1.txt"),Charset.forName("utf-8")));
    }

}
