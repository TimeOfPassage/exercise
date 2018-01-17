package socket.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * 
 * <pre>
 * �ͻ���ʵ�ֲ���
	   �� ���巢����Ϣ
	   �� ����DatagramPacket��������Ҫ���͵���Ϣ
	   �� ����DatagramSocket
	   �� ��������
 * </pre>
 * 
 * 
 * <pre>
 * ע�����⣺

     1�����̵߳����ȼ����⣺
                        ����ʵ�ʵľ��飬�ʵ��Ľ������ȼ��������ܻ��г�������Ч�ʵ͵����
    2���Ƿ�ر����������������
                         ����ͬһ��socket������ر��������������������������socketҲ�ᱻ�رգ�����һ�㲻�ùر�����ֱ�ӹر�socket����
     3��ʹ��TCPͨ�Ŵ������IO�����л�����
     4��socket��̴����ļ���IO������
 * 
 * 
 * </pre>
 *
 */
public class Client {

	public static void main(String[] args) {

		try {
			// �ͻ���
			// 1������������ĵ�ַ���˿ںš�����
			InetAddress address = InetAddress.getByName("localhost");
			int port = 1234;
			byte[] data = "�û�����admin;���룺123".getBytes();
			// 2���������ݱ����������͵�������Ϣ
			DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
			// 3������DatagramSocket����
			DatagramSocket socket = new DatagramSocket();
			// 4�����������������
			socket.send(packet);

			// ���ܷ���������Ӧ����
			// ======================================
			// 1���������ݱ������ڽ��ܷ���������Ӧ����
			byte[] data2 = new byte[1024];
			DatagramPacket packet2 = new DatagramPacket(data2, data2.length);
			// 2�����ܷ�������Ӧ������
			socket.receive(packet2);
			String reply = new String(data2, 0, packet2.getLength());
			System.out.println("���ǿͻ��ˣ�������˵��" + reply);
			// 4���ر���Դ
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
