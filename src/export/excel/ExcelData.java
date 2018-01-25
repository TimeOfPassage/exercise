package export.excel;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.eclipse.jetty.util.thread.ExecutorThreadPool;

public class ExcelData {

	// 准备入库队列
	public static Queue<List<ImportData>> intoDBQueue = new ConcurrentLinkedQueue<>();

	// 线程池
	public static ExecutorThreadPool pool = new ExecutorThreadPool();
}
