package com.hu.base.http.util.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ZcOriginalDebtConfirmDTO {

	@JsonProperty("contractNo")
	private String contractNo;
	@JsonProperty("borrowerName")
	private String borrowerName;
	@JsonProperty("receiveOpen")
	private String receiveOpen;
	@JsonProperty("receiveBranch")
	private String receiveBranch;
	@JsonProperty("bindBankNumber")
	private String bindBankNumber;
	@JsonProperty("bindBankCard")
	private String bindBankCard;
	@JsonProperty("bindPhone")
	private String bindPhone;
	@JsonProperty("bindCardProvince")
	private String bindCardProvince;
	@JsonProperty("bindCardCity")
	private String bindCardCity;
	@JsonProperty("debtStatus")
	private int debtStatus;
	
	
	
	public ZcOriginalDebtConfirmDTO(String contractNo, String borrowerName, String receiveOpen, String receiveBranch,
			String bindBankNumber, String bindBankCard, String bindPhone, String bindCardProvince, String bindCardCity,
			int debtStatus) {
		super();
		this.contractNo = contractNo;
		this.borrowerName = borrowerName;
		this.receiveOpen = receiveOpen;
		this.receiveBranch = receiveBranch;
		this.bindBankNumber = bindBankNumber;
		this.bindBankCard = bindBankCard;
		this.bindPhone = bindPhone;
		this.bindCardProvince = bindCardProvince;
		this.bindCardCity = bindCardCity;
		this.debtStatus = debtStatus;
	}
	public String getContractNo() {
		return contractNo;
	}
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}
	public String getBorrowerName() {
		return borrowerName;
	}
	public void setBorrowerName(String borrowerName) {
		this.borrowerName = borrowerName;
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
	public int getDebtStatus() {
		return debtStatus;
	}
	public void setDebtStatus(int debtStatus) {
		this.debtStatus = debtStatus;
	}

}
