package test.learn.guava.basic.collection;



public abstract class AbstractCollection {
    public abstract void test1(); 
    
    public void test2(){
        System.out.println("test2");
        test1();
        System.out.println("test2");
    }
}
