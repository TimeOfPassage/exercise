package sort;

public class QuickSort {

	public static void main(String[] args) {
		int array[] = { 10, 5, 3, 1, 7, 2, 8 };
		System.out.println("����֮ǰ��");
		for (int element : array) {
			System.out.print(element + " ");
		}

		quickCore(array, 0, array.length - 1);

		System.out.println("\n����֮��");
		for (int element : array) {
			System.out.print(element + " ");
		}
	}

	private static void quickCore(int[] array, int left, int right) {
		int i, j, standard;
		if (left > right)
			return;
		standard = array[left]; // ���������һ��Ԫ����Ϊ��׼ֵ
		i = left;
		j = right;

		// ���������߽���ɨ�裬ֱ��left = right
		while (i != j) {
			// ��������ɨ�裬�ҵ���һ���Ȼ�׼Ԫ��С��Ԫ��
			while (i < j && array[j] >= standard) {
				// �ҵ�����Ԫ��arr[right]����arr[left]����
				j--;
			}
			// ��������ɨ�裬�ҵ���һ���Ȼ�׼Ԫ�ش��Ԫ��
			while (i < j && array[i] <= standard) {
				// �ҵ�����Ԫ��arr[left]����arr[right]����
				i++;
			}
			if (i < j) {
				swap(i, j, array);
			}
		}
		// ��׼ֵ��λ
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
