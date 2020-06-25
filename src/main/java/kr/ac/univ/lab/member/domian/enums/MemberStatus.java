package kr.ac.univ.lab.member.domian.enums;

public enum MemberStatus {
	ATTENDING("attending"),
	GRADUATED("graduated");
	
	private String status;

	private MemberStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return this.status;
	}
}