package socket.thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * <pre>
 * Ӧ�ö��߳�ʵ�ַ��������ͻ���֮���ͨ��
   �� �������˴���ServerSocket��ѭ������accept()�ȴ��ͻ�������
   �� �ͻ��˴���һ��socket������ͷ�����������
   �� �������˽��ܿ�������󣬴���socket��ÿͻ�����ר������
   �� �������ӵ�����socket��һ���������߳��϶Ի�
   �� �������˼����ȴ��µ�����
 * </pre>
 *
 */
public class ClientThread implements Runnable {

	Socket socket = null;

	public ClientThread(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			// 2 ��ȡ������������˷�����Ϣ
			OutputStream outputStream = socket.getOutputStream();
			PrintWriter writer = new PrintWriter(outputStream);
			writer.write("��ã������ǿͻ��ˡ�");
			writer.flush();

			socket.shutdownOutput();
			// 3 ��ȡ����������ȡ��������Ӧ��Ϣ
			InputStream inputStream = socket.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
			String info = null;
			while ((info = reader.readLine()) != null) {
				System.out.println("���ǿͻ��ˣ���������Ӧ��ϢΪ��" + info);
			}

			// 4 �ر���Դ
			reader.close();
			inputStream.close();
			writer.close();
			outputStream.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
