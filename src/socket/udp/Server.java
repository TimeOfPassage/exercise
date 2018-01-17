package socket.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * <pre>
 * UDP���
       UDPЭ�飨�û����ݱ�Э�飩�������ӵġ����ɿ��ġ������,�ٶȿ�
                    �������ݴ���ʱ�����Ƚ�Ҫ��������ݶ�������ݱ���Datagram������С������64k�������ݱ���ָ��������Ҫ�ﵽ��Socket��������ַ�Ͷ˿ںţ���
                    Ȼ���ٽ����ݱ����ͳ�ȥ
                    
   DatagramPacket��:��ʾ���ݱ���
   DatagramSocket�ࣺ���ж˵���ͨ�ŵ���
   1����������ʵ�ֲ���
       �� ����DatagramSocket��ָ���˿ں�
       �� ����DatagramPacket
       �� ���ܿͻ��˷��͵�������Ϣ
       �� ��ȡ����
 * </pre>
 *
 */
public class Server {

	public static void main(String[] args) {

		//1 
		try {
			DatagramSocket ds = new DatagramSocket(1234);
			//2 �������ݱ� ���ڽ��տͻ�������
			byte[] data = new byte[1024];
			DatagramPacket dp = new DatagramPacket(data, data.length);
			//3 ���տͻ�������
			ds.receive(dp);//�˷����ڽ������ݱ�֮ǰ��һ������
			//4 ��ȡ����
			String info = new String(data,0,data.length);
			System.out.println("���Ƿ��������ͻ��˸����ң�"+info);
			
			
			//===============��ͻ�����Ӧ����
			//1 ����ͻ��˵�ַ| �˿�| ����
			InetAddress address = dp.getAddress();
			int port = dp.getPort();
			byte[] respData = "��ӭ��".getBytes();
			//2 �������ݱ���������Ӧ������
			DatagramPacket dataPacket = new DatagramPacket(respData, respData.length, address, port);
			//3 ��Ӧ�ͻ���
			ds.send(dataPacket);
			//4 �ر���Դ
			ds.close();
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
