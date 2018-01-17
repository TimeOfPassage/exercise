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
				System.out.println("我是服务端，客户端说：" + info);
			}
			socket.shutdownInput();// 关闭输入流
			OutputStream outputStream = socket.getOutputStream();
			PrintWriter writer = new PrintWriter(outputStream);
			writer.write("你好：欢迎您访问。");
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
