package kr.ac.univ.lab.noticeBoard.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import kr.ac.univ.lab.common.domain.enums.ActiveStatus;
import kr.ac.univ.lab.common.dto.CommonDto;
import kr.ac.univ.lab.common.validation.Editor;
import kr.ac.univ.lab.noticeBoard.domain.NoticeBoardAttachedFile;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class NoticeBoardDto extends CommonDto {
	/*
	 * JPA Audit
	 */

	/*
	 * 기본 정보
	 */
	@NotBlank(message = "The title must not be blank.")
	@Size(max = 50, message = "The title is up to 50 characters long.")
	private String title; 
	@Editor(max = 16777215, message="The editor's input size of bytes is exceeded.")
	private String content;
	private ActiveStatus activeStatus;
	private Long viewCount;
	private String memberId;

	/*
	 * AnonymousUser 여부
	 */
	private boolean isAnonymousUser;

	/*
	 * 첨부 파일
	 */
	private List<NoticeBoardAttachedFile> attachedFileList = new ArrayList<NoticeBoardAttachedFile>();

	/*
	 * newIcon 출력 여부
	 */
	private boolean isNewIcon;
}