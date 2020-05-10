package kr.ac.univ.lab.member.domian.enums;

public enum GenderType {
	MALE("male"),
	FEMALE("female");

	private String type;

	private GenderType(String type) {
		this.type = type;
	}

	public String getSearchType() {
		return this.type;
	}
}