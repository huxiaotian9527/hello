package com.hu.base.http.util.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ZcOriginalDebtDTO {
	//������������
	@JsonProperty("org_name")
	private String orgName;
	//���δ���
	@JsonProperty("batch_no")
	private String batchNo;
	//�������������
	@JsonProperty("super_borrower_name")
	private String superBorrowerNme;
	//���������֤������
	@JsonProperty("super_borrower_cert_type")
	private String superBorrowerCertType;
	//���������֤����
	@JsonProperty("super_borrower_cert_id")
	private String superBorrowerCertId;
	
	@JsonProperty("debtList")
	private List<ZcOriginalDebtItemDTO> debtList;
	
	public ZcOriginalDebtDTO(String orgName, String batchNo, String superBorrowerNme, String superBorrowerCertType,
			String superBorrowerCertId) {
		super();
		this.orgName = orgName;
		this.batchNo = batchNo;
		this.superBorrowerNme = superBorrowerNme;
		this.superBorrowerCertType = superBorrowerCertType;
		this.superBorrowerCertId = superBorrowerCertId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	public String getSuperBorrowerNme() {
		return superBorrowerNme;
	}
	public void setSuperBorrowerNme(String superBorrowerNme) {
		this.superBorrowerNme = superBorrowerNme;
	}
	public String getSuperBorrowerCertType() {
		return superBorrowerCertType;
	}
	public void setSuperBorrowerCertType(String superBorrowerCertType) {
		this.superBorrowerCertType = superBorrowerCertType;
	}
	public String getSuperBorrowerCertId() {
		return superBorrowerCertId;
	}
	public void setSuperBorrowerCertId(String superBorrowerCertId) {
		this.superBorrowerCertId = superBorrowerCertId;
	}
	public List<ZcOriginalDebtItemDTO> getDebtList() {
		return debtList;
	}
	public void setDebtList(List<ZcOriginalDebtItemDTO> debtList) {
		this.debtList = debtList;
	}
	
	
}
