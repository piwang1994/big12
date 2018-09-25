package com.zk.client;

import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.data.Stat;

public class ZK_GetData_Sync implements Watcher {
	private static CountDownLatch connectedSemaphore = new CountDownLatch(1);
	private static ZooKeeper zk = null;
	private static Stat stat = new Stat();
	@Override
	public void process(WatchedEvent event) {
		if(event.getType()==EventType.NodeDataChanged){
			
			try {
				byte data[] = zk.getData(event.getPath(), true, stat);
				System.out.println("添加节点后="+new String(data));
				System.out.println("添加节点后 dataversion="+stat.getVersion());
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

	public static void main(String[] args) throws Exception {
		String path = "/acl/node10";
		zk = new ZooKeeper("localhost:2181",5000,new ZK_GetData_Sync());
		byte data[] = zk.getData(path,true, stat);
		System.out.println("before="+new String(data));
		System.out.println("before dataversion="+stat.getVersion());
		connectedSemaphore.await();
		
	}

}
