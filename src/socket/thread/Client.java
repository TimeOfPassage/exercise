package socket.thread;

import java.io.IOException;
import java.net.Socket;

/**
 * <pre>
 5���ͻ��ˣ�
     �� ����Socket����ָ����Ҫ���ӵķ������ĵ�ַ�Ͷ˿ں�
     �� ���ӽ�����ͨ���������������˷���������Ϣ
     �� ͨ����������ȡ��������Ӧ����Ϣ
     �� �ر���Ӧ��Դ
 * </pre>
 */
public class Client {
	public static void main(String[] args) {
		try {
			// 1 �����ͻ���Socket
			Socket socket = new Socket("localhost", 1234);
			System.out.println("��ǰ�߳�����"+Thread.currentThread().getName());
			new Thread(new ClientThread(socket)).start();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
