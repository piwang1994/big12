package com.zk.client;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooKeeper;

public class ZKCreateSample implements Watcher{
	private static CountDownLatch connectedSemaphore = new CountDownLatch(1);
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ZooKeeper zookeeper = new ZooKeeper("localhost:2181",6000, new ZKCreateSample());
		System.out.println("begin state="+zookeeper.getState());
		try {
			connectedSemaphore.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println("Zookeeper session established.");
		}
		System.out.println("end state="+zookeeper.getState());
	}

	@Override
	public void process(WatchedEvent event) {
		// TODO Auto-generated method stub
		System.out.println("receive watched event:"+event);
		if(KeeperState.SyncConnected==event.getState()){
			connectedSemaphore.countDown();
		}
	}

}
