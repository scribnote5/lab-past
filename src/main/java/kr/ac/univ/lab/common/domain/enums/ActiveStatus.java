package kr.ac.univ.lab.common.domain.enums;

public enum ActiveStatus {
	ACTIVE("active"), 
	INACTIVE("inactive");

	private String status;

	private ActiveStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return this.status;
	}
}