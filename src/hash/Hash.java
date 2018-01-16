package hash;

public class Hash {
	public static void main(String[] args) {

		String uid = "20180110";
		int[] hh = new int[10];
		for (int i = 0; i < 50000; i++) {
			uid += "" + i;
			int h = hash(uid, 10);
			hh[h] += 1;
		}

		for (int i = 0; i < hh.length; i++) {
			System.out.println(hh[i]);
		}

		// int h = (h = uid.hashCode()) ^ (h >>> 16);
		// System.out.println(h);
//		System.out.println(UUID.randomUUID().toString().replaceAll("-", ""));
		
	}

	/**
	 * 散列算法 
	 * 散列表每个关键字被映射到0到TableSize-1这个范围中的某个值，这个映射叫做散列函数
	 * MAD法:hash(key) = (a * key + b ) % M M为素数、a > 0 、b > 0、 a % m != 0
	 */
	public static int hash(String key, int tablesize) {
		int hashVal = 0;
		for (int i = 0; i < key.length(); i++) {
			// 此处是ASCII值想加
			hashVal = 20 * hashVal + key.charAt(i);
		}
		hashVal = hashVal % tablesize;
		if (hashVal < 0) {
			hashVal += tablesize;
		}
		return hashVal;
	}
}
