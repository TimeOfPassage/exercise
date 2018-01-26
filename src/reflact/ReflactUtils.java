package reflact;

public class ReflactUtils {

	public static void main(String[] args) {
		print("a","1",'z',true);
	}

	private static void print(Object... args) {
		for (Object obj : args) {
			System.out.print(obj + "\t");
		}
		System.out.println();
	}
}
