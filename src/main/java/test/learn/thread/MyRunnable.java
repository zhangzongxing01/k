package test.learn.thread;



public class MyRunnable implements Runnable {
    private String name;
    public MyRunnable(String name){
        this.name=name;
    }
    @Override
    public void run() {
        System.out.println(this.name+" is running");
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }        
    }

}
