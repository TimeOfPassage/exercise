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
		 * 1������ִ��ʱ��������corePoolSize����������corePoolSize�������߳̿������
		 * 2����Ҫִ�е��������workQueue������
		 * 3�����workQueue��һ���б߽�Ķ��У�workQueue����ʱ��
		 * ���corePoolSize!=maximumPoolSize && corePoolSize < maximumPoolSize,
		 * ����������̣߳����߳������ﵽ����߳��ǣ������������Ҫִ�У���ܾ�����
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
