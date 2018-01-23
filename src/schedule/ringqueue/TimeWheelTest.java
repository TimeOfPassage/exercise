package schedule.ringqueue;

public class TimeWheelTest {

	public static void main(String[] args) {

		// System.out.println(System.nanoTime()); //5248283356076
		//
		// System.out.println(System.currentTimeMillis());//1516673749670
		//
		//
		// System.out.println(System.getProperty("os.name")); //Windows 7
		//
		// System.out.println(TimeUnit.SECONDS.toNanos(2));
		//
		// System.out.println(new SimpleDateFormat("yyyy-MM-dd
		// HH:mm:ss").format(new Date()));
//		HashedWheelTimer t = new HashedWheelTimer();
//		TimerTask task1 = new TimerTask() {
//			@Override
//			public void run(Timeout timeout) throws Exception {
//				System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//				timeout.timer().newTimeout(this, 500, TimeUnit.MILLISECONDS);
//			}
//		};
//		t.newTimeout(task1, 500, TimeUnit.MILLISECONDS);
		// Set<String> s = new HashSet<String>();
		// s.add("a");
		// s.add("b");
		// s.add("c");
		// s.add("d");
		// Iterator<String> iterator = s.iterator();
		// while(iterator.hasNext()){
		// String str = iterator.next();
		// if("c".equals(str)){
		// iterator.remove();
		// }
		// }
		// System.out.println(s.size());

		int i = 1;
		int j = 0;
		while (i < 500) {
			i <<= 1;
			j++;
		}
		System.out.println(i);// 512
		System.out.println(j);//9
	}
}
