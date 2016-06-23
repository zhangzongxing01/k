package test.learn.guava.basic;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.google.common.base.Function;
import com.google.common.collect.Ordering;

public class TestOrdering {

    public static void main(String[] args) {
        MyObject myObject1 = new MyObject("a", 1, 10);
        MyObject myObject2 = new MyObject("b", 2, 9);
        MyObject myObject3 = new MyObject("c", 3, 8);
        MyObject myObject4 = new MyObject("d", 3, 8);

        Function<MyObject, Integer> function = new Function<MyObject, Integer>() {

            @Override
            public Integer apply(MyObject myObject) {
                return myObject.getNumber2();
            }
        };
        Ordering<MyObject> myOrder1 = Ordering.natural().nullsFirst().onResultOf(function);
//        Ordering<Comparable>myOrder1=  Ordering.natural().nullsFirst();
        List<MyObject> myOrderedObjectList = Arrays.asList(myObject1, myObject2, myObject3);
        Ordering<MyObject> myOrder2 = Ordering.explicit(myOrderedObjectList);
        List<MyObject> myObjectList = Arrays.asList(myObject2, myObject3);
        System.out.println(myOrder1.isOrdered(myObjectList));
        // Collections.sort(myObjectList, myOrder);

        myObjectList = myOrder2.sortedCopy(myObjectList);
        System.out.println(myOrder2.isOrdered(myObjectList));
    }
}
