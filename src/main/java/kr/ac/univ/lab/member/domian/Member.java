package kr.ac.univ.lab.member.domian;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import kr.ac.univ.lab.common.domain.GeneralAudit;
import kr.ac.univ.lab.common.domain.enums.ActiveStatus;
import kr.ac.univ.lab.member.domian.enums.AuthorityType;
import kr.ac.univ.lab.member.domian.enums.GenderType;
import kr.ac.univ.lab.member.domian.enums.MemberStatus;
import kr.ac.univ.lab.member.domian.enums.MemberType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@Entity
@Table
@ToString
public class Member extends GeneralAudit {
	/*
	 * 기본 정보
	 */
	@Column
	private String memberId;

	@Column
	private String password;

	@Column
	private String koreanName;

	@Column
	private String englishName;

	@Column
	@Enumerated(EnumType.STRING)
	private GenderType gender;

	@Column
	private LocalDate birthDate;

	@Column
	private String email;

	@Column
	private String messangerId;

	@Column
	private String contact;

	@Column
	@Enumerated(EnumType.STRING)
	private MemberType memberType;

	@Column
	@Enumerated(EnumType.STRING)
	private MemberStatus memberStatus;
	
	@Column
	private String introduction;

	/*
	 * 부가 정보
	 */
	@Column
	private LocalDate admissionDate;

	@Column
	private LocalDate graduationDate;
	
	@Column
	private String webPage;
	
	@Column
	private String workplace;

	/*
	 * 관리자가 수정하는 정보
	 */
	@Column
	@Enumerated(EnumType.STRING)
	private ActiveStatus activeStatus;

	@Column
	@Enumerated(EnumType.STRING)
	private AuthorityType authorityType;

	/*
	 * 자동으로 업데이트 되는 정보
	 */
	@Column
	private LocalDateTime lastLoginDate;

	
	@Builder
	public Member(String memberId, String password, String koreanName, String englishName, GenderType gender, LocalDate birthDate, String email, String messangerId, String contact, MemberType memberType, MemberStatus memberStatus, String introduction, LocalDate admissionDate, LocalDate graduationDate, String webPage, String workplace, ActiveStatus activeStatus, AuthorityType authorityType, LocalDateTime lastLoginDate ) {
		this.memberId = memberId;
		this.password = password;
		this.koreanName = koreanName;
		this.englishName = englishName;
		this.gender = gender;
		this.birthDate = birthDate;
		this.email = email;
		this.messangerId = messangerId;
		this.contact = contact;
		this.memberType = memberType;
		this.memberStatus = memberStatus;
		this.introduction = introduction;
		this.webPage = webPage;
		this.workplace = workplace;
		this.admissionDate = admissionDate;
		this.graduationDate = graduationDate;
		this.activeStatus = activeStatus;
		this.authorityType = authorityType;
		this.lastLoginDate = lastLoginDate;
	}
	
	public void update(Member member) {
		this.memberId = member.getMemberId();
		this.password = member.getPassword();
		this.koreanName = member.getKoreanName();
		this.englishName = member.getEnglishName();
		this.gender = member.getGender();
		this.birthDate = member.getBirthDate();
		this.email = member.getEmail();
		this.messangerId = member.getMessangerId();
		this.contact = member.getContact();
		this.memberType = member.getMemberType();
		this.memberStatus = member.getMemberStatus();
		this.introduction = member.getIntroduction();
		this.webPage = member.getWebPage();
		this.workplace = member.getWorkplace();
		this.admissionDate = member.getAdmissionDate();
		this.graduationDate = member.getGraduationDate();
		this.activeStatus = member.getActiveStatus();
		this.authorityType = member.getAuthorityType();
	}
}