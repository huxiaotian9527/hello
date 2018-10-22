package com.hu.base.util.pdf;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 身份证号处理
 * @Author hutiantian
 * @Date 2018/10/22 15:04:53
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CertNoConfig {

    private String name = "身份证号";           //要查找的关键字

    private int page = 1;                      //替换的页码

    private int index = 0;                      //查找关键字下标（一页可能有多个关键字）

    private float xOffset = 80;                 //x坐标右偏移量

    private float width = 51;                    //替换宽度

    private int fontSize = 12;                  //替换后的*号字体大小

    private int yOffset = 4;                   //替换后*号向上的偏移量

    private String text = "**********";             //替换的内容

    private float fixHeight = 5;          //关键字y坐标修正，可能出现无法完全覆盖的情况，参考值:5

    private float defaultHeight = 12;      //关键字高度，可能出现无法取到值的情况，参考值:12

    public CertNoConfig(int page){
        this.page = page;
    }
}
