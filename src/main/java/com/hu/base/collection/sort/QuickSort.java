package com.hu.base.collection.sort;

/**
 * 快速排序
 *
 * @Author hutiantian
 * @Date 2018/10/12 14:07:26
 */
public class QuickSort {

    /**
     * 快速排序是用二分法对冒泡排序的一种改进，采用递归分而治之，
     * 每次按基准数把无序部分拆分成两份，直至无法拆分，思路：
     * 步骤一：找到一个基准数x，一般为数组的第一个元素
     * 步骤二：从数组（基准数之外）左起left找第一个x大的，右起right找比x小的，然后两两交换，直至left>=right
     * 步骤三：继续步骤一、步骤二，直至数组的无序部分大小为0
     * （有序部分在数组的后面，每一趟序有序部分+1）
     * <p>
     * 冒泡排序优点：原理简单，可以用稳定性算法实现。优化后最好情况（有序数组）复杂度是 O(N)
     * 缺点：效率很低，时间复杂度基本为 O(N²)
     * 特点：元素的比较次数与元素的初始排序有关（优化后的算法），可稳定。
     */
    public static void bubbleSort(int a[]) throws Exception {
        if (a == null) {
            throw new Exception("inValid input ,please check your input array!");
        }
        for (int i = 0; i < a.length - 1; i++) {                  //外层遍历n-1次
            for (int j = 0; j < a.length - 1 - i; j++) {            //内层需比较n-1-i次
                if (a[j] > a[j + 1]) {
                    CommonSortUtil.swap(a, j, j + 1);
                }
            }
        }
    }
}
