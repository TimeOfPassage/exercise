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
 * <title>Socket通信步骤</title>
 * <pre>
     ① 创建ServerSocket和Socket
	 ② 打开连接到Socket的输入/输出流
	 ③ 按照协议对Socket进行读/写操作
	 ④ 关闭输入输出流、关闭Socket
   </pre>
   
   <title>服务端</title>
   <pre>
     ① 创建ServerSocket对象，绑定监听端口
	 ② 通过accept()方法监听客户端请求
	 ③ 连接建立后，通过输入流读取客户端发送的请求信息
	 ④ 通过输出流向客户端发送乡音信息
	 ⑤ 关闭相关资源
   </pre>
 */
public class Server {

	public static void main(String[] args) {
		try {
			// 服务端通信
			ServerSocket serverSocket = new ServerSocket(1234);
			Socket socket = serverSocket.accept();
			InputStream inputStream = socket.getInputStream();
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
