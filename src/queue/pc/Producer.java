package queue.pc;

import java.util.UUID;
import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {

	private final BlockingQueue<Object> queue;

	public Producer(BlockingQueue<Object> q) {
		this.queue = q;
	}

	@Override
	public void run() {
		try {
			while (true) {
				queue.put(produce());
				Thread.sleep(800);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private Object produce() {
		User u = new User();
		u.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		u.setUserName(Math.random()+"");
		return u;
	}

}
