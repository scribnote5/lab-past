package kr.ac.univ.lab.noticeBoard.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import kr.ac.univ.lab.common.domain.GeneralAudit;
import kr.ac.univ.lab.common.domain.enums.ActiveStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@Entity
@Table
@ToString
public class NoticeBoard extends GeneralAudit {
	@Column
	private String title;

	@Column
	private String content;

	@Column
	@Enumerated(EnumType.STRING)
	private ActiveStatus activeStatus;

	// 수정: default value
	@Column
	private Long viewCount;

	@Builder
	public NoticeBoard(String title, String content, ActiveStatus activeStatus, Long viewCount) {
		this.title = title;
		this.content = content;
		this.viewCount = viewCount;
		this.activeStatus = activeStatus;
	}

	public void update(NoticeBoard noticeBoard) {
		this.title = noticeBoard.getTitle();
		this.content = noticeBoard.getContent();
		this.activeStatus = noticeBoard.getActiveStatus();
	}
}