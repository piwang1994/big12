package com.zk.client;

import java.security.NoSuchAlgorithmException;

import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;

public class DigestTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			System.out.print(DigestAuthenticationProvider.generateDigest("javaclient2:111111"));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
