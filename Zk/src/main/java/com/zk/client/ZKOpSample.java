package com.zk.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooDefs.Perms;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;

public class ZKOpSample {
	private ZooKeeper zk = null;

	public ZKOpSample(String connectString) {
		try {
			zk = new ZooKeeper(connectString, 1000, null);
		} catch (IOException e) {
			e.printStackTrace();
			if (zk != null) {
				try {
					zk.close();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}

	public ZooKeeper getZk() {
		return zk;
	}

	public void setZk(ZooKeeper zk) {
		this.zk = zk;
	}


	public String testCreateNode(String path, byte[] data, List<ACL> acls) {
		String res = "";
		try {
			// zk.addAuthInfo("auth", "javaclient1:111111".getBytes());
			res = zk.create(path, data, acls, CreateMode.PERSISTENT);
			System.out.println("创建节点" + res + "成功");
		} catch (KeeperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	/**
	 * 异步删除节点
	 * 
	 * @param path
	 * @return
	 */
	public boolean deleteNodeWithAsync(String path,int version) {
		String context ="上下文对象测试";
		System.out.println("删除");
		zk.delete(path, version, new DeleteCallBack(), context);
		return true;
	}

	public List<ACL> getIpAcl() {
		List<ACL> acls = new ArrayList<ACL>();
		Id ipId = new Id("ip", "127.0.0.1");
		acls.add(new ACL(Perms.ALL, ipId));
		return acls;
	}

	public List<ACL> getDigestAcl() {
		List<ACL> acls = new ArrayList<ACL>();
		Id digestId = new Id("digest",
				"javaclient2:CGf2ylBdcKMdCYuzd08lQfOPvN0=");
		acls.add(new ACL(Perms.ALL, digestId));
		return acls;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ZKOpSample zks = new ZKOpSample("localhost:2181");
		// 所有人都可以访问的节点
		zks.testCreateNode("/acl/javaclient/node1",
		 "node1data".getBytes(),Ids.OPEN_ACL_UNSAFE);

	
		// 只有被认证过的人都可以访问
//		 zks.getZk().addAuthInfo("digest", "javaclient1:111111".getBytes());
//		 zks.testCreateNode("/acl/javaclient/node2",
//		 "node2data".getBytes(),Ids.CREATOR_ALL_ACL);

		// zks.testCreateNode("/acl/javaclient/node3",
		// "node3data".getBytes(),zks.getIpAcl());

  		zks.testCreateNode("/acl/javaclient/node5", "node5data".getBytes(),
				zks.getDigestAcl());
		try{
		zks.getZk().addAuthInfo("digest", "javaclient2:111111".getBytes());
		zks.deleteNodeWithAsync("/acl/javaclient/node5", 0);
		
		Thread.sleep(30000);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
