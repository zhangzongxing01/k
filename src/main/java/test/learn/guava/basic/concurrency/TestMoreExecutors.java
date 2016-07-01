package test.learn.guava.basic.concurrency;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

public class TestMoreExecutors {

    public static void main(String[] args) {
        testCreateThreadPool();
    }

    public static void testCreateThreadPool() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Callable<String> myCallable = new MyCallable();
        ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(executorService);
        ListenableFuture<String> future = listeningExecutorService.submit(myCallable);
        //方案一：添加监听，另起一个线程去获取future的返回值，原理就是等待。
        future.addListener(new Runnable() {
            
            @Override
            public void run() {
                try {
                    future.get();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        } , listeningExecutorService);
        //方案二：添加callback，future成功后会回调下面两个方法，成功时，失败时。
        FutureCallback<String> callback = new FutureCallback<String>() {
            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("Failure");
            }

            @Override
            public void onSuccess(String arg0) {
                System.out.println(arg0);
                // TODO Auto-generated method stub

            }
        };
        Futures.addCallback(future, callback, listeningExecutorService);
//        listeningExecutorService.shutdown();
        if(listeningExecutorService.isTerminated()){
            listeningExecutorService.shutdown();
        }
    }

}
