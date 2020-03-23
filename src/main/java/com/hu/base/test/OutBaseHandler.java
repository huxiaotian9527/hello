package com.hu.base.test;

import lombok.Data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author hutiantian
 * @Date 2020/3/4 15:50:26
 */
public class OutBaseHandler {

    public static void main(String[] args)throws  Exception {
//        Map<String,OriginalData> map26 = getOriginalData("C:\\Users\\Administrator\\Desktop\\11.txt");
        Map<String,OriginalData> map27 = getOriginalData("C:\\Users\\Administrator\\Desktop\\12.txt");
        Map<String,String> map28 = getOriginalData1("C:\\Users\\Administrator\\Desktop\\16.txt");

//        for(Map.Entry<String,OriginalData> entry : map26.entrySet()){
//            if(map27.get(entry.getKey())==null){
//                map27.put(entry.getKey(),entry.getValue());
//            }
//        }

        List<PayData> list = getPayData();
        List<OutData> result = new ArrayList<>();

        for (PayData payData:list){
            OriginalData originalData = map27.get(payData.getContractNo());
            if(originalData !=null){
                OutData outData = new OutData();
                outData.setCaseId(originalData.getCaseId());
                outData.setCustId(originalData.getCustId());
                outData.setContractNo(originalData.getContractNo());
                outData.setCompany(originalData.getCompany());
                outData.setProduct(originalData.getProduct());
                outData.setCustName(originalData.getCustName());
                outData.setCertId(originalData.getCertId());
                outData.setOutState(originalData.getOutState());
                outData.setOutTime(originalData.getOutTime());
                outData.setOutMoney(originalData.getOutMoney());
                outData.setOverDueMoney(originalData.getOverDueMoney());
                outData.setFirstOADate(originalData.getFirstOADate());
                outData.setSection(originalData.getSection());
                outData.setOaDate(originalData.getOaDate());
                outData.setOverDueDays(originalData.getOverDueDays());
                outData.setPayMoney(payData.getPayMoney());
                outData.setBank(payData.getBank());
                outData.setBankNo(payData.getBankNo());
                outData.setPayDate(payData.getPayDate());
                String collector = map28.get(originalData.getCollector());
                if(collector!=null){
                    outData.setCollector(collector);
                }else {
                    outData.setCollector(originalData.getCollector());
                }
                result.add(outData);
            }
        }

        System.out.println("结果有多少条："+result.size());
        if(result.size()==0){
            return;
        }
        BigDecimal total = BigDecimal.ZERO;
        for (OutData outData:result){
            String money = outData.getPayMoney();
            total = total.add(new BigDecimal(money));
        }
        System.out.println("结果金额："+total.toString());

        BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\Administrator\\Desktop\\125.txt"));
        for (OutData outData:result){
            bw.write(outData.getCaseId()+"\t");
            bw.write(outData.getCustId()+"\t");
            bw.write(outData.getContractNo()+"\t");
            bw.write(outData.getCompany()+"\t");
            if(outData.getCollector()!=null){
                bw.write(outData.getCollector()+"\t");
            }
            bw.write(outData.getProduct()+"\t");
            bw.write(outData.getCustName()+"\t");
            bw.write(outData.getCertId()+"\t");
            bw.write(outData.getOutState()+"\t");
            bw.write(outData.getOutTime()+"\t");            //委外次数
            bw.write(outData.getOutMoney()+"\t");
            bw.write(outData.getOverDueMoney()+"\t");
            bw.write(outData.getFirstOADate()+"\t");        //初始委外日期
            bw.write(outData.getSection()+"\t");
            bw.write(outData.getOaDate()+"\t");             //分案逾期天数
            bw.write(outData.getOverDueDays()+"\t");        //逾期天数
            bw.write(outData.getPayMoney()+"\t");
            bw.write(outData.getBank()+"\t");
            bw.write(outData.getBankNo()+"\t");
            bw.write(outData.getPayDate()+"\t");
            bw.write("\n");
            bw.flush();
        }
        bw.close();


    }

    private static Map<String,String> getOriginalData1(String fileName)throws  Exception{
        BufferedReader bf =new BufferedReader(new FileReader(fileName));
        String str;
        Map<String,String> map = new HashMap<>();
        while((str =bf.readLine())!=null){
            String[] arr = str.split("\t");

            map.put(arr[1],arr[1]+"-"+arr[2]);
        }
        return map;
    }

    private static Map<String,OriginalData> getOriginalData(String fileName)throws  Exception{
        BufferedReader bf =new BufferedReader(new FileReader(fileName));
        String str;
        Map<String,OriginalData> map = new HashMap<>();
        while((str =bf.readLine())!=null){
            String[] arr = str.split("\t");
            OriginalData originalData = new OriginalData();
            originalData.setCaseId(arr[0]);
            originalData.setCustId(arr[1]);
            originalData.setContractNo(arr[2]);
            originalData.setCompany(arr[3]);
            originalData.setCollector(arr[4]);
            originalData.setProduct(arr[5]);
            originalData.setCustName(arr[6]);
            originalData.setCertId(arr[7]);
            originalData.setOutState(arr[9]);
            originalData.setOutTime(arr[10]);
            originalData.setOutMoney(arr[11]);
            originalData.setOverDueMoney(arr[12]);
            originalData.setFirstOADate(arr[13]);
            originalData.setSection(arr[16]);
            originalData.setOaDate(arr[17]);
            originalData.setOverDueDays(arr[18]);
            map.put(originalData.getContractNo(),originalData);
        }
        return map;
    }

    private static List<PayData> getPayData()throws  Exception{
        BufferedReader bf =new BufferedReader(new FileReader("C:\\Users\\Administrator\\Desktop\\13.txt"));
        String str;
        List<PayData> list= new ArrayList<>();
        while((str =bf.readLine())!=null){
            String[] arr = str.split("\t");
            PayData payData = new PayData();
            payData.setContractNo(arr[2]);
            payData.setPayMoney(arr[3]);
            payData.setBank(arr[4]);
            payData.setBankNo(arr[5]);
            payData.setPayDate(arr[6]);
            list.add(payData);
        }
        return list;
    }


    @Data
    static class OriginalData{
        private String caseId;
        private String custId;
        private String contractNo;
        private String company;
        private String product;
        private String custName;
        private String certId;
        private String outState;
        private String outTime;
        private String outMoney;
        private String overDueMoney;
        private String FirstOADate;
        private String section;
        private String oaDate;
        private String overDueDays;
        private String collector;
    }

    @Data
    static class PayData{
        private String contractNo;
        private String payMoney;
        private String bank;
        private String bankNo;
        private String payDate;
    }

    @Data
   static class OutData{
        private String caseId;
        private String custId;
        private String contractNo;
        private String company;
        private String product;
        private String custName;
        private String certId;
        private String outState;
        private String outTime;
        private String outMoney;
        private String overDueMoney;
        private String FirstOADate;
        private String section;
        private String oaDate;
        private String overDueDays;
        private String payMoney;
        private String bank;
        private String bankNo;
        private String payDate;
        private String collector;
    }

}
