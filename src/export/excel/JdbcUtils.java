package export.excel;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.ibatis.io.Resources;

public class JdbcUtils {
	private static String properties = "mybatis/jdbc.properties";
	private static String url = null;
	private static String user = null;
	private static String password = null;
	static {
		InputStream inStream = null;
		try {
			inStream = Resources.getResourceAsStream(properties);
			Properties p = new Properties();
			p.load(inStream);
			url = p.getProperty("url");
			user = p.getProperty("user");
			password = p.getProperty("password");
			Class.forName(p.getProperty("driver"));
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				inStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static Connection getConn() throws SQLException {
		return DriverManager.getConnection(url, user, password);
	}
}
