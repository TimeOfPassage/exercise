package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
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
