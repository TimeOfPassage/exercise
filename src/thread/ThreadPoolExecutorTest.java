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
	 * ����һ���̶��߳���Ŀ���̳߳أ����õ�corePoolSize��maximumPoolSize��С��ͬ��
	 * ͬʱʹ����һ���޽�LinkedBlockingQueue�������������˶�������񽫴������������У�������RejectedExecutionHandler����
	 * </pre>
	 */
	public static ExecutorService newFixedThreadPool(int nThreads) {
		return new ThreadPoolExecutor(nThreads, nThreads, 0L, TimeUnit.MILLISECONDS,
				new LinkedBlockingQueue<Runnable>());
	}

	/**
	 * <pre>
	 * ����һ�����幦�ܵ��̳߳أ�����corePoolSize=0��maximumPoolSize=Integer.MAX_VALUE��keepAliveTime=60s,
	 * �Լ�һ������������������ SynchronousQueue����������ύ֮�󣬽��ᴴ���µ��߳�ִ�У��߳̿��г���60s��������
	 * </pre>
	 */
	public static ExecutorService newCachedThreadPool() {
		return new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
	}

}
