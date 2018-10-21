package com.hu.base.util.pdf;

/**
 * 出借人清单处理策略
 * @Author hutiantian
 * @Date 2018/10/21 15:35:44
 */
public enum StrategyEnum {

    /**
     * 直接生成新的sheet页面
     */
    NEW,

    /**
     * 根据坐标替换源文件的出借人清单，并生成新的清单覆盖
     */
    REPLACE,

    /**
     * 删除出借人清单所在页，之后在生成新的sheet页
     */
    DELETE

}
