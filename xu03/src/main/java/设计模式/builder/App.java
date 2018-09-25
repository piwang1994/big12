package 设计模式.builder;

/**
 * Created by Administrator on 2018/9/7.
 */
public class App {
	public static void main(String[] args) {
		Computer c0 = new Computer.Builder()
							 .setCpu("intel")
							 .setMemory("sanxing")
							 .setHardDisk("xishu")
							 .build();

		Computer c1 = new Computer.Builder()
				.setCpu("intel")
				.setMemory("sanxing")
				.setHardDisk("xishu")
				.build();
		System.out.println(c0==c1);


	}
}
