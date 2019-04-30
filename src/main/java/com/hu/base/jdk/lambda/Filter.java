package com.hu.base.jdk.lambda;

import java.util.List;
import java.util.stream.Collectors;

/**
 * lambda快速处理结合
 * @Author hutiantian
 * @Date 2019/4/30 14:49:00
 */
public class Filter {


    public static void main(String[] args) {

    }

    /**
     * 获取集合中的某一个属性，组装成集合
     */
    private static void getList(List<LambdaDto> lambdaList){
        List<Integer> res = lambdaList.stream().map(e->e.getId()).collect(Collectors.toList());
    }

    /**
     * 过滤集合中的符合条件的元素集合
     *  filter可以用多次
     */
    private static void filterList(List<LambdaDto> lambdaList){
        List<LambdaDto> res = lambdaList.stream().filter(e->e.getName().contains("hutiantian")&&e.getId().equals(15))
                                    .filter(e->e.getId().equals(256)).collect(Collectors.toList());
    }


    /**
     * 判断集合中是否有符合条件的元素
     */
    private static void amyMatch(List<LambdaDto> lambdaList){
        boolean flag = lambdaList.stream().anyMatch(str->str.getName().equals("hutiantian"));
    }

    /**
     * 将集合中的元素逗号分隔连接起来
     */
    private static void join(List<LambdaDto> lambdaList){
        String res = lambdaList.stream().map(e -> e.getName()).collect(Collectors.joining(","));

    }
}
