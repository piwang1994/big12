import org.junit.Test;

import static java.lang.Thread.sleep;

public class Testjava {


    private void testjvm(int n) {
        System.out.println(n);
        testjvm(++n);
    }

    @Test
    public void test() {
        testjvm(1);
    }

@Test
    public void jvmGC() throws InterruptedException {
        while (true) {
            new Thread();
            sleep(10000);
        }
    }
}
