package com.hu.base.util.pdf;

/**
 * 出借人清单处理策略
 * @Author hutiantian
 * @Date 2018/10/21 15:35:44
 */
public enum StrategyEnum {

    /**
     * 直接生成新的sheet页
     * 注：NEW模式不会查找替换出借人清单，确定机构不会生成出借人清单的采用这种模式
     */
    NEW,

    /**
     * 根据坐标替换源文件的出借人清单，生成新的清单覆盖
     */
    REPLACE,

}
