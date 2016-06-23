package test.learn.thread.exception;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import test.learn.thread.MyRunnable;

public class TestHanderException {

    private static final int THREADS_SIZE = 1;
    private static final int CAPACITY     = 1;

    public static void main(String[] args) {
        testRejectedExecutionHandler(RejectedExecutionHandlerFactory.DiscardOldestPolicy );
    }

    public static void testRejectedExecutionHandler(int policy) {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(THREADS_SIZE, THREADS_SIZE, 0, TimeUnit.SECONDS,
                                                         new ArrayBlockingQueue<Runnable>(CAPACITY));
        pool.setRejectedExecutionHandler(RejectedExecutionHandlerFactory.getHandler(policy));
        // 新建10个任务，并将它们添加到线程池中。
        for (int i = 0; i < 10; i++) {
            Runnable myrun = new MyRunnable("task-" + i);
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            pool.execute(myrun);
        }
        // 关闭线程池
        pool.shutdown();
    }
}
