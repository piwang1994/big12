import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class TestZk {

    /**
     * 列出指定结点的孩子结点
     * @throws Exception
     */
    @Test
    public void testLs() throws Exception {

        String conn = "s202:2181,s203:2181,s204:2181";

        ZooKeeper zk = new ZooKeeper(conn,5000,null);

        List<String> children = zk.getChildren("/", false);

        for(String child : children){
            System.out.println(child);
        }

    }

    /**
     * 获得指定结点的数据
     * @throws Exception
     */
    @Test
    public void testGet() throws Exception {

        Stat stat = new Stat();

        System.out.println("第一次" + stat.toString());

        String conn = "s202:2181,s203:2181,s204:2181";

        ZooKeeper zk = new ZooKeeper(conn,5000,null);

        byte[] data = zk.getData("/a", false, stat);

        System.out.println(new String(data));
        System.out.println("第二次" + stat.toString());
    }

    /**
     * 创建指定结点
     * @throws Exception
     */
    @Test
    public void testCreate() throws Exception {

        String conn = "s202:2181,s203:2181,s204:2181";

        ZooKeeper zk = new ZooKeeper(conn,5000,null);

        String s = zk.create("/b", "helloworld".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        System.out.println(s);

        zk.close();
    }

    //删除
    @Test
    public void testDelete() throws Exception {

        String conn = "s202:2181,s203:2181,s204:2181";

        ZooKeeper zk = new ZooKeeper(conn,5000,null);

        //版本号 -1为通配,无法递归删除，需手动实现
        zk.delete("/a/b", -1);

        zk.close();
    }

    //set
    @Test
    public void testSet() throws Exception {

        String conn = "s202:2181,s203:2181,s204:2181";

        ZooKeeper zk = new ZooKeeper(conn,5000,null);

        Stat stat = zk.setData("/a", "helloworld".getBytes(), -1);
        System.out.println(stat.toString());
        zk.close();
    }

    //递归列出指定结点及其子结点的路径和数据
    public static void testLs2(String path, Stat stat , ZooKeeper zk) throws Exception {
        List<String> children = zk.getChildren(path, false);

        if(path.equals("/")){
            for(String child : children){
                byte[] data = zk.getData(path+child, false, stat);
                System.out.println(path+child+ ":\t" + new String(data));
                testLs2(path+child , stat,zk);
            }
        }
        else {
            for(String child : children){
                byte[] data = zk.getData(path+"/"+child, false, stat);
                System.out.println(path+"/"+child+ ":\t" + new String(data));
                testLs2(path+"/"+child ,stat ,zk);
            }
        }
    }

    public static void main(String[] args) throws Exception{
        String conn = "s202:2181,s203:2181,s204:2181";
        Stat stat = new Stat();

        ZooKeeper zk = new ZooKeeper(conn,5000,null);
        testLs2("/", stat,zk);
    }


    //观察者模式
    //此模式，是监控某个指定的结点，当结点发生改变，则会通过回调机制，调用process函数。并执行
    @Test
    public void testWatch() throws Exception {

        Stat stat = new Stat();

        String conn = "s202:2181,s203:2181,s204:2181";

        //采用匿名内部类，直接初始化watcher
        ZooKeeper zk = new ZooKeeper(conn, 5000, new Watcher() {
            public void process(WatchedEvent event) {
                System.out.println(event.getType().toString());
            }
        });

        byte[] data = zk.getData("/a", false, stat);

        System.out.println(new String(data));

        //强行运行代码
        while (true){
            Thread.sleep(500);
        }
    }
    //观察者模式2
    //此模式，是监控某个指定的结点，当结点发生改变，则会通过回调机制，调用process函数。并执行
    @Test
    public void testWatch2() throws Exception {

        Stat stat = new Stat();

        String conn = "s202:2181,s203:2181,s204:2181";

        //采用匿名内部类，直接初始化watcher
        ZooKeeper zk = new ZooKeeper(conn, 5000, null);

        byte[] data = zk.getData("/a", new Watcher() {
            public void process(WatchedEvent event) {
                System.out.println(event.getType().toString());
            }
        }, stat);

        System.out.println(new String(data));

        //强行运行代码
        while (true){
            Thread.sleep(500);
        }
    }



    //观察者模式重复注册//该方式监控节点有限
    //此模式，是监控某个指定的结点，当结点发生改变，则会通过回调机制，调用process函数。并执行
    @Test
    public void repeatWatch() throws Exception {

        final Stat stat = new Stat();

        String conn = "s202:2181,s203:2181,s204:2181";

        //采用匿名内部类，直接初始化watcher
        final ZooKeeper zk = new ZooKeeper(conn, 5000, null);


        Watcher w = new Watcher() {
            public void process(WatchedEvent event) {
                try {
                    System.out.println(event.getType().toString());
                    byte[] data = zk.getData("/a", this, stat);
                    System.out.println(new String(data));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        //触发第一次
        byte[] data = zk.getData("/a", w, stat);


        //强行运行代码
        while (true) {
            Thread.sleep(500);
        }
    }


}
