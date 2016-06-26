package test.learn.guava.basic.concurrency;

import java.util.concurrent.Callable;
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
        FutureCallback<String> callback = new FutureCallback<String>() {
            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("success");
            }

            @Override
            public void onSuccess(String arg0) {
                System.out.println("success");
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
