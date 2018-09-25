import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class IteratorDemo {
    static Collection c= new ArrayList();

    public static void main(String[] args) {
        c.add("qq");
        c.add("p");
        c.add("s");

        Iterator iter = c.iterator();
        while (iter.hasNext()) {
            Object o = iter.next();
            if (o.equals("p")) {
                iter.remove();
            }
            System.out.print(o);
        }


        for (Object o : c) {
            System.out.print(o);
        }
    }
}
