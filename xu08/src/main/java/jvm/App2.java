package jvm;

/**
 * Created by Administrator on 2018/9/14.
 */
public class App2 {
	public static void main(String[] args) throws Exception {
		ClassLoader loader = new MyClassLoader();
		Class clz = loader.loadClass("ByeServiceImpl") ;
		IByeService bs = (IByeService) clz.newInstance();
		bs.sayBye("tomtomt");

	}
}

