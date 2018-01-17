package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * <title>Socketͨ�Ų���</title>
 * <pre>
     �� ����ServerSocket��Socket
	 �� �����ӵ�Socket������/�����
	 �� ����Э���Socket���ж�/д����
	 �� �ر�������������ر�Socket
   </pre>
   
   <title>�����</title>
   <pre>
     �� ����ServerSocket���󣬰󶨼����˿�
	 �� ͨ��accept()���������ͻ�������
	 �� ���ӽ�����ͨ����������ȡ�ͻ��˷��͵�������Ϣ
	 �� ͨ���������ͻ��˷���������Ϣ
	 �� �ر������Դ
   </pre>
 */
public class Server {

	public static void main(String[] args) {
		try {
			// �����ͨ��
			ServerSocket serverSocket = new ServerSocket(1234);
			Socket socket = serverSocket.accept();
			InputStream inputStream = socket.getInputStream();
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

			writer.close();
			outputStream.close();
			br.close();
			inputStream.close();
			socket.close();
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
