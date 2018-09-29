package com.hu.base.http.util.model;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @author chl
 * @version 1.0
 * @calssName ZcOriginalDebtRelationshipDTO
 * @description 鍊烘潈鍘熷鏁版嵁鐢ㄦ埛鍏崇郴
 * @date 2016/11/19
 */
public class ZcOriginalDebtRelationshipDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("relationship_name")
	private String relationshipName;

	
	@JsonProperty("relationship_type")
	private String relationshipType;
	public ZcOriginalDebtRelationshipDTO(String relationshipName, String relationshipType, String relationshipPhone) {
		super();
		this.relationshipName = relationshipName;
		this.relationshipType = relationshipType;
		this.relationshipPhone = relationshipPhone;
	}

	@JsonProperty("relationship_phone")
	private String relationshipPhone;

	public String getRelationshipName() {
		return relationshipName;
	}

	public void setRelationshipName(String relationshipName) {
		this.relationshipName = relationshipName;
	}

	public String getRelationshipType() {
		return relationshipType;
	}

	public void setRelationshipType(String relationshipType) {
		this.relationshipType = relationshipType;
	}

	public String getRelationshipPhone() {
		return relationshipPhone;
	}

	public void setRelationshipPhone(String relationshipPhone) {
		this.relationshipPhone = relationshipPhone;
	}
}