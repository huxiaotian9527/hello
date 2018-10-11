package com.hu.base.collection.sort;

/**
 * 简单选择排序
 * @Author hutiantian
 * @Date 2018/10/11 10:57:44
 */
public class SelectSort {

    /**
     * 简单选择排序思路：
     * 步骤一：从前往后遍历数组无序部分，选择出最小元素所在下标，注：此时没有发生交换，只是用一个临时变量min记录
     * 步骤二：判断min是否为数组有序部分的末尾，若不是则发生交换
     * 步骤三：去除掉步骤一、步骤二，直至数组全部有序
     * 有序部分在数组的前面，每一趟序有序部分+1）
     *
     * 简单选择排序优点：原理简单
     * 缺点：效率很低，时间复杂度为 O(N²)
     * 特点：元素的比较次数与元素的初始排序无关，不稳定。
     *
     */
    public static void selectSort(int a[]) throws Exception{
        if(a==null){
            throw new Exception("inValid input ,please check your input array!");
        }
        int min;
        for (int i = 0; i < a.length-1; i++) {                  //外层遍历n-1次
            min = i;
            for (int j = i+1; j < a.length; j++) {              //内层需要与最后一个元素比较，故j<a.length
                if(a[min]>a[j]){
                    min = j;
                }
            }
            if(min!=i){
                CommonSortUtil.swap(a,i,min);           //这里可能把稳定性打乱
            }
        }
    }

    public static void main(String[] args) throws Exception{
        int[] a = {8,5,3,6,8,9,2,1,55,5};
        selectSort(a);
        CommonSortUtil.print(a);
    }

}
