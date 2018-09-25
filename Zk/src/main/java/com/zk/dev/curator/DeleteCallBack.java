package com.zk.dev.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;

public class DeleteCallBack implements BackgroundCallback {

	public void processResult(CuratorFramework client, CuratorEvent event)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println(event.getPath()+",data="+event.getData());
		System.out.println("event type="+event.getType());
		System.out.println("event code="+event.getResultCode());
		
	}

}
