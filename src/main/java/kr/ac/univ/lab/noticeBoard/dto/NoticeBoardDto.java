package kr.ac.univ.lab.noticeBoard.dto;

import java.util.ArrayList;
import java.util.List;

import kr.ac.univ.lab.common.domain.enums.ActiveStatus;
import kr.ac.univ.lab.common.dto.CommonDto;
import kr.ac.univ.lab.noticeBoard.domain.NoticeBoardAttachedFile;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class NoticeBoardDto extends CommonDto{
	/*
	 * JPA Audit
	 */
	
	/*
	 * 기본 정보
	 */
	private String title;
	private String content;
	private ActiveStatus activeStatus;
	private Long viewCount;
	private String memberId;
	
	/*
	 * 첨부 파일
	 */
	private List<NoticeBoardAttachedFile> attachedFileList = new ArrayList<NoticeBoardAttachedFile>();
	
	/*
	 * newIcon 출력 여부
	 */
	private boolean newIcon;
}