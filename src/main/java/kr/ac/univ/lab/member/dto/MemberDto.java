package kr.ac.univ.lab.member.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import kr.ac.univ.lab.common.domain.enums.ActiveStatus;
import kr.ac.univ.lab.common.dto.CommonDto;
import kr.ac.univ.lab.common.validation.Contact;
import kr.ac.univ.lab.member.domian.MemberAttachedFile;
import kr.ac.univ.lab.member.domian.enums.AuthorityType;
import kr.ac.univ.lab.member.domian.enums.GenderType;
import kr.ac.univ.lab.member.domian.enums.MemberStatus;
import kr.ac.univ.lab.member.domian.enums.MemberType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class MemberDto extends CommonDto {
	/*
	 * JPA Audit
	 */

	/*
	 * 개인 정보
	 */
	@Size(min = 6, max = 16, message = "The member id can be used for more than 6 characters and less than 16characters.")
	private String memberId;
	@Size(min = 6, max = 16, message = "The password can be used for more than 6 characters and less than 16 characters.")
	private String password;
	@NotEmpty(message ="Korean name is empty.")
	private String koreanName;
	@NotEmpty(message ="English name is empty.")
	private String englishName;
	private GenderType gender;
	@Past(message ="BirthDate mus be past.")
	private LocalDate birthDate;
	@Email(message = "Email format is not valid.")
	private String email;
	@Email(message = "Email format is not valid.")
	private String privateEmail;
	@NotEmpty(message ="Messanger id is empty.")
	private String messangerId;
	@Contact(message = "Contact format is not valid.")
	private String contact;
	private MemberType memberType;
	private MemberStatus memberStatus;
	private String introduction;

	/*
	 * 부가 정보
	 */
	private LocalDate admissionDate;
	private LocalDate graduatedDate;
	private String workplace;
	private String webPage;

	/*
	 * 기타 정보
	 */
	private ActiveStatus activeStatus;
	private AuthorityType authorityType;

	/*
	 * 첨부 파일
	 */
	private List<MemberAttachedFile> attachedFileList = new ArrayList<MemberAttachedFile>();
}