package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class ExecutorsTest {

	public static void main(String[] args) {

		// Ĭ���̹߳���
		/*
		 * ThreadFactory threadFactory = Executors.defaultThreadFactory();
		 * Thread thread = threadFactory.newThread(() -> { System.out.println(
		 * "Executors defaultThreadFactory " +
		 * Thread.currentThread().getName()); }); thread.start();
		 */
		// ������Ҫ���������õ��̹߳���
		/*
		 * ExecutorService service = Executors.newCachedThreadPool();
		 * service.execute(() -> { System.out.println(
		 * "Executors newCachedThreadPool " + Thread.currentThread().getName());
		 * });
		 */
		// ����threadfactory���������õ��̹߳���
		/*
		 * ThreadFactory threadFactory = Executors.defaultThreadFactory();
		 * ExecutorService executorService =
		 * Executors.newCachedThreadPool(threadFactory);
		 * executorService.execute(() -> { System.out.println(
		 * "Executors newCachedThreadPool(ThreadFactory) "
		 * +Thread.currentThread().getName()); });
		 */
		//ָ���̳߳����߳�����
		// ExecutorService service = Executors.newFixedThreadPool(5);
		// service.execute(() ->{
		// System.out.println("Executors
		// newFixedThreadPool(int)"+Thread.currentThread().getName());
		// });
	}

}
