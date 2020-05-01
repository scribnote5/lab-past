package kr.ac.univ.lab.domain.enums;

public enum SearchType {
	NONE("none"),
	TITLE("title"), 
	CONTENT("content"),
	MEMBER_ID("memberId");

	private String type;

	private SearchType(String type) {
		this.type = type;
	}

	public String getSearchType() {
		return this.type;
	}
}