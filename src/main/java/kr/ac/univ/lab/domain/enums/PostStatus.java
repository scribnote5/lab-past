package kr.ac.univ.lab.domain.enums;

public enum PostStatus {
	ACTIVE("active"), 
	INACTIVE("inactive"),
	NOTICE("notice");

	private String status;

	private PostStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return this.status;
	}
}