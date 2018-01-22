package queue.pc;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {

	private final BlockingQueue<Object> queue;

	public Consumer(BlockingQueue<Object> q) {
		this.queue = q;
	}

	@Override
	public void run() {
		try {
			while(true){
					consume(queue.take());
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void consume(Object obj) {
		if(obj instanceof User){
			User u = (User) obj;
			System.out.println(Thread.currentThread().getName() + " -- " + u);
		}
	}
}
