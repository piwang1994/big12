package com.zk.client;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.Watcher.Event.KeeperState;

public class ZKCreateSample_With_SID_PASSWD implements Watcher {
	private static CountDownLatch connectedSemaphore = new CountDownLatch(1);

	public static void main(String[] args) throws IOException, Exception {
		// TODO Auto-generated method stub
		ZooKeeper zookeeper = new ZooKeeper("localhost:2181", 6000,
				new ZKCreateSample_With_SID_PASSWD());
		connectedSemaphore.await();
		long sessionId = zookeeper.getSessionId();
		byte[] passwd = zookeeper.getSessionPasswd();
		Thread.sleep(30000);
		System.out.println("sleep end");
		//zookeeper.;
		//用不合法的sessionID和sessionPasswd创建连接
//		ZooKeeper zookeeper1 = new ZooKeeper("localhost:2181", 6000,
//				new ZKCreateSample_With_SID_PASSWD(),1l,"test".getBytes());
		
		//用合法的sessionID和sessionPasswd创建连接
		ZooKeeper zookeeper2 = new ZooKeeper("localhost:2181", 6000,
				new ZKCreateSample_With_SID_PASSWD(),sessionId,passwd);
		
		//Thread.sleep(3000);
		//System.out.println("begin state="+zookeeper2.getState());
		Thread.sleep(30000);
		//System.out.println("end state=" + zookeeper.getState());
	}


	public void process(WatchedEvent event) {
		// TODO Auto-generated method stub
		System.out.println("receive watched event:" + event);
		if (KeeperState.SyncConnected == event.getState()) {
			connectedSemaphore.countDown();
		}
	}
}
