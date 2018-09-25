package concurrent;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *
 */
public class LockDemo {
	public static void main(String[] args) {
		//创建读写锁
		final ReentrantReadWriteLock lock = new ReentrantReadWriteLock() ;
		new Thread(){
			public void run() {
				lock.readLock().lock();
				System.out.println("1");
				lock.readLock().unlock();
			}
		}.start();
		new Thread() {
			public void run() {
				lock.readLock();
				System.out.println("1");
				lock.readLock().unlock();
			}
		}.start();
	}
}
