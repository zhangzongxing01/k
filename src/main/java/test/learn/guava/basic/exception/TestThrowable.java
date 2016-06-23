package test.learn.guava.basic.exception;

public class TestThrowable {

    public static void main(String[] args) {
        try {
            Integer.class.cast(args);
        } catch (Exception | Error e) {
            // TODO: handle exception
        }
        testRuntimeException();
    }

    public static void testRuntimeException() {
        boolean tag=true;
        if (tag) {
            throw new RuntimeException();
        }
       
        throw new NullPointerException();
        
       
    }
}
