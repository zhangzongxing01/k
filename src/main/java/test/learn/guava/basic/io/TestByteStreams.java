package test.learn.guava.basic.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

import com.google.common.io.ByteStreams;
import com.google.common.io.Closer;

public class TestByteStreams {

    public static void main(String[] args) {
        testToByteArray();
    }

    public static void testToByteArray() {
        InputStream in = null;
        try {
            in = new FileInputStream("D:\\testFile\\1.txt");
            byte[] mydatas=ByteStreams.toByteArray(in);
            System.out.println(mydatas);
            System.out.println(new String(mydatas));
        }catch(IOException e){
            
        }finally{
            try {
                in.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    public static void testCopyChannel() {
        ReadableByteChannel in = null;
        WritableByteChannel out = null;
        try {
            in = new FileInputStream("D:\\testFile\\1.txt").getChannel();
            out = new FileOutputStream(new File("D:\\testFile\\4.txt")).getChannel();
//            ByteBuffer buffer = ByteBuffer.allocateDirect(8192);
//            while (in.read(buffer) != -1) {
//                buffer.flip();
//                while (buffer.hasRemaining()) {
//                    out.write(buffer);
//                }
//                buffer.clear();
//            }
            // ByteStreams.copy(in, out);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                in.close();
                out.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public static void testCopy() {
        InputStream in = null;
        OutputStream out = null;
        Closer closer=Closer.create();
        try {
            in = new FileInputStream("D:\\testFile\\1.txt");
            closer.register(in);
            out = new FileOutputStream(new File("D:\\testFile\\2.txt"));
            closer.register(out);
            ByteStreams.copy(in, out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                closer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //
    }
}
