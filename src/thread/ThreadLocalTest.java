package thread;

import java.util.HashMap;

public class ThreadLocalTest {

	private static final ThreadLocal<HashMap<String, Object>> threadLocal = new ThreadLocal<HashMap<String, Object>>(){
		@Override
		protected HashMap<String, Object> initialValue() {
			return new HashMap<String, Object>();
		}
	};

	public static void main(String[] args) {

		HashMap<String, Object> map = threadLocal.get();

		map.put("key", "value");

		
		System.out.println(map.get("key"));
		
	}

}
