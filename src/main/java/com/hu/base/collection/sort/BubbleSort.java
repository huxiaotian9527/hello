package com.hu.base.collection.sort;

/**
 * 冒泡排序
 * @Author hutiantian
 * @Date 2018/10/11 9:34:53
 */
public class BubbleSort {

    /**
     * 冒泡排序思路：
     * 步骤一：从前往后遍历数组无序部分，比较两个相邻元素，如果前一个比后一个大，就交换位置。
     * 步骤二：一趟序之后，最大的元素会落到最后，数组中无序的元素个数为n-i（i表示趟序的次数）
     * 步骤三：继续步骤一、步骤二，直至数组的无序部分大小为0
     *（有序部分在数组的后面，每一趟序有序部分+1）
     *
     * 冒泡排序优点：原理简单，可以用稳定性算法实现。优化后最好情况（有序数组）复杂度是 O(N)
     * 缺点：效率很低，时间复杂度基本为 O(N²)
     * 特点：元素的比较次数与元素的初始排序有关（优化后的算法），可稳定。
     *
     */
    public static void bubbleSort(int a[]) throws Exception{
        if(a==null){
            throw new Exception("inValid input ,please check your input array!");
        }
        for (int i = 0; i < a.length-1; i++) {                  //外层遍历n-1次
            for (int j = 0; j < a.length-1-i; j++) {            //内层需比较n-1-i次
                if(a[j]>a[j+1]){
                    CommonSortUtil.swap(a,j,j+1);
                }
            }
        }
    }

    /**
     * 优化后的冒泡排序
     *
     * 优化思路：新增标志位flag,每轮遍历的时候设置为true，如果该轮遍历发生交换，flag至为false
     * 如果该轮遍历未发生交换，说明数组已经有序，就不用在继续遍历。
     *
     * 特点：在有序的数组中的时间复杂度为O(N)
     *
     */
    public static void bubbleSortPlanB(int a[]){
        if(a==null){
            return;
        }
        boolean flag;
        for (int i = 0; i < a.length-1; i++) {
            flag = true;
            for (int j = 0; j < a.length-1-i; j++) {
                if(a[j]>a[j+1]){
                    CommonSortUtil.swap(a,j,j+1);
                    flag = false;
                }
            }
            if(flag){
                break;
            }
        }
    }

    public static void main(String[] args) throws Exception {
//        int a[] = new int[10];                 动态初始化
//        int[] a = new int[]{5,5};
        int[] a = {8,5,3,6,8,9,2,1,55};        //静态初始化
        bubbleSort(a);
//        bubbleSortPlanB(a);
        CommonSortUtil.print(a);
    }
}
