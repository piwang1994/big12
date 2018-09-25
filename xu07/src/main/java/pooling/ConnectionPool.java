package pooling;


import java.sql.Connection;
import java.sql.DriverManager;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 连接池-----》被MyDataSource调用
 */
public class ConnectionPool {


	//单例模式：保证一个连接池
	private static ConnectionPool instance = null ;
	//忙连接个数
	private AtomicInteger busies = new AtomicInteger(0) ;

	public static ConnectionPool getInstance(){
		if(instance != null){
			return instance ;
		}
		synchronized (ConnectionPool.class){
			if(instance == null ){
				instance = new ConnectionPool() ;
			}
		}
		return instance ;
	}

	private static int INIT = 2 ;

	private static int MAX = 3 ;

	//容器
	private List<Connection> pool = new LinkedList<Connection>() ;



	/**
	 * 构造+初始化池子
	 */
	private ConnectionPool(){
		initPool() ;
	}
	private void initPool() {
		for(int i = 0 ; i < INIT ; i ++){
			Connection conn = openNewWrappedConnection();
			if(conn != null){
				pool.add(conn) ;
			}
		}
	}

	/**
	 * 开启装饰连接
	 */
	private Connection openNewWrappedConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/big12" ;
			String user = "root" ;
			String pass = "root" ;
			Connection conn = DriverManager.getConnection(url , user , pass) ;
			return new MyConnection(conn , this);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null ;
	}


	/**
	 * 获取连接
	 */
	public synchronized Connection getConnection() throws InterruptedException {
		if(!pool.isEmpty()){
			busies.incrementAndGet() ;
			return pool.remove(0) ;
		}
		while(busies.get() >= MAX){
			this.wait(10);
		}
		Connection conn = openNewWrappedConnection();
		busies.incrementAndGet();
		return conn ;
	}

    /**
     *
     */
	public synchronized void backConnection(Connection conn){
		pool.add(conn) ;
		busies.decrementAndGet();
		this.notify();
	}
}
