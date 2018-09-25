package client;

public class QQClientMain {
    public static void main(String[] args){
        QQClientChatsUI ui = new QQClientChatsUI();
        QQClientCommThread t1 = new QQClientCommThread();
        t1.start();
    }
}
