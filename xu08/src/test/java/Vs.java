public class Vs extends Thread{
    private volatile boolean stop = false;//不加volatile则会stop在线程自己的空间中
    public void stopMe(){
        stop=true;
    }

    public void run(){
        int i=0;
        while(!stop){
            i++;
        }
        System.out.println("Stop thread");
    }

    public static void main(String args[]) throws InterruptedException{
        Vs t=new Vs();
        t.start();
        Thread.sleep(1000);
        t.stopMe();
        Thread.sleep(1000);
    }
}
