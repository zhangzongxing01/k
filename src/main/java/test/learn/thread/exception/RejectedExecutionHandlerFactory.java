package test.learn.thread.exception;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

public class RejectedExecutionHandlerFactory {

    public static final int AbortPolicy         = 1;
    public static final int CallerRunsPolicy    = 2;
    public static final int DiscardPolicy       = 3;
    public static final int DiscardOldestPolicy = 4;

    public static RejectedExecutionHandler getHandler(int policy) {
        RejectedExecutionHandler Handler = null;
        switch (policy) {
            case AbortPolicy:
                Handler = new ThreadPoolExecutor.AbortPolicy();
                break;
            case CallerRunsPolicy:
                Handler = new ThreadPoolExecutor.CallerRunsPolicy();
                break;
            case DiscardPolicy:
                Handler = new ThreadPoolExecutor.DiscardPolicy();
                break;
            case DiscardOldestPolicy:
                Handler = new ThreadPoolExecutor.DiscardOldestPolicy();
                break;
            default:
                Handler = new ThreadPoolExecutor.AbortPolicy();
        }
        return Handler;
    }
}
