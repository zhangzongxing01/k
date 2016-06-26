package test.learn.guava.basic.concurrency;

import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class TestFuture {

    public static void main(String[] args) throws Exception {
        
//        Thread thread=new Thread();
//        ExecutorService executorService = Executors.newCachedThreadPool();
//        Future<String> myFuture = executorService.submit(new MyCallable());
        FutureTask<String> myFuture=new FutureTask<String>(new MyCallable());
        Thread thread=new Thread(myFuture);
        thread.start();
        System.out.println("hah");
        System.out.println(myFuture.get());
//        System.out.println(myFuture.get(20, TimeUnit.MILLISECONDS));
//        executorService.shutdown();
//        Callable myCallable=new MyCallable();
//        myCallable.call();
//        System.out.println("s");
    }

}
