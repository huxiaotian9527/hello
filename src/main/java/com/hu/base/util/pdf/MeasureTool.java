package com.hu.base.util.pdf;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author hutiantian
 * @Date 2018/10/22 16:51:19
 */
public class MeasureTool {
    private static Map<String,Measure> measureMap = new HashMap<>();

    public static Measure getMeasure(String orgNo){
        Measure measure = measureMap.get(orgNo);
        if(measure!=null){
            return measure;
        }
        measure = new Measure();
        CertNoConfig config= new CertNoConfig();
        switch (orgNo){
            case "C0035":               //达菲
                measure.setXOffset(200);
                measure.setLoanPage(5);
                config.setXOffset(78);
                measure.setConfigs(new CertNoConfig[]{config});
                break;
            case "C0021":               //功夫贷
                measure.setLoanName("出借人列表");
                config.setWidth(63);
                config.setText("************");
                measure.setRePageNew(new Coordinate.C2(38,50,559,800));
                measure.setConfigs(new CertNoConfig[]{config});
                break;
            case "C0018":               //厚本
                measure.setXOffset(230);
                measure.setConFontSize(10);
                config.setXOffset(79);
                config.setWidth(57);
                config.setText("***********");
                //厚本的有两个身份证号
                CertNoConfig config1= new CertNoConfig();
                config1.setXOffset(79);
                config1.setPage(4);
                config1.setWidth(58);
                config1.setText("***********");
                measure.setConfigs(new CertNoConfig[]{config,config1});
                break;
            case "C0023":               //即科
                measure.setConFontSize(10);
                measure.setLoanIndex(0);
                measure.setConfigs(new CertNoConfig[]{config});
                break;
            case "C0011":               //买单侠
                config.setXOffset(78);
                config.setWidth(72);
                config.setText("**************");
                measure.setConfigs(new CertNoConfig[]{config});
                break;
            case "C13":               //美利
                measure.setConFontSize(10);
                measure.setRePageNew(new Coordinate.C2(38,50,559,800));
                config.setXOffset(78);
                config.setWidth(51);
                config.setYOffset(3);
                config.setText("**********");
                measure.setConfigs(new CertNoConfig[]{config});
                break;
            case "x":                   //什马
                measure.setReplaceName("编号");
                measure.setConFontSize(10);
                measure.setXOffset(-20);
                measure.setBeforeXOffset(-20);
                measure.setRePageNew(new Coordinate.C2(38,50,559,800));
                measure.setConfigs(new CertNoConfig[]{config});
                break;
            case "30587853-5":          //我来贷

                break;
            default:
                measure = null;
                break;
        }
     measureMap.put(orgNo,measure);
     return measure;
    }
}
