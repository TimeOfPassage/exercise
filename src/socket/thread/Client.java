package socket.thread;

import java.io.IOException;
import java.net.Socket;

/**
 * <pre>
 5、客户端：
     ① 创建Socket对象，指明需要连接的服务器的地址和端口号
     ② 连接建立后，通过输出流想服务器端发送请求信息
     ③ 通过输入流获取服务器响应的信息
     ④ 关闭响应资源
 * </pre>
 */
public class Client {
	public static void main(String[] args) {
		try {
			// 1 创建客户端Socket
			Socket socket = new Socket("localhost", 1234);
			System.out.println("当前线程名："+Thread.currentThread().getName());
			new Thread(new ClientThread(socket)).start();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
