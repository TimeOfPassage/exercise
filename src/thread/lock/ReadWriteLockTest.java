package thread.lock;

public class ReadWriteLockTest {

	public static void main(String[] args) {

		Ticket data = new Ticket();
		/**
		 * ����ͬʱ��ȡ���� ���Ƕ�ȡ���ݵ�ʱ�򣬲�����д�����ݣ���ȴ���ȡ������Ȼ��д������
		 */
		Worker w1 = new Worker(data, false, "����");
		Worker w2 = new Worker(data, false, "����");
		Worker w3 = new Worker(data, true, "����");
		new Thread(w2).start();
		new Thread(w1).start();
		new Thread(w3).start();
	}
}
