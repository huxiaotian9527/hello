package com.hu.base.util.NewPdf;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 坐标类
 * @Author hutiantian
 * @Date 2018/9/17 14:50:04
 */

@Data
public class Coordinate {

    private C1 c1;          //C1表示一个坐标的点

    private C2 c2;         //C2表示两个坐标的点

    @Data
    public static class C1{
        private float x;                    //A点x坐标
        private float y;                    //A点y坐标
        private float width;                //A点宽度
        private float height;               //A点高度
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class C2{
        private float x1;                    //A点x坐标
        private float y1;                    //A点y坐标
        private float x2;                    //B点x坐标
        private float y2;                    //B点y坐标
    }


}
