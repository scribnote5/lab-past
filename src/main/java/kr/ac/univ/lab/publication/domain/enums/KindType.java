package kr.ac.univ.lab.publication.domain.enums;

public enum KindType {
	INTERNATIONAL("International"),
	DOMESTIC("Domestic"),
	;

	private String kindType;

	private KindType (String kindType) {
		this.kindType = kindType;
	}

	public String getKindType() {
		return this.kindType;
	}
}