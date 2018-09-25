package jvm;

import java.lang.reflect.Method;

/**
 * Created by Administrator on 2018/9/14.
 */
public class App {
	public static void main(String[] args) throws Exception {
		//使用自定义的类加载
		ClassLoader loader = new MyClassLoader();
		Class clz = loader.loadClass("Hello") ;
		System.out.println(clz);
		Method m = clz.getDeclaredMethod("main") ;
		m.setAccessible(true);
		m.invoke(null ) ;
		System.out.println();


	}
}
