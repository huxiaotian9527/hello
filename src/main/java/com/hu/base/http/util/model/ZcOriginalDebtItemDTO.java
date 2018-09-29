//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.hu.base.http.util.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
public class ZcOriginalDebtItemDTO implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	@JsonProperty("phone")
    private String phone;
	@JsonProperty("education")
    private String education;
	@JsonProperty("nation")
    private String nation;
	@JsonProperty("salary")
    private Long salary;
	@JsonProperty("position")
    private String position;
	@JsonProperty("register_phone")
    private String registerPhone;
	@JsonProperty("register_name")
    private String registerName;
	@JsonProperty("user_channel")
    private Integer userChannel;
	@JsonProperty("register_cert_type")
    private Integer registerCertType;
	@JsonProperty("register_cert_number")
    private String registerCertNumber;
	@JsonProperty("receive_open")
    private String receiveOpen;
    @JsonProperty("receive_branch")
    private String receiveBranch;
    @JsonProperty("bind_bank_number")
    private String bindBankNumber;
    @JsonProperty("bind_bank_card")
    private String bindBankCard;
    @JsonProperty("bind_phone")
    private String bindPhone;
    @JsonProperty("bind_card_province")
    private String bindCardProvince;
    @JsonProperty("bind_card_city")
    private String bindCardCity;
    @JsonProperty("borrow_type")
    private Integer borrowType;
    @JsonProperty("borrow_contract_amount")
    private Long borrowContractAmount;
    @JsonProperty("loan_time_type")
    private Integer loanTimeType;
    @JsonProperty("loan_time_count")
    private Integer loanTimeCount;
    @JsonProperty("borrow_payment_mode")
    private Integer borrowPaymentMode;
    @JsonProperty("product_type")
    private String productType;
    @JsonProperty("contract_code")
    private String contractCode;
    @JsonProperty("contract_amount")
    private Long contractAmount;
    @JsonProperty("deposit_amount")
    private Long depositAmount;
    @JsonProperty("request_time_count")
    private Integer requestTimeCount;
    @JsonProperty("loan_purpose")
    private String loanPurpose;
    @JsonProperty("month_repay_amount")
    private Long monthRepayAmount;
    @JsonProperty("borrower_name")
    private String borrowerName;
    @JsonProperty("cert_id")
    private String certId;
    @JsonProperty("cert_type")
    private Integer certType;
    @JsonProperty("borrower_address")
    private String borrowerAddress;
    @JsonProperty("borrower_sex")
    private Integer borrowerSex;
    @JsonProperty("is_married")
    private Integer isMarried;
    @JsonProperty("iqianjin_id")
    private Integer iqianjinId;
    @JsonProperty("annual_income")
    private Long annualIncome;
    @JsonProperty("car_asset")
    private String carAsset;
    @JsonProperty("local_house_asset")
    private String localHouseAsset;
    @JsonProperty("house_situation")
    private String houseSituation;
    @JsonProperty("house_time")
    private Integer houseTime;
    @JsonProperty("child_situation")
    private String childSituation;
    @JsonProperty("annual_rate")
    private BigDecimal annualRate;
    @JsonProperty("customer_level")
    private String customerLevel;
    @JsonProperty("customer_channel")
    private String customerChannel;
    @JsonProperty("payment_mode")
    private Integer paymentMode;
    @JsonProperty("work_unit")
    private String workUnit;
    @JsonProperty("work_time")
    private Integer workTime;
    @JsonProperty("work_sum_time")
    private Integer workSumTime;
    @JsonProperty("gjj_radix")
    private BigDecimal gjjRadix;
    @JsonProperty("gjj_payment_term")
    private Integer gjjPaymentTerm;
    @JsonProperty("gjj_payment_radio")
    private BigDecimal gjjPaymentRadio;
    @JsonProperty("is_first_request")
    private Integer isFirstRequest;
    @JsonProperty("prior_loan_status")
    private String priorLoanStatus;
    @JsonProperty("history_paid_count")
    private Integer historyPaidCount;
    @JsonProperty("bank_open_name")
    private String bankOpenName;
    @JsonProperty("receive_bank_card")
    private String receiveBankCard;
    @JsonProperty("receive_bank_name")
    private String receiveBankName;
    @JsonProperty("receive_country")
    private String receiveCountry;
    @JsonProperty("loan_id")
    private String loanId;
    @JsonProperty("history_cost_time")
    private Integer historyCostTime;
    @JsonProperty("monthly_payment_amount")
    private Long monthlyPaymentAmount;
	@JsonProperty("intermediary_agreement_code")
	private String intermediaryAgreementCode;
	@JsonProperty("ori_annual_rate")
	private BigDecimal oriAnnualRate;
	@JsonProperty("career_code")
	private String  careerCode;//职业类型编码

	@JsonProperty("house_address")
	private String houseAddress; // 居住详细地址




	@JsonProperty("relationship")
    private List<ZcOriginalDebtRelationshipDTO> relationship;
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public Long getSalary() {
		return salary;
	}

	public void setSalary(Long salary) {
		this.salary = salary;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getRegisterPhone() {
		return registerPhone;
	}

	public void setRegisterPhone(String registerPhone) {
		this.registerPhone = registerPhone;
	}

	public String getRegisterName() {
		return registerName;
	}

	public void setRegisterName(String registerName) {
		this.registerName = registerName;
	}

	public Integer getUserChannel() {
		return userChannel;
	}

	public void setUserChannel(Integer userChannel) {
		this.userChannel = userChannel;
	}

	public Integer getRegisterCertType() {
		return registerCertType;
	}

	public void setRegisterCertType(Integer registerCertType) {
		this.registerCertType = registerCertType;
	}

	public String getRegisterCertNumber() {
		return registerCertNumber;
	}

	public void setRegisterCertNumber(String registerCertNumber) {
		this.registerCertNumber = registerCertNumber;
	}

	public String getReceiveOpen() {
		return receiveOpen;
	}

	public void setReceiveOpen(String receiveOpen) {
		this.receiveOpen = receiveOpen;
	}

	public String getReceiveBranch() {
		return receiveBranch;
	}

	public void setReceiveBranch(String receiveBranch) {
		this.receiveBranch = receiveBranch;
	}

	public String getBindBankNumber() {
		return bindBankNumber;
	}

	public void setBindBankNumber(String bindBankNumber) {
		this.bindBankNumber = bindBankNumber;
	}

	public String getBindBankCard() {
		return bindBankCard;
	}

	public void setBindBankCard(String bindBankCard) {
		this.bindBankCard = bindBankCard;
	}

	public String getBindPhone() {
		return bindPhone;
	}

	public void setBindPhone(String bindPhone) {
		this.bindPhone = bindPhone;
	}

	public String getBindCardProvince() {
		return bindCardProvince;
	}

	public void setBindCardProvince(String bindCardProvince) {
		this.bindCardProvince = bindCardProvince;
	}

	public String getBindCardCity() {
		return bindCardCity;
	}

	public void setBindCardCity(String bindCardCity) {
		this.bindCardCity = bindCardCity;
	}

	public Integer getBorrowType() {
		return borrowType;
	}

	public void setBorrowType(Integer borrowType) {
		this.borrowType = borrowType;
	}

	public Long getBorrowContractAmount() {
		return borrowContractAmount;
	}

	public void setBorrowContractAmount(Long borrowContractAmount) {
		this.borrowContractAmount = borrowContractAmount;
	}

	public Integer getLoanTimeType() {
		return loanTimeType;
	}

	public void setLoanTimeType(Integer loanTimeType) {
		this.loanTimeType = loanTimeType;
	}

	public Integer getLoanTimeCount() {
		return loanTimeCount;
	}

	public void setLoanTimeCount(Integer loanTimeCount) {
		this.loanTimeCount = loanTimeCount;
	}

	public Integer getBorrowPaymentMode() {
		return borrowPaymentMode;
	}

	public void setBorrowPaymentMode(Integer borrowPaymentMode) {
		this.borrowPaymentMode = borrowPaymentMode;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getContractCode() {
		return contractCode;
	}

	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}

	public Long getContractAmount() {
		return contractAmount;
	}

	public void setContractAmount(Long contractAmount) {
		this.contractAmount = contractAmount;
	}

	public Long getDepositAmount() {
		return depositAmount;
	}

	public void setDepositAmount(Long depositAmount) {
		this.depositAmount = depositAmount;
	}

	public Integer getRequestTimeCount() {
		return requestTimeCount;
	}

	public void setRequestTimeCount(Integer requestTimeCount) {
		this.requestTimeCount = requestTimeCount;
	}

	public String getLoanPurpose() {
		return loanPurpose;
	}

	public void setLoanPurpose(String loanPurpose) {
		this.loanPurpose = loanPurpose;
	}

	public Long getMonthRepayAmount() {
		return monthRepayAmount;
	}

	public void setMonthRepayAmount(Long monthRepayAmount) {
		this.monthRepayAmount = monthRepayAmount;
	}

	public String getBorrowerName() {
		return borrowerName;
	}

	public void setBorrowerName(String borrowerName) {
		this.borrowerName = borrowerName;
	}

	public String getCertId() {
		return certId;
	}

	public void setCertId(String certId) {
		this.certId = certId;
	}

	public Integer getCertType() {
		return certType;
	}

	public void setCertType(Integer certType) {
		this.certType = certType;
	}

	public String getBorrowerAddress() {
		return borrowerAddress;
	}

	public void setBorrowerAddress(String borrowerAddress) {
		this.borrowerAddress = borrowerAddress;
	}

	public Integer getBorrowerSex() {
		return borrowerSex;
	}

	public void setBorrowerSex(Integer borrowerSex) {
		this.borrowerSex = borrowerSex;
	}

	public Integer getIsMarried() {
		return isMarried;
	}

	public void setIsMarried(Integer isMarried) {
		this.isMarried = isMarried;
	}

	public Integer getIqianjinId() {
		return iqianjinId;
	}

	public void setIqianjinId(Integer iqianjinId) {
		this.iqianjinId = iqianjinId;
	}

	public Long getAnnualIncome() {
		return annualIncome;
	}

	public void setAnnualIncome(Long annualIncome) {
		this.annualIncome = annualIncome;
	}

	public String getCarAsset() {
		return carAsset;
	}

	public void setCarAsset(String carAsset) {
		this.carAsset = carAsset;
	}

	public String getLocalHouseAsset() {
		return localHouseAsset;
	}

	public void setLocalHouseAsset(String localHouseAsset) {
		this.localHouseAsset = localHouseAsset;
	}

	public String getHouseSituation() {
		return houseSituation;
	}

	public void setHouseSituation(String houseSituation) {
		this.houseSituation = houseSituation;
	}

	public Integer getHouseTime() {
		return houseTime;
	}

	public void setHouseTime(Integer houseTime) {
		this.houseTime = houseTime;
	}

	public String getChildSituation() {
		return childSituation;
	}

	public void setChildSituation(String childSituation) {
		this.childSituation = childSituation;
	}

	public BigDecimal getAnnualRate() {
		return annualRate;
	}

	public void setAnnualRate(BigDecimal annualRate) {
		this.annualRate = annualRate;
	}

	public String getCustomerLevel() {
		return customerLevel;
	}

	public void setCustomerLevel(String customerLevel) {
		this.customerLevel = customerLevel;
	}

	public String getCustomerChannel() {
		return customerChannel;
	}

	public void setCustomerChannel(String customerChannel) {
		this.customerChannel = customerChannel;
	}

	public Integer getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(Integer paymentMode) {
		this.paymentMode = paymentMode;
	}

	public String getWorkUnit() {
		return workUnit;
	}

	public void setWorkUnit(String workUnit) {
		this.workUnit = workUnit;
	}

	public Integer getWorkTime() {
		return workTime;
	}

	public void setWorkTime(Integer workTime) {
		this.workTime = workTime;
	}

	public Integer getWorkSumTime() {
		return workSumTime;
	}

	public void setWorkSumTime(Integer workSumTime) {
		this.workSumTime = workSumTime;
	}

	public BigDecimal getGjjRadix() {
		return gjjRadix;
	}

	public void setGjjRadix(BigDecimal gjjRadix) {
		this.gjjRadix = gjjRadix;
	}

	public Integer getGjjPaymentTerm() {
		return gjjPaymentTerm;
	}

	public void setGjjPaymentTerm(Integer gjjPaymentTerm) {
		this.gjjPaymentTerm = gjjPaymentTerm;
	}

	public BigDecimal getGjjPaymentRadio() {
		return gjjPaymentRadio;
	}

	public void setGjjPaymentRadio(BigDecimal gjjPaymentRadio) {
		this.gjjPaymentRadio = gjjPaymentRadio;
	}

	public Integer getIsFirstRequest() {
		return isFirstRequest;
	}

	public void setIsFirstRequest(Integer isFirstRequest) {
		this.isFirstRequest = isFirstRequest;
	}

	public String getPriorLoanStatus() {
		return priorLoanStatus;
	}

	public void setPriorLoanStatus(String priorLoanStatus) {
		this.priorLoanStatus = priorLoanStatus;
	}

	public Integer getHistoryPaidCount() {
		return historyPaidCount;
	}

	public void setHistoryPaidCount(Integer historyPaidCount) {
		this.historyPaidCount = historyPaidCount;
	}

	public String getBankOpenName() {
		return bankOpenName;
	}

	public void setBankOpenName(String bankOpenName) {
		this.bankOpenName = bankOpenName;
	}

	public String getReceiveBankCard() {
		return receiveBankCard;
	}

	public void setReceiveBankCard(String receiveBankCard) {
		this.receiveBankCard = receiveBankCard;
	}

	public String getReceiveBankName() {
		return receiveBankName;
	}

	public void setReceiveBankName(String receiveBankName) {
		this.receiveBankName = receiveBankName;
	}

	public String getReceiveCountry() {
		return receiveCountry;
	}

	public void setReceiveCountry(String receiveCountry) {
		this.receiveCountry = receiveCountry;
	}

	public String getLoanId() {
		return loanId;
	}

	public void setLoanId(String loanId) {
		this.loanId = loanId;
	}

	public Integer getHistoryCostTime() {
		return historyCostTime;
	}

	public void setHistoryCostTime(Integer historyCostTime) {
		this.historyCostTime = historyCostTime;
	}

	public Long getMonthlyPaymentAmount() {
		return monthlyPaymentAmount;
	}

	public void setMonthlyPaymentAmount(Long monthlyPaymentAmount) {
		this.monthlyPaymentAmount = monthlyPaymentAmount;
	}

	public List<ZcOriginalDebtRelationshipDTO> getRelationship() {
		return relationship;
	}

	public void setRelationship(List<ZcOriginalDebtRelationshipDTO> relationship) {
		this.relationship = relationship;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
    

}
