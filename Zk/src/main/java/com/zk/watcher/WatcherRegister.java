package com.zk.watcher;

import java.io.IOException;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

public class WatcherRegister {
	private ZooKeeper  zk = null;
	
	public WatcherRegister(String connectString,Watcher watcher) {
		try {
			zk = new ZooKeeper(connectString,10000,watcher);
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	public void testWatcherdisabled(String path) throws KeeperException, InterruptedException{
		WatcherExample1 we1 = new WatcherExample1();
		we1.setZk(zk);
		zk.getData(path, we1, null);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WatcherExample we = new WatcherExample();
		WatcherRegister wr = new WatcherRegister("localhost:2181",we);
		try {
			wr.testWatcherdisabled("/node7");
			Thread.sleep(300000);
		} catch (KeeperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
