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
	private Long postIdx;
	
	@Column
	private String savedFileName;

	@Column
	private String fileSize;

	@Builder
	public NoticeBoardAttachedFile(Long postIdx, String savedFileName, String fileSize) {
		this.postIdx = postIdx;
		this.savedFileName = savedFileName;
		this.fileSize = fileSize;
	}
}