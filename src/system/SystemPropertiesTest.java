package system;

import java.util.Properties;
import java.util.Set;

public class SystemPropertiesTest {
	public static void main(String[] args) {

		Properties properties = System.getProperties();
		Set<Object> keySet = properties.keySet();
		for (Object obj : keySet) {
			System.out.println(obj + " -- " + properties.getProperty(obj + ""));
		}
	}
}
