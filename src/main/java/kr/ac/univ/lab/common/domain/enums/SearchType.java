package kr.ac.univ.lab.common.domain.enums;

public enum SearchType {
	NONE("none"),
	TITLE("title"),
	CONTENT("content"),
	
	MEMBER_ID("memberId"),
	KOREAN_NAME("koreanName"),
	EMAIL("email"),
	
	AUTHORS("authors"),
	PUBLISHED_IN("publishedIn")
	;

	private String type;

	private SearchType(String type) {
		this.type = type;
	}

	public String getSearchType() {
		return this.type;
	}
}