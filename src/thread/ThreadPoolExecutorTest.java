package thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorTest {

	public static void main(String[] args) {

		/**
		 * <pre>
		     int corePoolSize,
		     int maximumPoolSize,
		     long keepAliveTime,
		     TimeUnit unit,
		     BlockingQueue<Runnable> workQueue
		 * </pre>
		 */
		ThreadPoolExecutor pool = new ThreadPoolExecutor(3, 3, 0L, TimeUnit.MILLISECONDS,
				new LinkedBlockingQueue<Runnable>());

		pool.execute(() -> {
			System.out.println("ThreadPoolExecutor " + Thread.currentThread().getName());
		});
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
