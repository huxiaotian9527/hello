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
     * 步骤三：此时将x与left交换，数组就已x为边界分为了两部分，左边部分小于x，右边部分都大于x
     * 步骤四：分别对左右两部分进行步骤一、二、三的操作，直至左右部分
     *
     * <p>
     *
     *
     *
     */
    public static void quickSort(int a[] ,int left,int right){


    }
}
