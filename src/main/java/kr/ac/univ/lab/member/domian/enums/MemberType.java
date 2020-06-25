package kr.ac.univ.lab.member.domian.enums;

public enum MemberType {
	FACULTY("Faculty"),
	FULL_TIME_PhD("full_time_Ph.d."),
	PART_TIME_PhD("part_time_Ph.d."),
	FULL_TIME_MS("full_time_M.S."),
	PART_TIME_MS("part_time_M.S."),
	BS("B.S.");
	
	private String type;

	private MemberType(String type) {
		this.type = type;
	}

	public String getType() {
		return this.type;
	}
}