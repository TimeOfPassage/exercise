package jetty;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;

public class JettyTest {
	public static void main(String[] args) {

		Server server = new Server();

		ServerConnector conn = new ServerConnector(server);
		conn.setPort(8888);
		server.setConnectors(new Connector[] { conn });
		
		
		ResourceHandler rHandler = new ResourceHandler();
		rHandler.setDirectoriesListed(true);
		rHandler.setResourceBase("d:/");
//		HandlerList handlerList = new HandlerList();
//		handlerList.setHandlers(new Handler[]{rHandler,new DefaultHandler()});
		server.setHandler(rHandler);
		
		
		//����������
//		ContextHandler ctx = new ContextHandler();
//		ctx.setContextPath("/hello");
//		ctx.setResourceBase(".");
//		ctx.setClassLoader(Thread.currentThread().getContextClassLoader());
//		server.setHandler(ctx);
//		ctx.setHandler(new HelloHandler());
		
		try {
			server.start();
			server.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static class HelloHandler extends AbstractHandler {

		/**
		 * @param target �����Ŀ�꣬��������һ��URI���������ַ���������
		 * @param baseRequest Jetty�ɱ�������������ǽ����
		 * @param request ���ɱ����������������Ѿ�����װ
		 * @param response ��Ӧ���������Ѿ�����װ
		 */
		@Override
		public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
				throws IOException, ServletException {
			response.setContentType("text/html;charset=utf-8");

			response.setStatus(HttpServletResponse.SC_OK);

			baseRequest.setHandled(true);
			
			System.out.println(target);
			System.out.println(baseRequest);
			System.out.println(request);
			System.out.println(response);
			

			response.getWriter().println("<h1>HelloWorld</h1>");
		}
	}
}
