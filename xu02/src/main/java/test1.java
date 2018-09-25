import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class test1 {
    @Test
    public void testArraylist() {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        long l1 = System.currentTimeMillis();
        for(int i=0;i<1000000;i++){
            arrayList.add(0,i);
        }
        System.out.println(System.currentTimeMillis()-l1);

        LinkedList ll = new LinkedList();
        long l2 = System.currentTimeMillis();
        for(int i=0;i<1000000;i++){
            arrayList.add(0,i);
        }
        System.out.println(System.currentTimeMillis()-l2);


    }

    @Test
    public void testHashmap(){
        HashMap<Integer, String> hashMap = new HashMap<Integer, String>();

        hashMap.put(1,"tom");
        hashMap.put(1,"tom");
        hashMap.put(2,"tom");
        hashMap.put(2,"tom");

    }
}
