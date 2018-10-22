package com.hu.base.util.pdf;

import lombok.Data;

/**
 * pdf处理参数配置类
 * @Author hutiantian
 * @Date 2018/10/21 11:31:01
 */
@Data
public class Measure {

    private String replaceName = "合同编号";       //需要替换的关键字(合同编号)

    private String afterName = "合同编号：123456";        //替换后的关键字（合同编号+"13456..."）

    private int findPage = 1;             //关键字所在页

    private int replaceIndex = 0;         //指定替换关键字下标(关键字可能会匹配多个)；若为小于0或者大于最大值，则匹配最后一个

    private float fixHeight = 5;          //关键字y坐标修正，可能出现无法完全覆盖的情况，参考值:5

    private float defaultHeight = 12;      //关键字高度，可能出现无法取到值的情况，参考值:12

    private float replaceWidth = 1000;       //替换关键字的长度，参考值：1000

    private String loanName = "出借人清单";       //需要替换的关键字(合同编号)

    private int loanPage = -1;             //关键字所在页，如果为-1，则取最后一页

    private int loanIndex = -1;         //匹配到多个的时候，替换第几个关键字，一般为第一个（下标为0的），若为小于0，则匹配最后一个

//    private StrategyEnum strategy= StrategyEnum.REPLACE;        //默认为替换策略
    private StrategyEnum strategy= StrategyEnum.NEW;

    private Coordinate.C2 rePageLast = new Coordinate.C2(38,50,559,0);    //最后一页要追加的矩形（y2会重新赋值）

    private float loanHeight = 30;          //需要遮盖的图层的修正高度（关键字的高度上面还有一点表格）

    private float newLoanHeight = 15;       //新生成的表格修正高度（可以将表格适当的往下移）

    private Coordinate.C2 rePageNew = new Coordinate.C2(38,50,559,806);     //新的页要追加的矩形

}
