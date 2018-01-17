package socket.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * <pre>
 * UDP编程
       UDP协议（用户数据报协议）是无连接的、不可靠的、无序的,速度快
                    进行数据传输时，首先将要传输的数据定义成数据报（Datagram），大小限制在64k，在数据报中指明数据索要达到的Socket（主机地址和端口号），
                    然后再将数据报发送出去
                    
   DatagramPacket类:表示数据报包
   DatagramSocket类：进行端到端通信的类
   1、服务器端实现步骤
       ① 创建DatagramSocket，指定端口号
       ② 创建DatagramPacket
       ③ 接受客户端发送的数据信息
       ④ 读取数据
 * </pre>
 *
 */
public class Server {

	public static void main(String[] args) {

		//1 
		try {
			DatagramSocket ds = new DatagramSocket(1234);
			//2 创建数据报 用于接收客户端数据
			byte[] data = new byte[1024];
			DatagramPacket dp = new DatagramPacket(data, data.length);
			//3 接收客户端数据
			ds.receive(dp);//此方法在接受数据报之前会一致阻塞
			//4 读取数据
			String info = new String(data,0,data.length);
			System.out.println("我是服务器：客户端告诉我："+info);
			
			
			//===============向客户端响应数据
			//1 定义客户端地址| 端口| 数据
			InetAddress address = dp.getAddress();
			int port = dp.getPort();
			byte[] respData = "欢迎您".getBytes();
			//2 创建数据报，包含响应的数据
			DatagramPacket dataPacket = new DatagramPacket(respData, respData.length, address, port);
			//3 响应客户端
			ds.send(dataPacket);
			//4 关闭资源
			ds.close();
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
