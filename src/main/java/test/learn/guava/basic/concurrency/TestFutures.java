package test.learn.guava.basic.concurrency;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

public class TestFutures {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        testTransformAsync();
    }

    /**
     * public static <V, X extends Exception> CheckedFuture<V, X> makeChecked
     */
    static ListeningExecutorService executorService = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());

    public static void testTransformAsync() throws InterruptedException, ExecutionException {
//        ListenableFuture<String> future = executorService.submit(new MyCallable());
        ListenableFuture<Integer> indexSearch = searchAsync(123);
        AsyncFunction<Integer, String> queryFunction = new AsyncFunction<Integer, String>() {

            @Override
            public ListenableFuture<String> apply(final Integer i) {
                return getPersonsByIdAsync(i);
            }
        };

        ListenableFuture<String> results = Futures.transformAsync(indexSearch, queryFunction, executorService);
        System.out.println("hhh");
        System.out.println(results.get());
        // future.
    }

    public static ListenableFuture<String> getPersonsByIdAsync(final Integer i) {
        return executorService.submit(new Callable<String>() {

            @Override
            public String call() throws Exception {
                Thread.sleep(1000);
                System.out.println("getPersonsByIdAsync 执行");
                return i+"nihao";
            }
        });
    }

    public static ListenableFuture<Integer> searchAsync(final Integer query) {
        return executorService.submit(new Callable<Integer>() {

            @Override
            public Integer call() throws Exception {
                Thread.sleep(1000);
                System.out.println("searchAsync 执行");
                return query+1;
            }
        });
    }
}
