package kr.ac.univ.lab.member.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import kr.ac.univ.lab.common.domain.enums.ActiveStatus;
import kr.ac.univ.lab.common.dto.CommonDto;
import kr.ac.univ.lab.member.domian.MemberAttachedFile;
import kr.ac.univ.lab.member.domian.enums.GenderType;
import kr.ac.univ.lab.member.domian.enums.MemberStatus;
import kr.ac.univ.lab.member.domian.enums.MemberType;
import kr.ac.univ.lab.member.domian.enums.PermissionType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class MemberDto extends CommonDto{
	/*
	 * JPA Audit
	 */
	
	/*
	 * 기본 정보
	 */
	private String memberId;
	private String password;
	private String koreanName;
	private String englishName;
	private GenderType gender;
	private LocalDate birthDate;
	private String email;
	private String messangerId;
	private String contact;
	private MemberType memberType;
	private MemberStatus memberStatus;
	private String introduction;
	
	/*
	 * 부가 정보
	 */
	private LocalDate admissionDate;
	private LocalDate graduationDate;
	private String workplace;
	private String webPage;
	
	/*
	 * 관리자가 수정하는 정보
	 */
	private ActiveStatus activeStatus;
	private PermissionType permissionType;
	
	/*
	 * 첨부 파일
	 */
	private List<MemberAttachedFile> attachedFileList = new ArrayList<MemberAttachedFile>();
}