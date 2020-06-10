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
public class NoticeBoardComment extends GeneralAudit {
	@Column
	private Long noticeBoardIdx;
	
	@Column
	private String content;

	@Column
	@Enumerated(EnumType.STRING)
	private ActiveStatus activeStatus;

	@Builder
	public NoticeBoardComment(String content, ActiveStatus activeStatus) {
		this.content = content;
		this.activeStatus = activeStatus;
	}

	public void update(NoticeBoardComment noticeBoard) {
		this.content = noticeBoard.getContent();
		this.activeStatus = noticeBoard.getActiveStatus();
	}
}