package 设计模式.adaptor;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * 适配器模式windowAdapter
 */
public class App {
	public static void main(String[] args) {
		JFrame f = new JFrame() ;
		f.setBounds(0 ,0 , 1366 , 768);
		f.setTitle("标题栏");
		f.addWindowListener(new WindowAdapter() {
			//里面的方法都是空实现----》》给用户自主选择实现方法
			public void windowClosing(WindowEvent e) {
				System.exit(-1);
			}

		});

		f.setVisible(true);
	}
}
