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
public class ServerThread implements Runnable {

	Socket socket = null;

	public ServerThread(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		InputStream inputStream;
		try {
			inputStream = socket.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
			String info = null;
			while ((info = br.readLine()) != null) {
				System.out.println("���Ƿ���ˣ��ͻ���˵��" + info);
			}
			socket.shutdownInput();// �ر�������
			OutputStream outputStream = socket.getOutputStream();
			PrintWriter writer = new PrintWriter(outputStream);
			writer.write("��ã���ӭ�����ʡ�");
			writer.flush();
			if("exit".equals(info)){
				writer.close();
				outputStream.close();
				br.close();
				inputStream.close();
				socket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
