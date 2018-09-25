package com.zk.dev.zkClient;

import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;

public class ZkclientTest {
	private ZkClient zkclient = null;
	public ZkclientTest(){
		this.zkclient = new ZkClient("localhost:2181,localhost:2182,localhost:2183",5000);
	}
	
	public void createPersistentNode(String path,Object data){
		//zkclient.createPersistent(path, data);
		zkclient.create(path, data, CreateMode.PERSISTENT);
	}
	public static void main(String[] args) {
		ZkclientTest zt = new ZkclientTest();
		zt.createPersistentNode("/zkclient/node2", "zkclientnode2");
		
	}

}
