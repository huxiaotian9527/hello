package com.hu.base.test;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author hutiantian
 * @Date 2018/9/18 15:27:33
 */
@Slf4j
@Data
@ToString
public class Test implements Cloneable {
    public String a;
    public String b;
    private static final int COUNT_BITS = Integer.SIZE - 3;
    private static final int CAPACITY   = (1 << COUNT_BITS) - 1;

    // runState is stored in the high-order bits
    private static final int RUNNING    = -1 << COUNT_BITS;
    private static final int SHUTDOWN   =  0 << COUNT_BITS;
    private static final int STOP       =  1 << COUNT_BITS;
    private static final int TIDYING    =  2 << COUNT_BITS;
    private static final int TERMINATED =  3 << COUNT_BITS;

    // Packing and unpacking ctl
    private static int runStateOf(int c)     { return c & ~CAPACITY; }
    private static int workerCountOf(int c)  { return c & CAPACITY; }
    private static int ctlOf(int rs, int wc) { return rs | wc; }

    /*
     * Bit field accessors that don't require unpacking ctl.
     * These depend on the bit layout and on workerCount being never negative.
     */

    private static boolean runStateLessThan(int c, int s) {
        return c < s;
    }

    private static boolean runStateAtLeast(int c, int s) {
        return c >= s;
    }

    private static boolean isRunning(int c) {
        return c < SHUTDOWN;
    }


    public static void main(String[] args) {
        System.out.println(3/2);
    }


    public  static  int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        if(m==0||n==0){
            return 0;
        }
        String lower;
        String higher;
        if(m<n){
            lower = text1;
            higher = text2;
        }else {
            lower = text2;
            higher = text1;
        }
        String common ;
        int temp ;
        String array = "";
        for(int i=0;i<lower.length();i++){
            String s = lower.substring(i,i+1);
            if(!array.contains(s)){
                if(higher.indexOf(s)>=0){
                    array = array + s;
                }
            }
        }
        int max = 0;
        for(int i=0;i<array.length();i++){
            String s = array.substring(i,i+1);
            common = s;
            int lowFirst = lower.indexOf(s);
            int high = higher.indexOf(s);
            for (int j = lowFirst+1;j<lower.length();j++){
                String t = lower.substring(j,j+1);
                temp = higher.indexOf(t,high+1);
                if (temp >= 0) {
                    common = common + t;
                    high = temp;
                }
            }
            if(max<common.length()){
                max = common.length();
            }
        }
        return max;
    }


    public static int maxSubArray(int[] nums) {
        int ans = nums[0];
        int sum = 0;
        for(int num: nums) {
            if(sum > 0) {
                sum += num;
            } else {
                sum = num;
            }
            ans = Math.max(ans, sum);
        }
        return ans;
    }

    public static  List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> resultList= new ArrayList<>();
        Set<String> strSet = new HashSet<>();
        int length = nums.length;
        int a,b,c,temp;
        String str;
        for(int i=0;i<length-2;i++){
            for(int j=i+1;j<length-1;j++){
                for(int k=j+1;k<length;k++){
                    if(nums[i]+nums[j]+nums[k]==0){
                        a = nums[i];
                        b = nums[j];
                        c = nums[k];
                        if(a>b){
                            temp = b;
                            b = a;
                            a = temp;
                        }
                        if(b>c){
                            temp =c;
                            c = b;
                            b = temp;
                        }
                        if(a>b){
                            temp = b;
                            b = a;
                            a = temp;
                        }
                        str = a+""+b+""+c+"";
                        if(strSet.contains(str)){
                            break;
                        }
                        strSet.add(str);
                        List<Integer> list = new ArrayList();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[k]);
                        resultList.add(list);
                        break;
                    }
                }
            }
        }
        return resultList;
    }


    private static String trim(String str){
        if(str.contains("省")&&str.contains("市")){
            int i = str.indexOf("省");
            int j = str.indexOf("市");
            return str.substring(i+1,j+1);
        }else if(str.contains("自治区")&&str.contains("市")){
            int i = str.indexOf("自治区");
            int j = str.indexOf("市");
            return str.substring(i+3,j+1);
        }
        return "";
    }


    /**
     * 反转字符串
     */
    public static void inversionString(String str) {
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int t = (int) chars[i];
            if (t > 64 && t < 90) {
                chars[i] += 32;
            } else if (t > 97 && t < 122) {
                chars[i] -= 32;
            }
        }
        System.out.println(new String(chars));
    }


    public static int findLCS(String A, int n, String B, int m) {
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                dp[i][j] = 0;
            }
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (A.charAt(i - 1) == B.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = dp[i - 1][j] > dp[i][j - 1] ? dp[i - 1][j] : dp[i][j - 1];
                }
            }
        }
        return dp[n][m];
    }

    public static int xxxx(String text1, String text2) {
        char[] s1 = text1.toCharArray();
        char[] s2 = text2.toCharArray();
        int[][] dp = new int[s1.length + 1][s2.length + 1];

        for(int i = 1 ; i < s1.length + 1 ; i ++){
            for(int j = 1 ; j < s2.length + 1 ; j ++){
                //如果末端相同
                if(s1[i - 1] == s2[j - 1]){
                    dp[i][j] = dp[i-1][j-1] + 1;
                }else{
                    //如果末端不同
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        return dp[s1.length][s2.length];
    }
}