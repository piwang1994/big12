package 设计模式.singleton;

/**
 * 回收站
 */
public class LazySingleton {
	private LazySingleton(){
	}
	private static LazySingleton instance = new LazySingleton();

	//LazySingleton
	public static LazySingleton getInstance(){
		if(instance != null){
			return instance ;
		}
		synchronized (LazySingleton.class){
			if(instance == null){
				instance = new LazySingleton() ;
			}
		}
		return instance ;
	}




}

//测试
class TestSingle{
	public static void main(String[] args) {
		new Thread() {
			public void run() {
				LazySingleton t = LazySingleton.getInstance();
				System.out.println(t);
			}
		}.start();
		new Thread() {
			public void run() {
				LazySingleton t = LazySingleton.getInstance();
				System.out.println(t);
			}
		}.start();
	}
}
