package export.excel;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.eclipse.jetty.util.thread.ExecutorThreadPool;

public class ExcelData {

	// ׼��������
	public static Queue<List<ImportData>> intoDBQueue = new ConcurrentLinkedQueue<>();

	// �̳߳�
	public static ExecutorThreadPool pool = new ExecutorThreadPool();
}
