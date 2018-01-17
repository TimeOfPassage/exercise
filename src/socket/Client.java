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
