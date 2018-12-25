package com.hu.base.encrypt;

import lombok.Data;

/**
 * @Author hutiantian
 * @Date 2018/12/24 15:42:23
 */
@Data
public class SingleLoanDTO {
    private String reqID;                       //数据唯一标识
    private String opCode;                      //预留操作代码
    private String uploadTs;                    //机构系统生成本记 录时间
    private String name;                        //姓名
    private String pid;                         //身份证号码
    private String mobile;                      //手机号码
    private String loanId;                      //贷款编号
    private String originalLoanId;              //原贷款编号
    private String guaranteeType;               //贷款担保类型
    private String loanPurpose;                 //贷款用途
    private String applyDate;                   //贷款申请时间
    private String accountOpenDate;             //账户开立时间
    private String issueDate;                   //贷款放款时间
    private String dueDate;                     //贷款到期日期
    private String loanAmount;                  //贷款金额
    private String totalTerm;                   //还款总期数
    private String targetRepayDateType;         //账单日类型
    private String termPeriod;                  //每期还款周期
    private String targetRepayDateList;         //账单日列表
    private String firstRepaymentDate;          //首次应还款日
    private String gracePeriod;                 //逾期宽限期
    private String device;                      //设备信息
    private String deviceType;                  //设备类型
    private String imei;                        //设备IMEI/MEID
    private String mac;                         //MAC地址
    private String ipAddress;                   //IP地址
    private String osName;                      //设备操作系统标签
}
