package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class ExecutorsTest {

	public static void main(String[] args) {

		// 默认线程工厂
		/*
		 * ThreadFactory threadFactory = Executors.defaultThreadFactory();
		 * Thread thread = threadFactory.newThread(() -> { System.out.println(
		 * "Executors defaultThreadFactory " +
		 * Thread.currentThread().getName()); }); thread.start();
		 */
		// 根据需要创建可重用的线程工厂
		/*
		 * ExecutorService service = Executors.newCachedThreadPool();
		 * service.execute(() -> { System.out.println(
		 * "Executors newCachedThreadPool " + Thread.currentThread().getName());
		 * });
		 */
		// 根据threadfactory创建可重用的线程工厂
		/*
		 * ThreadFactory threadFactory = Executors.defaultThreadFactory();
		 * ExecutorService executorService =
		 * Executors.newCachedThreadPool(threadFactory);
		 * executorService.execute(() -> { System.out.println(
		 * "Executors newCachedThreadPool(ThreadFactory) "
		 * +Thread.currentThread().getName()); });
		 */
		//指定线程池中线程数量
		// ExecutorService service = Executors.newFixedThreadPool(5);
		// service.execute(() ->{
		// System.out.println("Executors
		// newFixedThreadPool(int)"+Thread.currentThread().getName());
		// });
	}

}
