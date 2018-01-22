package queue.pc;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class PCTest {

	public static void main(String[] args) {

		BlockingQueue<Object> bq = new LinkedBlockingQueue<>();
		Producer p = new Producer(bq);
		Consumer c1 = new Consumer(bq);
		Consumer c2 = new Consumer(bq);
		Consumer c3 = new Consumer(bq);
		Consumer c4 = new Consumer(bq);
		Consumer c5 = new Consumer(bq);
		Consumer c6 = new Consumer(bq);

		new Thread(p).start();
		new Thread(c1).start();
		new Thread(c2).start();
		new Thread(c3).start();
		new Thread(c4).start();
		new Thread(c5).start();
		new Thread(c6).start();
		
		

	}

}
