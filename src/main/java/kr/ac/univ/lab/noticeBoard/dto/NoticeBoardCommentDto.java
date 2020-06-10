package kr.ac.univ.lab.noticeBoard.dto;

import kr.ac.univ.lab.common.domain.enums.ActiveStatus;
import kr.ac.univ.lab.common.dto.CommonDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class NoticeBoardCommentDto extends CommonDto{
	/*
	 * JPA Audit
	 */
	
	/*
	 * 기본 정보
	 */
	private String content;
	private ActiveStatus activeStatus;

	/*
	 * 접근 여부
	 */
	private boolean isAccess;
	
	/*
	 * newIcon 출력 여부
	 */
	private boolean isNewIcon;
}