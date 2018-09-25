import java.util.HashSet;

/*
 * HashSet():16,0.75
 * HashSet(int capacity,float loadFactor)
 *
 */
public class HashSetDemo1 {

    public static void main(String[] args) {
        HashSet<String> set = new HashSet<String>();
        set.add("abc");
        set.add("abc");
        set.add("abc");
        set.add("abc2");
        set.add("abc2");
        set.add("abc2");
        set.add("abc5");
        set.add("abc1");
        set.add("abc9");

        System.out.println(set.size());
        for (String string : set) {
            System.out.println(string);
        }


    }
}