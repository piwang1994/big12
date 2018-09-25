package com.zk.dev.curator;

import java.util.List;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCache.StartMode;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;

public class CuratorClientTest {
	private CuratorFramework client = null;

	public CuratorClientTest() {
		RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
		client = CuratorFrameworkFactory.builder()
				.connectString("s202:2181,localhost:2182")
				.sessionTimeoutMs(10000).retryPolicy(retryPolicy)
				.namespace("base").build();
		client.start();
	}

	public void closeClient() {
		if (client != null)
			this.client.close();
	}

	public void createNode(String path, byte[] data) throws Exception {
		client.create().creatingParentsIfNeeded()
				.withMode(CreateMode.PERSISTENT).withACL(Ids.OPEN_ACL_UNSAFE)
				.forPath(path, data);
	}

	public void deleteNode(String path, int version) throws Exception {
		// client.delete().guaranteed().deletingChildrenIfNeeded().withVersion(version).inBackground(new
		// DeleteCallBack()).forPath(path);
		client.delete().guaranteed().deletingChildrenIfNeeded().withVersion(version)
				.inBackground(new DeleteCallBack()).forPath(path);

	}

	public void readNode(String path) throws Exception {
		Stat stat = new Stat();
		byte[] data = client.getData().storingStatIn(stat).forPath(path);
		System.out.println("读取节点" + path + "的数据:" + new String(data));
		System.out.println(stat.toString());
	}

	public void updateNode(String path, byte[] data, int version)
			throws Exception {
		client.setData().withVersion(version).forPath(path, data);
	}

	public void getChildren(String path) throws Exception {
		List<String> children = client.getChildren().usingWatcher(new WatcherTest()).forPath("/curator");
		for (String pth : children) {
			System.out.println("child=" + pth);
		}
	}

	public void addNodeDataWatcher(String path) throws Exception {
		final NodeCache nodeC = new NodeCache(client, path);
		nodeC.start(true);
		nodeC.getListenable().addListener(new NodeCacheListener() {
			public void nodeChanged() throws Exception {
				String data = new String(nodeC.getCurrentData().getData());
				System.out.println("path=" + nodeC.getCurrentData().getPath()
						+ ":data=" + data);
			}
		});
	}
	@Test
	public void addChildWatcher(String path) throws Exception {
		final PathChildrenCache cache = new PathChildrenCache(this.client,
				path, true);
		cache.start(StartMode.POST_INITIALIZED_EVENT);//ppt中需要讲StartMode
		System.out.println(cache.getCurrentData().size());
		//byte childone[] = cache.getCurrentData().get(0).getData();
//		System.out.println("childone:"
//				+ cache.getCurrentData().get(0).getPath() + ";data="
//				+ new String(childone));
		cache.getListenable().addListener(new PathChildrenCacheListener() {
			public void childEvent(CuratorFramework client,
					PathChildrenCacheEvent event) throws Exception {
				if(event.getType().equals(PathChildrenCacheEvent.Type.INITIALIZED)){
					System.out.println("客户端子节点cache初始化数据完成");
					System.out.println("size="+cache.getCurrentData().size());
				}else if(event.getType().equals(PathChildrenCacheEvent.Type.CHILD_ADDED)){
					System.out.println("添加子节点:"+event.getData().getPath());
					System.out.println("修改子节点数据:"+new String(event.getData().getData()));
				}else if(event.getType().equals(PathChildrenCacheEvent.Type.CHILD_REMOVED)){
					System.out.println("删除子节点:"+event.getData().getPath());
				}else if(event.getType().equals(PathChildrenCacheEvent.Type.CHILD_UPDATED)){
					System.out.println("修改子节点数据:"+event.getData().getPath());
					System.out.println("修改子节点数据:"+new String(event.getData().getData()));
				}
			}
		});
	}

	public static void main(String[] args) {
		CuratorClientTest ct = null;
		try {
			ct = new CuratorClientTest();
			// ct.createNode("/curator/test10/node1", "test-node1".getBytes());
			//ct.readNode("/curator/test/node1");
			//ct.getChildren("/curator");
			// ct.updateNode("/curator/test/node1", "test-node1-new".getBytes(),
			// 0);
			// ct.readNode("/curator/test/node1");
			// ct.deleteNode("/curator/test10", 0);

			ct.addNodeDataWatcher("/curator/test7");
			ct.addChildWatcher("/curator");
			Thread.sleep(300000);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ct.closeClient();
		}

	}

}
