package com.hu.base.util.pdf;

import lombok.Data;

/**
 * 坐标类
 * @Author hutiantian
 * @Date 2018/9/17 14:50:04
 */

@Data
public class Coordinate {
    private float x;                    //pdf矩形x坐标
    private float y;                    //pdf矩形y坐标
    private float width;                //pdf矩形宽度
    private float height;               //pdf矩形高度

}
