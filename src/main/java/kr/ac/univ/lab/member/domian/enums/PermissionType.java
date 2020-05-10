package kr.ac.univ.lab.member.domian.enums;

public enum PermissionType {
	NON_MEMBER("non_member"), 
	GENERAL("general"), 
	MANAGER("manager"),
	ROOT("root");

	private String type;

	private PermissionType(String type) {
		this.type = type;
	}

	public String getSearchType() {
		return this.type;
	}
}