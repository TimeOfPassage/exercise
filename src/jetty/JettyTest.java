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
		
		
		//设置上下文
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
		 * @param target 请求的目标，它可以是一个URI或者命名分发器的名称
		 * @param baseRequest Jetty可变请求对象，它总是解包的
		 * @param request 不可变的请求对象，它可能已经被封装
		 * @param response 响应，它可能已经被封装
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
