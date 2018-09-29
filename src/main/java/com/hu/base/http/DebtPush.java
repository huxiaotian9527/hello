package com.hu.base.http;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hu.base.http.util.DebtHttpRequest;
import com.hu.base.http.util.iqianjin.JsonUtils;
import com.hu.base.http.util.iqianjin.MD5Utils;
import com.hu.base.http.util.model.ZcOriginalDebtDTO;
import com.hu.base.http.util.model.ZcOriginalDebtItemDTO;
import com.hu.base.http.util.model.ZcOriginalDebtRelationshipDTO;
import com.hu.base.http.util.wolaidian.AESUtils;
import com.hu.base.http.util.wolaidian.RSAUtils;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class DebtPush {

	// 测试3
	// public final static String publicKey =
	// "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC4yJukSk5+FqApDuuhps4l1GV3CGy8/6wJ8MxVI8suMGYyAd+sx+d15ZlL9vNdh7llj+kssbpa9ZBCM9nSONnx7gZRpQZOd/JfmWyJnYlq/7qlrI3z5QGjh9J4e/cURy6jq0+dbGEjxei4G2yplqJ0ko9zxFqSKNJpe213+7VqSQIDAQAB";
	//生产厚本
	// public final static String publicKey =
	// "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDErHV4oq4EB//GyHdejHKtHxfFik/HCCbI3g5IFcTRyDZtd7rjBn9GyissGo1jY0V0RlY+/XyHkX+B//Tqc5mcKpUDgDcBxX3oojgoeI5GaoBm9/pbLMAf/gkHugVxyCTQaa2dL828cujbCO5hw2SG5/m8rvZQNbUr8D2Q2UjA8QIDAQAB";
	// 买单侠
//	public final static String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCI2/4uErrkTXO/5tMLgariI+6HW5jT+hrcHEjmsBv8vT25jLPOa3Wg5bde2HApV3FO8cjob8z6p4l3L/mwmjF5juTne+SaKuAKpoO95h/RIHNdtmKDFVVC5/Qlc8J9+coJ7PJn12fh3BU6Mp2/S5Bqhth+g75HPNhtLRbUm12WFQIDAQAB";
	public final static String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC0SbcYAqR0nyW5DiFZGaz5gOrSmgDyyhQt64ctgNN0XKhXTfXZA7YL/924Hc3tMuH+3J/1QEdEzO+JMgduyX6JrItjX2C+cgfm6T3iwXWiO+UwWqgV0F4ujGJgt8QuDRM3f+6Wkbv7G6d/85oGKPvTnDDMzUp6JIdepIeQr7zGoQIDAQAB";

	//测试3
	// public final static String privateKey =
	// "MIICeQIBADANBgkqhkiG9w0BAQEFAASCAmMwggJfAgEAAoGBALjIm6RKTn4WoCkO66GmziXUZXcIbLz/rAnwzFUjyy4wZjIB36zH53XlmUv2812HuWWP6Syxulr1kEIz2dI42fHuBlGlBk538l+ZbImdiWr/uqWsjfPlAaOH0nh79xRHLqOrT51sYSPF6LgbbKmWonSSj3PEWpIo0ml7bXf7tWpJAgMBAAECgYEAixOCAX9j4IbK+NwgAUT4t4RK8fJHVWDhbYHtaM9N52/KGy1J2lODJDswy/oMZYL3yTctetQxvLBCaXF5Kj5b2fBzk5S7SXEdKnv2OKAelrxje3Yozlttayh09l82SfdiBBiWOmWzqsCqj59CHHfLQg/s6SQSR9NaxjWg+3X9qTkCQQDtYE1CLylrmlV0Gt4uPQzDKOMnh6X9zmUljLrMt7qOD8D/fLMeSfizeiV92WA0ZN93t44f5vV2wKqaag3rEa87AkEAx0f8OdMobhP9rxvba1unPm/G2ZP80GA6K5ci0LLSLGZv2xJU2vTBptukuBOgG8XPLKPOGHs7QtsRA2AEsIv8SwJBAORj0/HqDqBJTg74Cu3SL4VWwbg2+67H9um6DcEKxaQJ5FftUynBYljNHWWI0NeYdqppIYXwYhxwI7WOdPXHfiECQQCahX3VVJ9X45ZkrmQQNexTpJZ65Z418TeJz1JGozIHDZMUOn2jnf9+o3dw6H7Qqiw/v/Ax2XQVHgpuJIjeYF1LAkEA5tx0dXyu/gSrd6KvjuWwoMul73bQAqIzJihRHsL2GeBE4Q+eBp/OcmaV7oH2CNi4x53CVtIA/qA0E/QBoHXUDg==";
	// 测试3买单侠
	// public final static String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC0SbcYAqR0nyW5DiFZGaz5gOrSmgDyyhQt64ctgNN0XKhXTfXZA7YL/924Hc3tMuH+3J/1QEdEzO+JMgduyX6JrItjX2C+cgfm6T3iwXWiO+UwWqgV0F4ujGJgt8QuDRM3f+6Wkbv7G6d/85oGKPvTnDDMzUp6JIdepIeQr7zGoQIDAQAB";

	//生产
	// public final static String privateKey =
	// "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAMSsdXiirgQH/8bId16Mcq0fF8WKT8cIJsjeDkgVxNHINm13uuMGf0bKKywajWNjRXRGVj79fIeRf4H/9OpzmZwqlQOANwHFfeiiOCh4jkZqgGb3+lsswB/+CQe6BXHIJNBprZ0vzbxy6NsI7mHDZIbn+byu9lA1tSvwPZDZSMDxAgMBAAECgYEAwVSiWUbZdp8CYL7ZELlRVTsWXBodWBJ8RcJ7GYNEqc99KVij/t15KfY8QCuNxyBjuuwVhfAahQ2S4RDiCcI/QVjffO2NwSAm8ifOEFCJmd7v2bpHXkaNcJcDjg/Zgv6cy/REs0Qg3ZWukriquq5iHYJmhYgCUOtucjecep8ZbMECQQDyoN3spz76j6eyxLIlsdEaXQVcl1MebsWiRMFXGUeuGjf6aYesAPIbMpEXIi5wQBtOhEfG8V0dn8JGLcgJdsbJAkEAz4M84tzaSCWUKYrNrxJxnYYbKf7zYoa/W2uf46U74DFMIXUlSIb2CSB4LZCY12OWVb4EcYn9lO7ZWpeU+uU06QJBAIEXMBx6u5pqlmoaF0LRAhVJytGBvfqiK0rz0/54d9fdIU19mm7crfPkiqfJSvX0VdMHtHzX7JgXaTEXqKfIZ2kCQApTUGtFbAMdA5NRmcx1UXvyGVXsnrWvWMQ2xbzxEtqj+VLM3m8OcB2iJPi3cjN9fBSGIPh5Uknf3KbaVgpp70kCQDjSypHa3ql3oLh5hAysgfOfcfeL9N30Sc38fKv+Ts8L6my0C142RBqYWqB1OCpS4xhvh3uzv/R6dM5qIwyk4fE=";

	public final static String privateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAIjb/i4SuuRNc7/m0wuBquIj7odbmNP6GtwcSOawG/y9PbmMs85rdaDlt17YcClXcU7xyOhvzPqniXcv+bCaMXmO5Od75Joq4Aqmg73mH9Egc122YoMVVULn9CVzwn35ygns8mfXZ+HcFToynb9LkGqG2H6Dvkc82G0tFtSbXZYVAgMBAAECgYBCt1u1ghPxb4BZsGB5X4C6NOH2wkRCMc4eNvui2X8LOgV5Coosul3hTZqSddhXjvUBkwRpqva1yi5NF+oUmVY8QO3Lfe5ajZv6EJVo5T6NibYdtNVFBFDyvYNnOw+83Mu+t1sj9XJ/b0PZ+gi2rAmvlpwhIRsJ2KYT++gktz+0EQJBAMhYVDxk7Hebfs6vgRJdUh7WiHIrBQFpVE8uJ/C2W6r13yXES3F4CEBOxM+oChmFSLElzHvImutFjDNS4CvfYHsCQQCu4NY1AL+V63njsUn6tRusvdG/ySeGRIgTESV+P3Mz/w6D64Me02F2kYfVXTr5kJmcbFfb0pWg1tL8KxfsC0avAkALZLKNqLOl7ardeC+f6ryz0kF6NKYZOXM4+Yf98XDvHQJutGYR8uCNP62DnavNXb4PjPW/CF5/CLp7ZLgo5pAnAkBgNtGDvdKqkDCypkMWjXgZdSImi2yUgbYAD4n6SLyAWg7JY9TzK8dOrcj3CeE9l2H8XtuD1Pt/z1GrbaYutxF/AkBNmZfNBT01WpvxTre4THakJcz4BMsHP0uN+6M1O9Sq+HAu5brGHF0qFIwWF45KtF4IPjFQuccCAzFNVTgYhAW5";
	public static void main(String[] args) throws Exception {
		pushTest();
	}
	
	public static void pushTest() throws Exception{
		String rKey = "3825348207625580";
		// RSA加密后的key作为http请求的key参数
		String paramKey = RSAUtils.encrypt(rKey, publicKey);
		// System.out.println("RSA加密后 paramKey="+paramKey);
		String content = new ObjectMapper().writeValueAsString(getZcOriginalDebt());
		System.out.println("content：" + content);
		// AES加密后的content，作为http请求的content参数
		String paramContent = AESUtils.encrypt(content, rKey);
			
		String signString = MD5Utils.md5Hex(URLEncoder.encode(content, "UTF-8"));
		// System.out.println("MD5加密后 signString="+signString);

		// String dekey = RSAUtils.decrypt(paramKey, privateKey);
		// System.out.println("dekey:"+dekey);
		// String deContent = AESUtils.decrypt(paramContent, dekey);
		// System.out.println("deContent:"+deContent);

		JSONObject reqJson = JsonUtils.toJSONObject(paramContent, paramKey, signString);

		System.out.println(reqJson.toJSONString());

		String url = "http://localhost:8082/debtws-api/openapi/v3/maidanxia/receiveassets";
//		String url = "https://partner.ssjlicai.com/debtws/openapi/v2/maidanxia/receiveassets";
		Map<String, String> headers = new HashMap<String, String>();
		String result = DebtHttpRequest.doPostByJson(url, reqJson, headers);
		System.out.println(result);
	}


	public static ZcOriginalDebtDTO getDebt(List<String> row) {
		ZcOriginalDebtDTO debt = new ZcOriginalDebtDTO(row.get(0), row.get(1), "", "1", "");
		List<ZcOriginalDebtItemDTO> debtList = new ArrayList<ZcOriginalDebtItemDTO>();
		
		ZcOriginalDebtItemDTO debt2 = getZcOriginalDebtItem(row);
		debtList.add(debt2);
		debt.setDebtList(debtList);
		return debt;
	}
	
	
	public static ZcOriginalDebtDTO getZcOriginalDebt() {

		File excelFile = new File("D:\\11.xlsx");

		try {
			TwoTuple<List<String>, List<List<String>>> debtData = ExcelUtil.readData(new FileInputStream(excelFile), 0);
			ZcOriginalDebtDTO debt = new ZcOriginalDebtDTO(debtData.second.get(0).get(0), debtData.second.get(0).get(1)
					+ "_", "", "1", "");
			List<ZcOriginalDebtItemDTO> debtList = new ArrayList<ZcOriginalDebtItemDTO>();
			for (List<String> row : debtData.second) {

				// ZcOriginalDebtItemDTO debt2 = getZcOriginalDebtItem(row);
				debtList.add(getZcOriginalDebtItem(row));
				debt.setDebtList(debtList);
				/*
				 * if (debt != null) { debtList.add(debt); }
				 */
				System.out.println(debtList.size());
			}

			for (List<String> d : debtData.second) {
				System.out.println("[解析Excel]==11==row={}" + d);
			}
			System.out.println("[解析Excel]===22=row.size={}" + debtData.second.size());
			return debt;
		} catch (Exception e) {
			e.printStackTrace();
		}


		return null;// debt;
	}
	
	public static ZcOriginalDebtItemDTO getZcOriginalDebtItem(List<String> row) {
		ZcOriginalDebtItemDTO debtItem = new ZcOriginalDebtItemDTO();
	
		String cert_id = row.get(5);
		//贷款产品类型
		debtItem.setProductType(row.get(12));
		//借款人手机号
		debtItem.setPhone(row.get(27));
		//借款人学历
		debtItem.setEducation(row.get(31));
		//民族
		debtItem.setNation(""); // TODO
		//薪资
		debtItem.setSalary(StringUtils.isEmpty(row.get(35)) ? 0L : Long.parseLong(row.get(35)));
		//职位
		debtItem.setPosition(""); // TODO
		//注册手机号
		debtItem.setRegisterPhone(row.get(27));
		//注册名
		debtItem.setRegisterName(row.get(24));
		//用户渠道：1网贷平台2 消费金融公司 3房地产服务公司 4其他公司
		debtItem.setUserChannel(1);
		//注册证件类型：1 身份证 2 护照
		debtItem.setRegisterCertType(1);
		//证件号码
		debtItem.setRegisterCertNumber(cert_id);
		//开户行
		debtItem.setReceiveOpen(row.get(6));
		//开户支行
		debtItem.setReceiveBranch(row.get(6));
		//绑卡银行编号如：招商银行CMB，农业银行ABC，工商银行ICBC
		debtItem.setBindBankNumber("CIB");
		//银行卡号
		debtItem.setBindBankCard(row.get(43) + "_");
		//银行卡绑定手机号
		debtItem.setBindPhone(row.get(9));
		//开户省份
		debtItem.setBindCardProvince(row.get(10));

		//开户城市
		debtItem.setBindCardCity(row.get(11));
		//资产类型：1.消费分期 2.个人信用贷 3.车贷 4.房贷 5.其他
		debtItem.setBorrowType(2);
		//借款金额
		debtItem.setBorrowContractAmount(new BigDecimal(row.get(20)).multiply(new BigDecimal("100")).longValue());// TODO,应取13
		//合同金额
		debtItem.setContractAmount(new BigDecimal(row.get(19)).multiply(new BigDecimal("100")).longValue());
		//放款金额
		debtItem.setDepositAmount(new BigDecimal(row.get(20)).multiply(new BigDecimal("100")).longValue());
		//借款期限类型：1、天 2、月
		debtItem.setLoanTimeType(2);
		//借款期数
		debtItem.setLoanTimeCount(Integer.parseInt(row.get(15)));
		//还款方式：1、一次性本息还款2、等额本息3、先息后本
		debtItem.setPaymentMode(2);
		
		//合同编号
		debtItem.setContractCode(row.get(18) + "_1");
		
		//申请借款期数
		debtItem.setRequestTimeCount(Integer.parseInt(row.get(21)));
		//申请借款用途
		debtItem.setLoanPurpose(row.get(22));
		//借款人月还款额
		debtItem.setMonthRepayAmount(new BigDecimal(row.get(23)).multiply(new BigDecimal("100")).longValue());
		//还款方式
		debtItem.setBorrowPaymentMode(2);
		//借款人姓名
		debtItem.setBorrowerName(row.get(24));
		//借款人证件号
		debtItem.setCertId(cert_id);
		//证件类型:1身份证 2:护照
		debtItem.setCertType(1);
		//居住地址
//		debtItem.setBorrowerAddress(row.get(28));
		debtItem.setBorrowerAddress("130532");
		debtItem.setHouseAddress("深圳平安祝你幸福");

		//性别:1为男2为女
		// debtItem.setBorrowerSex(row.get(29).trim().equals("女") ? 2 : 1);
		debtItem.setBorrowerSex(Integer.parseInt(row.get(29)));
		//婚姻状况：1 已婚 2 未婚
		debtItem.setIsMarried(2);// Integer.parseInt(row.get(30)));
		//个人年收入
		debtItem.setAnnualIncome(StringUtils.isEmpty(row.get(35)) ? 0L : Long.parseLong(row.get(35)) * 100);
		//车产
		debtItem.setCarAsset(row.get(36));
		//本地房产
		debtItem.setLocalHouseAsset(row.get(37));
		//居住情况
		debtItem.setHouseSituation("");// TODO
		//居住时长
		debtItem.setHouseTime(1);// TODO
		//子女情况
		debtItem.setChildSituation(""); // TODO
		//利率
		debtItem.setAnnualRate(new BigDecimal(row.get(38)));
		//客户等级
		debtItem.setCustomerLevel("");
		//客户渠道
		debtItem.setCustomerChannel("");
		//还款方式:1、一次性本息还款2、等额本息3、先息后本
		debtItem.setPaymentMode(2);
		//工作单位
		debtItem.setWorkUnit(row.get(40));
		//本单位工作年限
		debtItem.setWorkTime(StringUtils.isEmpty(row.get(41)) ? 0 : Integer.parseInt(row.get(41)));
		//总工作年限
		debtItem.setWorkSumTime(StringUtils.isEmpty(row.get(41)) ? 0 : Integer.parseInt(row.get(41)));
		//公积金基数
		debtItem.setGjjRadix(new BigDecimal("0")); // TODO
		//公积金缴纳期限
		debtItem.setGjjPaymentTerm(0); // TODO
		//公积金缴纳比例
		debtItem.setGjjPaymentRadio(new BigDecimal("0.12")); // TODO
		//是否是首次申请:1 是 2 否
		debtItem.setIsFirstRequest(1); // TODO
		//之前借款还款状态
		debtItem.setPriorLoanStatus("全部结清"); // TODO
		//历史结清次数
		debtItem.setHistoryCostTime(0); // TODO
		//银行卡账户名
		debtItem.setBankOpenName(row.get(24));
		//银行卡号
		debtItem.setReceiveBankCard(row.get(43) + "_");
		//银行名称
		debtItem.setReceiveBankName("");
		//银行所在地
		debtItem.setReceiveCountry("");
		//原始年利率
		debtItem.setOriAnnualRate(new BigDecimal("0.08"));
		//居间协议编号
		debtItem.setIntermediaryAgreementCode("123456");
		//职业代码
		debtItem.setCareerCode("20000");//职业类型编码

		debtItem.setRelationship(getDebtRelation(row));
		return debtItem;
	}
	
	public static List<ZcOriginalDebtRelationshipDTO> getDebtRelation(List<String> row) {
		ZcOriginalDebtRelationshipDTO relation = new ZcOriginalDebtRelationshipDTO(row.get(33), row.get(33),
				row.get(34));
		List<ZcOriginalDebtRelationshipDTO> relations = new ArrayList<ZcOriginalDebtRelationshipDTO>();
		relations.add(relation);
		return relations;
	}
}
