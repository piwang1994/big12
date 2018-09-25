package com.zk.client;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.ZooKeeper;

public class ZK_GetChildren_Sync implements Watcher {
	private static CountDownLatch connectedSemaphore = new CountDownLatch(1);
	private static ZooKeeper zk = null;
	@Override
	public void process(WatchedEvent event) {
		// TODO Auto-generated method stub
		System.out.println("dddd");
		if(event.getType()==EventType.NodeChildrenChanged){
			List<String> childrenList = null;
			try {
				childrenList = zk.getChildren(event.getPath(), true);
				System.out.println("添加节点后="+childrenList.toString());
				connectedSemaphore.countDown();
			} catch (KeeperException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws Exception{
		String path = "/acl";
		zk = new ZooKeeper("localhost:2181",5000,new ZK_GetChildren_Sync());
		List<String> childrenList = zk.getChildren(path, true);
		System.out.println(childrenList.toString());
		connectedSemaphore.await();
	}
}
