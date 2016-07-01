package test.learn.io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class TestBuffer {

    public static void main(String[] args) {

    }

    public static void useNIOReadr(){
        FileChannel inChannel=null;
        try {
            ByteBuffer buffer = ByteBuffer.allocate(48);  
            inChannel =new  FileInputStream("D:\\testFile\\1.txt").getChannel();
            inChannel.read(buffer);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            try {
                inChannel.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    public static void useIOReadr() {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream("D:\\testFile\\1.txt")));
            String nameLine = reader.readLine();
            String ageLine = reader.readLine();
            String emailLine = reader.readLine();
            String phoneLine = reader.readLine();
            System.out.println(nameLine);
            System.out.println(ageLine);
            System.out.println(emailLine);
            System.out.println(phoneLine);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}
