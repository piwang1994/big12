package com.zk.dev.curator;

import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.zookeeper.WatchedEvent;

public class WatcherTest implements CuratorWatcher {

	public void process(WatchedEvent event) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(event.getPath());
	}

}
