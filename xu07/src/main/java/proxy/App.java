package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * App
 * 
 */
public class App {
	public static void main(String[] args) {

		//目标对象
		final WelcomeService target = new WelcomeServiceImpl() ;


		//类加载器
		ClassLoader loader = ClassLoader.getSystemClassLoader();

		//接口集合
		Class[] interfaces = {WelcomeService.class,WelcomeService2.class};

		//处理器
		InvocationHandler h = new InvocationHandler() {
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				long start=System.nanoTime();
				Object o = method.invoke(target, args);
				System.out.println(System.nanoTime()-start);
				return o;
			}
		} ;


		//创建代理对象
		Object proxy = Proxy.newProxyInstance(loader , interfaces , h);

		//访问代理对象的方法
		((WelcomeService)proxy).sayHello("tomas");
		((WelcomeService2) proxy).sayGood("老徐");
	}
}
