package thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorTest {

	public static int count = 0;
	
	public static void main(String[] args) {

		/**
		 * <pre>
		     int corePoolSize,
		     int maximumPoolSize,
		     long keepAliveTime,
		     TimeUnit unit,
		     BlockingQueue<Runnable> workQueue
		 * </pre>
		 * <pre>
		 * 1、任务执行时，先消耗corePoolSize的数量，当corePoolSize数量的线程开辟完毕
		 * 2、将要执行的任务放入workQueue队列中
		 * 3、如果workQueue是一个有边界的队列，workQueue放满时，
		 * 如果corePoolSize!=maximumPoolSize && corePoolSize < maximumPoolSize,
		 * 则继续开辟线程，当线程数量达到最大线程是，如果还有任务要执行，则拒绝处理
		 * </pre>
		 */
		ThreadPoolExecutor pool = new ThreadPoolExecutor(1, 4, 0L, TimeUnit.MILLISECONDS,
				new ArrayBlockingQueue<>(2),new CustomRejectedExecutionHandler());

		pool.execute(()->{
			while(true){
				
			}
		});
		pool.execute(()->{
			System.out.println("Test");
		});
		
//		pool.execute(() -> {
//			System.out.println("ThreadPoolExecutor 1 " + Thread.currentThread().getName());
//			try {
//				Thread.currentThread().sleep(30);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		});
//		pool.execute(() -> {
//			System.out.println("ThreadPoolExecutor 2 " + Thread.currentThread().getName());
//			try {
//				Thread.currentThread().sleep(30);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		});
//		pool.execute(() -> {
//			System.out.println("ThreadPoolExecutor 3 " + Thread.currentThread().getName());
//			try {
//				Thread.currentThread().sleep(30);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		});
//		pool.execute(() -> {
//			System.out.println("ThreadPoolExecutor 4 " + Thread.currentThread().getName());
//			try {
//				Thread.currentThread().sleep(30);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		});
//		pool.execute(() -> {
//			System.out.println("ThreadPoolExecutor 5 " + Thread.currentThread().getName());
//			try {
//				Thread.currentThread().sleep(30);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		});
//		pool.execute(() -> {
//			System.out.println("ThreadPoolExecutor 6 " + Thread.currentThread().getName());
//			try {
//				Thread.currentThread().sleep(30);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		});
//		pool.execute(() -> {
//			System.out.println("ThreadPoolExecutor 7 " + Thread.currentThread().getName());
//			try {
//				Thread.currentThread().sleep(30);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		});
	}

	/**
	 * <pre>
	 * 构造一个固定线程数目的线程池，配置的corePoolSize与maximumPoolSize大小相同，
	 * 同时使用了一个无界LinkedBlockingQueue存放阻塞任务，因此多余的任务将存在再阻塞队列，不会由RejectedExecutionHandler处理
	 * </pre>
	 */
	public static ExecutorService newFixedThreadPool(int nThreads) {
		return new ThreadPoolExecutor(nThreads, nThreads, 0L, TimeUnit.MILLISECONDS,
				new LinkedBlockingQueue<Runnable>());
	}

	/**
	 * <pre>
	 * 构造一个缓冲功能的线程池，配置corePoolSize=0，maximumPoolSize=Integer.MAX_VALUE，keepAliveTime=60s,
	 * 以及一个无容量的阻塞队列 SynchronousQueue，因此任务提交之后，将会创建新的线程执行；线程空闲超过60s将会销毁
	 * </pre>
	 */
	public static ExecutorService newCachedThreadPool() {
		return new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
	}

}
