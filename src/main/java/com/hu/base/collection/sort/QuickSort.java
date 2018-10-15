package com.hu.base.collection.sort;

import java.util.Arrays;

/**
 * 快速排序
 *
 * @Author hutiantian
 * @Date 2018/10/12 14:07:26
 */
public class QuickSort {

    /**
     * 快速排序是用二分法（中间数划分）对冒泡排序的一种改进，采用递归分而治之，
     * 每次按基准数把无序部分拆分成两份，直至无法拆分，思路：
     * 步骤一：找到一个基准数x，一般为数组的第一个元素
     * 步骤二：从数组（基准数之外）左起left找第一个x大的，右起right找比x小的，然后两两交换，直至left>=right
     * 步骤三：此时将x与left交换，数组就已x为边界分为了两部分，左边部分小于x，右边部分都大于x
     * 步骤四：递归对左右两部分进行步骤一、二、三的操作，直至整个序列有序
     * <p>
     * 快速排序排序优点：最好时间复杂度为O(N*logN)
     * 缺点：不稳定
     * 特点：排序效率高
     */
    public static void quickSort(int a[] ,int left,int right){
        if(left>=right){                            //代表a[]已经有序
            return;
        }
        int middle = findMid(a,left,right);         //middle为基准数替换后的下标
        quickSort(a,left,middle-1);            //二分后左边部分继续二分
        quickSort(a,middle+1,right);            //二分后右边的部分继续二分
    }

    /**
     * 通过left、right移动，将无序数组中的第一个元素交换至"中间位置",同时返回这个位置的下表
     * @param a 需要处理的数组
     * @param left left下标
     * @param right right下标
     * @return "中间位置"的下标
     * 这里的left、right和跳出循环的判断很容易搞混淆，我反正第一遍是敲错了。
     */
    public static int findMid(int a[] ,int left,int right){
        int tail = right;                               //以数组最右边的数为基础数
        while(left<right){                              //当left<right的时候才能继续循环，大于等于都跳出
            while(left<right&&a[left]<=a[tail]){
                left++;                                 //left前移一位
            }
            while(left<right&&a[right]>=a[tail]){
                right--;                                //right后移一位
            }
            CommonSortUtil.swap(a,left,right);          //交换left和right的值
        }
        CommonSortUtil.swap(a,left,tail);               //交换值，此时的a[left]>=a[tail]
        return left;
    }

    public static void main(String[] args) {
        int[] a = {8,5,3,6,4,9,2,1,55,5};
        quickSort(a,0,a.length-1);
        System.out.println(Arrays.toString(a));
    }
}
