package kr.ac.univ.lab.member.domian.enums;

public enum AuthorityType {
	NON_MEMBER("non_member"), 
	GENERAL("general"), 
	MANAGER("manager"),
	ROOT("root");

	private String type;

	private AuthorityType(String type) {
		this.type = type;
	}

	public String getAuthorityType() {
		return this.type;
	}
}