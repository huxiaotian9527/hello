package com.hu.base.collection.sort;

/**
 * 二分查找算法
 * @Author hutiantian
 * @Date 2019/3/18 15:18:53
 */
public class HalfSelect {

    public static void main(String[] args) {
        int[] a = new int[]{0,2,4,6,8,10,12,14};
        int x = halfSelect(a,0);
        if(x==-1){
            System.out.println("不存在");
        }else {
            System.out.println("存在，下标为"+x);
        }
    }

    /**
     * 有序数组二分查找
     */
    private static int halfSelect(int[] a,int x){
        int pre = 0;
        int suf = a.length-1;
        int mid;
        if(x<a[pre]||x>a[suf]){             //比最大大或者比最小小
            return -1;
        }
        while(pre<=suf){
            mid = (pre+suf)/2;
            if(a[mid]>x){
                suf = mid-1;
            }else if(a[mid]<x){
                pre = mid+1;
            }else {
                return mid;
            }
        }
        return -1;
    }


}
