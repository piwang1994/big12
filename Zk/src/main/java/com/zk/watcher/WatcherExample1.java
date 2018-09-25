package com.zk.watcher;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

public class WatcherExample1 implements Watcher {
	private ZooKeeper  zk = null;

	public void process(WatchedEvent event) {
		// TODO Auto-generated method stub
		System.out.println("watcher="+this.getClass().getName());
		System.out.println("path="+event.getPath());
		System.out.println("eventType="+event.getType().name());
		try {
			//重新设置watcher
			WatcherExample1 we1 = new WatcherExample1();
			we1.setZk(zk);
			zk.getData(event.getPath(), we1, null);
		} catch (KeeperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public ZooKeeper getZk() {
		return zk;
	}
	public void setZk(ZooKeeper zk) {
		this.zk = zk;
	}

	
}
