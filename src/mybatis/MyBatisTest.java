package mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisTest {
	private static String resource = "mybatis/mybatis-config.xml";
	private static String properties = "mybatis/jdbc.properties";
	private static SqlSessionFactory sqlSessionFactory = null;

	static {
		InputStream inputStream = null;
		InputStream inStream = null;
		try {
			inStream = Resources.getResourceAsStream(properties);
			Properties p = new Properties();
			p.load(inStream);
			inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream, p);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				inputStream.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			try {
				inStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {

		SqlSession session = sqlSessionFactory.openSession();
		User user = session.selectOne("mybatis.UserMapper.queryUser", "bca1acfeb2034cf3a595f06a77da7952");
		System.out.println(user);
		UserMapper mapper = session.getMapper(UserMapper.class);
		User user1 = mapper.queryUser("bca1acfeb2034cf3a595f06a77da7952");
		System.out.println(user1);
		User user2 = mapper.queryUser2("bca1acfeb2034cf3a595f06a77da7952");
		System.out.println(user2);

		session.close();
	}
}
