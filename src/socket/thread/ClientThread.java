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
 * 应用多线程实现服务器与多客户端之间的通信
   ① 服务器端创建ServerSocket，循环调用accept()等待客户端连接
   ② 客户端创建一个socket并请求和服务器端连接
   ③ 服务器端接受苦读段请求，创建socket与该客户建立专线连接
   ④ 建立连接的两个socket在一个单独的线程上对话
   ⑤ 服务器端继续等待新的连接
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
			// 2 获取输出流，向服务端发送信息
			OutputStream outputStream = socket.getOutputStream();
			PrintWriter writer = new PrintWriter(outputStream);
			writer.write("你好，这里是客户端。");
			writer.flush();

			socket.shutdownOutput();
			// 3 获取输入流，获取服务器响应信息
			InputStream inputStream = socket.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
			String info = null;
			while ((info = reader.readLine()) != null) {
				System.out.println("我是客户端：服务器响应信息为：" + info);
			}

			// 4 关闭资源
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
