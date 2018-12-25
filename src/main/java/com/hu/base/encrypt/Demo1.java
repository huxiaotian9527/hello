package com.hu.base.encrypt;

import com.alibaba.fastjson.JSON;
import com.hu.base.util.http.HttpsUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author hutiantian
 * @Date 2018/12/24 15:31:40
 */
public class Demo1 {
    private static final String name = "";
    private static final String password = "";
    private static final String url = "https://172.23.0.10:8443/api/v1/credit/loan/issue";


    public static void main(String[] args) throws Exception{
//        System.out.println(new Date().toString());
        SingleLoanDTO dto = new SingleLoanDTO();
        dto.setReqID("suishouji000000000001");
        dto.setOpCode("A");
        dto.setUploadTs("2018-10-07T01:00:00");
        dto.setName("张三");
        dto.setPid("500112199301010002");
        dto.setMobile("18810660002");
        dto.setLoanId("suishou000000015616");
        dto.setOriginalLoanId("");
        dto.setGuaranteeType("1");
        dto.setLoanPurpose("1");
        dto.setApplyDate("2018-10-05T10:00:00");
        dto.setAccountOpenDate("2018-10-05T10:00:00");
        dto.setIssueDate("2018-10-05T10:00:00");
        dto.setDueDate("2019-11-9");
        dto.setLoanAmount("3000.00");
        dto.setTotalTerm("3");
        dto.setTargetRepayDateType("2");
        dto.setTermPeriod("-1");
        dto.setTargetRepayDateList("2018-11-06,2018-12-06,2019-01-06");
        dto.setFirstRepaymentDate("2018-11-06");
        dto.setGracePeriod("0");
        dto.setDevice("");
        dto.setDeviceType("");
        dto.setImei("");
        dto.setMac("");
        dto.setIpAddress("");
        dto.setOsName("");
        Map<String,String> map = new HashMap<>();
        map.put("Authorization","Basic YWRtaW46cGFzc3dvcmQ=");
//        Class clazz = dto.getClass();
//        Field[] fields = clazz.getDeclaredFields();
//        for (Field field:fields){
//            String getName = "get"+field.getName().substring(0,1).toUpperCase()+field.getName().substring(1);
//            Method method = clazz.getMethod(getName);
//            map.put(field.getName(),method.invoke(dto,null).toString());
//        }
        String jsonStr = JSON.toJSONString(dto);
        System.out.println(jsonStr);
        String result = HttpsUtil.doPost(url,jsonStr,map);
        System.out.println(result);

    }


}
