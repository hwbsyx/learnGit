package concurrent;

import java.util.concurrent.TimeUnit;


/**
 *  只对写操作加锁，读操作没有加锁，会产生脏读问题：即一个线程读取了其他线程修改了但是还没提交的数据。
 *  本例对写操作睡2秒。读取操作没有加锁，会产生脏读问题。
 *
 *  对读写方法都加锁，加在方法上默认是对this加锁，此时this都是a对象，当写操作没有完成时不会释放锁，读操作拿不到锁就会成为堵塞状态
 *  *
 */
public class Account {
	String name;
	double balance;
	
	public synchronized void set(String name, double balance) {
		this.name = name;
		/*try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/

		this.balance = balance;
	}
	
	public /*synchronized*/ double getBalance(String name) {
		return this.balance;
	}
	
	
	public static void main(String[] args) {
		Account a = new Account();
		new Thread(()->a.set("zhangsan", 100.0)).start();
		
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(a.getBalance("zhangsan"));
		
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(a.getBalance("zhangsan"));
	}
}