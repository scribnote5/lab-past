package kr.ac.univ.lab.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import kr.ac.univ.lab.domain.enums.PostStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table
public class NoticeBoard {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idx;

	@Column
	private String title;

	@Column
	private String content;

	@Column
	@Enumerated(EnumType.STRING)
	private PostStatus postStatus;
	
	@Column
	private Long viewCount;
	
	@Column()
	private LocalDateTime createdDate;
	
	@Column
	private LocalDateTime updatedDate;

	@Builder
	public NoticeBoard(String title, String content, PostStatus postStatus, Long viewCount, LocalDateTime createdDate, LocalDateTime updatedDate) {
		this.title = title;
		this.content = content;
		this.viewCount = viewCount;
		this.postStatus = postStatus;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
	}
	
	public void setCreatedDateNow() {
        this.createdDate = LocalDateTime.now();
    }
    
    public void update(NoticeBoard noticeBoard) {
    	this.title = noticeBoard.getTitle();
		this.content = noticeBoard.getContent();
		this.postStatus = noticeBoard.getPostStatus();
		this.updatedDate = LocalDateTime.now();
    }
}