package test.learn.guava.basic;

import java.io.File;

public class Test {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String packageValue = "11.22";

        String packagePath = packageValue.replaceAll("\\.", "\\"+File.separator);
        
        System.out.println(packagePath);
        System.out.println(File.separator);
    }

}
