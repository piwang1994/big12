package com.zk.watcher;

import java.io.IOException;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

public class WatcherExample implements Watcher {
	private String zkpath="localhost:2181";

	public void process(WatchedEvent event) {
		// TODO Auto-generated method stub
		System.out.println("watcher="+this.getClass().getName());
		System.out.println("path="+event.getPath());
		System.out.println("eventType="+event.getType().name());
	
	}
	
	public String getZkpath() {
		return zkpath;
	}

	public void setZkpath(String zkpath) {
		this.zkpath = zkpath;
	}

	public static void main(String[] args){
		WatcherExample  wx = new WatcherExample();
		try {
			ZooKeeper	zk = new ZooKeeper(wx.getZkpath(),10000, wx);
			zk.getChildren("/node7",true);
			Thread.sleep(300000);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeeperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}

}
