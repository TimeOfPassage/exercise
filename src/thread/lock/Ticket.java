package thread.lock;

import java.text.SimpleDateFormat;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Ticket {
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	ReadWriteLock lock = new ReentrantReadWriteLock();

	int count = 50;

	Lock read = lock.readLock();

	Lock write = lock.writeLock();

	public void get(String name) {
		// System.out.println(Thread.currentThread().getName()+"::get
		// start::"+sdf.format(new Date()));
		read.lock();
		try {
			Thread.sleep(500);
			System.out.println("վ��"+name+":��ѯ��Ʊʣ�ࣺ"+count);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			read.unlock();
			// System.out.println(Thread.currentThread().getName()+"::get end
			// ::"+sdf.format(new Date()));
		}
	}

	public void set(String name) {
		write.lock();
		try {
			if (count > 0) {
				Thread.sleep(500);
				count--;
				System.out.println(name + ":����Ʊ");
			} else {
				System.out.println("Ʊ�Ѿ�����");
				System.exit(0);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			write.unlock();
		}
	}

}
