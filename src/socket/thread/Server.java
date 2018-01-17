package socket.thread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * <title>Socket通信步骤</title>
 * 
 * <pre>
     ① 创建ServerSocket和Socket
	 ② 打开连接到Socket的输入/输出流
	 ③ 按照协议对Socket进行读/写操作
	 ④ 关闭输入输出流、关闭Socket
 * </pre>
 * 
 * <title>服务端</title>
 * 
 * <pre>
   ① 创建ServerSocket对象，绑定监听端口
 ② 通过accept()方法监听客户端请求
 ③ 连接建立后，通过输入流读取客户端发送的请求信息
 ④ 通过输出流向客户端发送乡音信息
 ⑤ 关闭相关资源
 * </pre>
 */
public class Server {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		try {
			// 服务端通信
			@SuppressWarnings("resource")
			ServerSocket serverSocket = new ServerSocket(1234);
			Socket socket = null;
			System.out.println("当前线程名："+Thread.currentThread().getName());
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
