package client;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;


/**
 * 客户端群聊界面
 */
public class QQClientChatsUI extends JFrame implements ActionListener {

	//历史聊天区
	private JTextArea taHistory;

	//好友列表
	private JList<String> lstFriends;

	//消息输入区
	private JTextArea taInputMessage;

	//发送按钮
	private JButton btnSend;

	//刷新好友列表按钮
	private JButton btnRefresh;

	public QQClientChatsUI() {
		init();
		this.setVisible(true);
	}

	/**
	 * 初始化布局
	 */
	private void init() {
		this.setTitle("QQClient");
		this.setBounds(100, 100, 800, 600);
		this.setLayout(null);

		//历史区
		taHistory = new JTextArea();
		taHistory.setBounds(0, 0, 600, 400);

		JScrollPane sp1 = new JScrollPane(taHistory);
		sp1.setBounds(0, 0, 600, 400);
		this.add(sp1);

		//lstFriends
		lstFriends = new JList<String>();
		lstFriends.setBounds(620, 0, 160, 400);
		lstFriends.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
			}
		});
		this.add(lstFriends);

		//taInputMessage
		taInputMessage = new JTextArea();
		taInputMessage.setBounds(0, 420, 540, 160);
		this.add(taInputMessage);

		//btnSend
		btnSend = new JButton("发送");
		btnSend.setBounds(560, 420, 100, 160);
		btnSend.addActionListener(this);
		this.add(btnSend);

		//btnRefresh
		btnRefresh = new JButton("刷新");
		btnRefresh.setBounds(680, 420, 100, 160);
		btnRefresh.addActionListener(this);
		this.add(btnRefresh);

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(-1);
			}
		});
	}

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
	}

	/**
	 * 刷新好友列表
	 */
	public void refreshFriendList(List<String> list) {
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		for (String s : list) {
			listModel.addElement(s);
		}
		lstFriends.setModel(listModel);
	}

	/**
	 * 更新历史区域内容
	 */
	public void updateHistory(String who ,String msg) {
		taHistory.append("[" + who + "]说:\r\n");
		String formatStr = msg.replace("\n", "\n\t");
		formatStr = "\t" + formatStr + "\r\n";
		taHistory.append(formatStr);
	}
}
