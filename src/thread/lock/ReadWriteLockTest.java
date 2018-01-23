package thread.lock;

public class ReadWriteLockTest {

	public static void main(String[] args) {

		Ticket data = new Ticket();
		/**
		 * 可以同时读取数据 但是读取数据的时候，不可以写入数据，需等待读取结束，然后写入数据
		 */
		Worker w1 = new Worker(data, false, "张三");
		Worker w2 = new Worker(data, false, "李四");
		Worker w3 = new Worker(data, true, "王五");
		new Thread(w2).start();
		new Thread(w1).start();
		new Thread(w3).start();
	}
}
