package sort;

public class QuickSort {

	public static void main(String[] args) {
		int array[] = { 10, 5, 3, 1, 7, 2, 8 };
		System.out.println("排序之前：");
		for (int element : array) {
			System.out.print(element + " ");
		}

		quickCore(array, 0, array.length - 1);

		System.out.println("\n排序之后：");
		for (int element : array) {
			System.out.print(element + " ");
		}
	}

	private static void quickCore(int[] array, int left, int right) {
		int i, j, standard;
		if (left > right)
			return;
		standard = array[left]; // 待排数组第一个元素作为基准值
		i = left;
		j = right;

		// 从左右两边交替扫描，直到left = right
		while (i != j) {
			// 从右往左扫描，找到第一个比基准元素小的元素
			while (i < j && array[j] >= standard) {
				// 找到这种元素arr[right]后与arr[left]交换
				j--;
			}
			// 从左往右扫描，找到第一个比基准元素大的元素
			while (i < j && array[i] <= standard) {
				// 找到这种元素arr[left]后，与arr[right]交换
				i++;
			}
			if (i < j) {
				swap(i, j, array);
			}
		}
		// 基准值归位
		array[left] = array[i];
		array[i] = standard;
		quickCore(array, left, i - 1);
		quickCore(array, i + 1, right);
	}

	private static void swap(int i, int j, int[] arr) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
