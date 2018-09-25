package proxy;

/**
 * 接口实现类
 */
public class WelcomeServiceImpl implements WelcomeService,WelcomeService2 {
	public void sayHello(String msg) {
		System.out.println("hello : " + msg);
	}

	public void sayGood(String good) {
		System.out.println("GOOD : " + good);
	}
}
