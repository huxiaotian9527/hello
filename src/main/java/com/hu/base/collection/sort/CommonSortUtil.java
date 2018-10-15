package com.hu.base.collection.sort;

/**
 * @Author hutiantian
 * @Date 2018/10/11 10:58:27
 */
public class CommonSortUtil {

    /**
     * 交换数组的两个下标的元素值
     */
    public static void swap(int a[],int i,int j){
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    /**
     * 不用了，用Arrays.toString(object[] o);
     */
    @Deprecated
    public static void print(int a[]){
        for(int i:a){
            System.out.print(i+"  ");
        }
        System.out.println();
    }
}
