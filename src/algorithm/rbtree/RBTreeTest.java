package algorithm.rbtree;

/**
 * <pre>
 * 	https://www.cnblogs.com/skywang12345/p/3245399.html
 * 
	R-B Tree��ȫ����Red-Black Tree���ֳ�Ϊ�������������һ������Ķ�����������������ÿ���ڵ��϶��д洢λ��ʾ�ڵ����ɫ�������Ǻ�(Red)���(Black)��
	�����������:
	��1��ÿ���ڵ�����Ǻ�ɫ�������Ǻ�ɫ��
	��2�����ڵ��Ǻ�ɫ��
	��3��ÿ��Ҷ�ӽڵ㣨NIL���Ǻ�ɫ�� [ע�⣺����Ҷ�ӽڵ㣬��ָΪ��(NIL��NULL)��Ҷ�ӽڵ㣡]
	��4�����һ���ڵ��Ǻ�ɫ�ģ��������ӽڵ�����Ǻ�ɫ�ġ�
	��5����һ���ڵ㵽�ýڵ������ڵ������·���ϰ�����ͬ��Ŀ�ĺڽڵ㡣
	
	ע�⣺
	(01) ����(3)�е�Ҷ�ӽڵ㣬��ֻΪ��(NIL��null)�Ľڵ㡣
	(02) ����(5)��ȷ��û��һ��·���������·����������������������������ǽӽ�ƽ��Ķ�������
	
	ͼƬʾ����https://images0.cnblogs.com/i/497634/201403/251730074203156.jpg
	
	ʱ�临�Ӷ��ǣ�O(lgn)
	
	Java�����е�TreeSet��TreeMap ���Ǻ����ʵ��
	
	��������(һ) ����������      https://images0.cnblogs.com/i/497634/201403/251733282013849.jpg
	
	��������(��) ���
	
			��һ��: �����������һ�Ŷ�������������ڵ���롣
			�ڶ�����������Ľڵ���ɫΪ"��ɫ"��
			������: ͨ��һϵ�е���ת����ɫ�Ȳ�����ʹ֮���³�Ϊһ�ź������
	
	��������(��) ɾ��
	
			��һ���������������һ�Ŷ�������������ڵ�ɾ����
			�ڶ�����ͨ��"��ת��������ɫ"��һϵ��������������ʹ֮���³�Ϊһ�ú������
 * 
 * </pre>
 */
public class RBTreeTest {
	private static final int a[] = { 10, 40, 30, 60, 90, 70, 20, 50, 80 };
	private static final boolean mDebugInsert = true; // "����"�����ļ�⿪��(false���رգ�true����)
	private static final boolean mDebugDelete = false; // "ɾ��"�����ļ�⿪��(false���رգ�true����)

	public static void main(String[] args) {
		int i, ilen = a.length;
		RBTree<Integer> tree = new RBTree<Integer>();

		System.out.printf("== ԭʼ����: ");
		for (i = 0; i < ilen; i++)
			System.out.printf("%d ", a[i]);
		System.out.printf("\n");

		for (i = 0; i < ilen; i++) {
			tree.insert(a[i]);
			// ����mDebugInsert=true,����"��Ӻ���"
			if (mDebugInsert) {
				System.out.printf("== ��ӽڵ�: %d\n", a[i]);
				System.out.printf("== ������ϸ��Ϣ: \n");
				tree.print();
				System.out.printf("\n");
			}
		}

		System.out.printf("== ǰ�����: ");
		tree.preOrder();

		System.out.printf("\n== �������: ");
		tree.inOrder();

		System.out.printf("\n== �������: ");
		tree.postOrder();
		System.out.printf("\n");

		System.out.printf("== ��Сֵ: %s\n", tree.minimum());
		System.out.printf("== ���ֵ: %s\n", tree.maximum());
		System.out.printf("== ������ϸ��Ϣ: \n");
		tree.print();
		System.out.printf("\n");

		// ����mDebugDelete=true,����"ɾ������"
		if (mDebugDelete) {
			for (i = 0; i < ilen; i++) {
				tree.remove(a[i]);

				System.out.printf("== ɾ���ڵ�: %d\n", a[i]);
				System.out.printf("== ������ϸ��Ϣ: \n");
				tree.print();
				System.out.printf("\n");
			}
		}

		// ���ٶ�����
		tree.clear();
	}
}
