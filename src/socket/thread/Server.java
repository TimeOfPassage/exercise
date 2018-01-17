package socket.thread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * <title>Socketͨ�Ų���</title>
 * 
 * <pre>
     �� ����ServerSocket��Socket
	 �� �����ӵ�Socket������/�����
	 �� ����Э���Socket���ж�/д����
	 �� �ر�������������ر�Socket
 * </pre>
 * 
 * <title>�����</title>
 * 
 * <pre>
   �� ����ServerSocket���󣬰󶨼����˿�
 �� ͨ��accept()���������ͻ�������
 �� ���ӽ�����ͨ����������ȡ�ͻ��˷��͵�������Ϣ
 �� ͨ���������ͻ��˷���������Ϣ
 �� �ر������Դ
 * </pre>
 */
public class Server {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		try {
			// �����ͨ��
			@SuppressWarnings("resource")
			ServerSocket serverSocket = new ServerSocket(1234);
			Socket socket = null;
			System.out.println("��ǰ�߳�����"+Thread.currentThread().getName());
			int count = 0;
			while (true) {
				socket = serverSocket.accept();
				ServerThread st = new ServerThread(socket);
				new Thread(st).start();
				count++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
