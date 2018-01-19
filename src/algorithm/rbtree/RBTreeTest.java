package algorithm.rbtree;

/**
 * <pre>
 * 	https://www.cnblogs.com/skywang12345/p/3245399.html
 * 
	R-B Tree，全称是Red-Black Tree，又称为“红黑树”，它一种特殊的二叉查找树。红黑树的每个节点上都有存储位表示节点的颜色，可以是红(Red)或黑(Black)。
	红黑树的特性:
	（1）每个节点或者是黑色，或者是红色。
	（2）根节点是黑色。
	（3）每个叶子节点（NIL）是黑色。 [注意：这里叶子节点，是指为空(NIL或NULL)的叶子节点！]
	（4）如果一个节点是红色的，则它的子节点必须是黑色的。
	（5）从一个节点到该节点的子孙节点的所有路径上包含相同数目的黑节点。
	
	注意：
	(01) 特性(3)中的叶子节点，是只为空(NIL或null)的节点。
	(02) 特性(5)，确保没有一条路径会比其他路径长出俩倍。因而，红黑树是相对是接近平衡的二叉树。
	
	图片示例：https://images0.cnblogs.com/i/497634/201403/251730074203156.jpg
	
	时间复杂度是：O(lgn)
	
	Java集合中的TreeSet和TreeMap 都是红黑树实现
	
	基本操作(一) 左旋和右旋      https://images0.cnblogs.com/i/497634/201403/251733282013849.jpg
	
	基本操作(二) 添加
	
			第一步: 将红黑树当作一颗二叉查找树，将节点插入。
			第二步：将插入的节点着色为"红色"。
			第三步: 通过一系列的旋转或着色等操作，使之重新成为一颗红黑树。
	
	基本操作(三) 删除
	
			第一步：将红黑树当作一颗二叉查找树，将节点删除。
			第二步：通过"旋转和重新着色"等一系列来修正该树，使之重新成为一棵红黑树。
 * 
 * </pre>
 */
public class RBTreeTest {
	private static final int a[] = { 10, 40, 30, 60, 90, 70, 20, 50, 80 };
	private static final boolean mDebugInsert = true; // "插入"动作的检测开关(false，关闭；true，打开)
	private static final boolean mDebugDelete = false; // "删除"动作的检测开关(false，关闭；true，打开)

	public static void main(String[] args) {
		int i, ilen = a.length;
		RBTree<Integer> tree = new RBTree<Integer>();

		System.out.printf("== 原始数据: ");
		for (i = 0; i < ilen; i++)
			System.out.printf("%d ", a[i]);
		System.out.printf("\n");

		for (i = 0; i < ilen; i++) {
			tree.insert(a[i]);
			// 设置mDebugInsert=true,测试"添加函数"
			if (mDebugInsert) {
				System.out.printf("== 添加节点: %d\n", a[i]);
				System.out.printf("== 树的详细信息: \n");
				tree.print();
				System.out.printf("\n");
			}
		}

		System.out.printf("== 前序遍历: ");
		tree.preOrder();

		System.out.printf("\n== 中序遍历: ");
		tree.inOrder();

		System.out.printf("\n== 后序遍历: ");
		tree.postOrder();
		System.out.printf("\n");

		System.out.printf("== 最小值: %s\n", tree.minimum());
		System.out.printf("== 最大值: %s\n", tree.maximum());
		System.out.printf("== 树的详细信息: \n");
		tree.print();
		System.out.printf("\n");

		// 设置mDebugDelete=true,测试"删除函数"
		if (mDebugDelete) {
			for (i = 0; i < ilen; i++) {
				tree.remove(a[i]);

				System.out.printf("== 删除节点: %d\n", a[i]);
				System.out.printf("== 树的详细信息: \n");
				tree.print();
				System.out.printf("\n");
			}
		}

		// 销毁二叉树
		tree.clear();
	}
}
