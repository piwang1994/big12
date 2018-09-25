package 设计模式.factory;

/**
 * Created by Administrator on 2018/9/7.
 */
public class App {
	public static void main(String[] args) {

		//静态工厂
		System.out.println(Factory1.newTVSet().getBrand()) ;

		//非静态工厂
		Factory2 f2 = new Factory2();
		System.out.println(f2.newTVSet().getColor()) ;
	}
}
