package queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BlockingQueueTest {

	public static void main(String[] args) {
		
		BlockingQueue<String> bq = new LinkedBlockingQueue<>();
		
		boolean add = bq.add("aaaaa");
		System.out.println(add);
		System.out.println(bq.size());
		System.out.println("Examine "+bq.element());
		boolean remove = bq.remove("aaaaa");
		System.out.println(remove);
		System.out.println(bq.size());
		
		boolean offer = bq.offer("b");
		System.out.println(offer);
		System.out.println(bq.size());
		System.out.println("Examine "+bq.peek());
		String poll = bq.poll();
		System.out.println(poll);
		System.out.println(bq.size());
		
	}
}
