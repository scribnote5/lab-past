package kr.ac.univ.lab.noticeBoard.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import kr.ac.univ.lab.common.domain.AttachedFileAudit;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@Entity
@Table
@ToString
@EntityListeners(AuditingEntityListener.class)
public class NoticeBoardAttachedFile extends AttachedFileAudit {
	@Column
	private Long noticeBoardIdx;
	
	@Column
	private String savedFileName;

	@Column
	private String fileSize;

	@Builder
	public NoticeBoardAttachedFile(Long noticeBoardIdx, String savedFileName, String fileSize) {
		this.noticeBoardIdx = noticeBoardIdx;
		this.savedFileName = savedFileName;
		this.fileSize = fileSize;
	}
}