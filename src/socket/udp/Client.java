package socket.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * 
 * <pre>
 * 客户端实现步骤
	   ① 定义发送信息
	   ② 创建DatagramPacket，包含将要发送的信息
	   ③ 创建DatagramSocket
	   ④ 发送数据
 * </pre>
 * 
 * 
 * <pre>
 * 注意问题：

     1、多线程的优先级问题：
                        根据实际的经验，适当的降低优先级，否侧可能会有程序运行效率低的情况
    2、是否关闭输出流和输入流：
                         对于同一个socket，如果关闭了输出流，则与该输出流关联的socket也会被关闭，所以一般不用关闭流，直接关闭socket即可
     3、使用TCP通信传输对象，IO中序列化部分
     4、socket编程传递文件，IO流部分
 * 
 * 
 * </pre>
 *
 */
public class Client {

	public static void main(String[] args) {

		try {
			// 客户端
			// 1、定义服务器的地址、端口号、数据
			InetAddress address = InetAddress.getByName("localhost");
			int port = 1234;
			byte[] data = "用户名：admin;密码：123".getBytes();
			// 2、创建数据报，包含发送的数据信息
			DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
			// 3、创建DatagramSocket对象
			DatagramSocket socket = new DatagramSocket();
			// 4、向服务器发送数据
			socket.send(packet);

			// 接受服务器端响应数据
			// ======================================
			// 1、创建数据报，用于接受服务器端响应数据
			byte[] data2 = new byte[1024];
			DatagramPacket packet2 = new DatagramPacket(data2, data2.length);
			// 2、接受服务器响应的数据
			socket.receive(packet2);
			String reply = new String(data2, 0, packet2.getLength());
			System.out.println("我是客户端，服务器说：" + reply);
			// 4、关闭资源
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
