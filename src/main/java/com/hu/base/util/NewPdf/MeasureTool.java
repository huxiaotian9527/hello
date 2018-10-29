package com.hu.base.util.NewPdf;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author hutiantian
 * @Date 2018/10/22 16:51:19
 */
public class MeasureTool {
    private static Map<String, Measure> measureMap = new HashMap<>();

    public static Measure getMeasure(String orgNo){
        Measure measure = measureMap.get(orgNo);
        if(measure!=null){
            return measure;
        }
        measure = new Measure();
        switch (orgNo){
            case "C0024":               //达菲
                measure.setLoanFlag(false);
                break;
            case "C0021":               //功夫贷
                measure.setLoanName("出借人列表");
                measure.setRePageNew(new Coordinate.C2(38,50,559,800));
                break;
            case "C0018":               //厚本
                break;
            case "C0023":               //即科
                measure.setLoanIndex(0);
                break;
            case "C0011":               //买单侠
                break;
            case "C13":               //美利
                break;
            case "C0019":                //什马
                break;
            case "30587853-5":          //我来贷
                measure.setExtractFlag(true);
                break;
            default:
                measure = null;
                break;
        }
     measureMap.put(orgNo,measure);
     return measure;
    }
}
