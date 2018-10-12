package com.hu.base.collection.sort;

/**
 * 插入排序
 *
 * @Author hutiantian
 * @Date 2018/10/11 14:07:42
 */
public class InsertSort {

    /**
     * 直接插入排序思路：
     * 步骤一.从前往后选择数组无序部分的第一个元素x
     * 步骤二.在有序数组中从后往前扫描，若x小于有序数组的某个元素j，则将该元素后移(a[j+1]=a[j]);否则将x插入j位置
     * 步骤三：重复步骤二，直到数组的最后一个元素
     * 有序部分在数组前面，每一趟序有序部分+1
     * <p>
     * 简单选择排序优点：原理简单，最好时间复杂度为O(N)，稳定
     * 缺点：可能会频繁移动
     * 特点：元素的比较次数与元素的初始排序有关，稳定。
     */
    public static void insertSort(int a[]) throws Exception {
        if (a == null) {
            throw new Exception("inValid input ,please check your input array!");
        }
        int j, x;
        for (int i = 1; i < a.length; i++) {        //初始状态认为第一个元素为有序序列
            x = a[i];
            for (j = i - 1; j >= 0 && a[j] > x; j--) {         //这里必须用x暂存a[i]，因为a[j+1]会把a[i]的值覆盖
                a[j + 1] = a[j];
            }
            a[j + 1] = x;         //这里j在出for循环的时候已经做-1处理了，所以需要重写j+1的值
        }
    }

    /**
     * 优化插入排序思路--二分插入排序：
     * 思路：直接插入排序是从后往前一个个判断，并一次次移动元素x；
     * 二分插入可以从有序的中间开始判断，确定x在有序数组的位置，
     * 整体一次性往后移动大于x的有序数组，然后将x插入
     *
     * 特点：没有减少时间复杂度，对基本有序（增序）的反而会降低效率。
     */
    public static void binaryInsertSort(int a[]) throws Exception {
        if (a == null) {
            throw new Exception("inValid input ,please check your input array!");
        }
        int x, left, right;
        for (int i = 1; i < a.length; i++) {        //初始状态认为第一个元素为有序序列
            x = a[i];                               //暂存a[i]的值，[left,i-1]右移会覆盖a[i]的值
            left = 0;
            right = i - 1;
            while (left <= right) {                     //每次用有序数组的中间元素与x比较，提高效率
                int mid = (left + right) / 2;
                if (a[mid] > x)
                    right = mid - 1;
                else
                    left = mid + 1;
            }
            for (int j = i - 1; j >= left; j--) {      //将[left,i-1]中的元素右移一个单位
                a[j + 1] = a[j];
            }
            a[left] = x;                            //a[i]的值赋值给a[left]
        }
    }

    public static void main(String[] args) throws Exception {
//        int[] a = {8,5,3,6,8,9,2,1,55,5};
        int[] a = {2, 3, 5, 7, 9, 10, 1};
        insertSort(a);
//        binaryInsertSort(a);
        CommonSortUtil.print(a);
    }


}
