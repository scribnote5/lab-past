package kr.ac.univ.lab.publication.domain.enums;

public enum PublicationType {
	JOURNAL("Journal"),
	CONFERENCE("Conference"),
	POSTER("Poster"),
	REGULAR("Regular"),
	DEMO("Demo"),
	WORKSHOP("Workshop"),
	WORKINPROCESS("Work in Process")
	;

	private String publicationType;

	private PublicationType(String publicationType) {
		this.publicationType = publicationType;
	}

	public String getPublicationType() {
		return this.publicationType;
	}
}