package kr.ac.univ.lab.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import kr.ac.univ.lab.domain.AttachedFile;
import kr.ac.univ.lab.domain.enums.PostStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class NoticeBoardDto {
	private Long id;
	private String title;
	private String content;
	private PostStatus postStatus;
	private Long viewCount;
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;
	private String memberId;
	private List<AttachedFile> attachedFileList = new ArrayList<AttachedFile>();
}