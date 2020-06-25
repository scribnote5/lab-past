package kr.ac.univ.lab.noticeBoard.domain.enums;

public enum SearchType {
	NONE("none"),
	TITLE("title"),
	CONTENT("content")
	;

	private String type;

	private SearchType(String type) {
		this.type = type;
	}

	public String getSearchType() {
		return this.type;
	}
}