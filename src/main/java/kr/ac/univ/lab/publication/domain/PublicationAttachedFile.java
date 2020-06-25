package kr.ac.univ.lab.publication.domain;

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
public class PublicationAttachedFile extends AttachedFileAudit {
	@Column
	private Long publicationIdx;
	
	@Column
	private String savedFileName;

	@Column
	private String fileSize;

	@Builder
	public PublicationAttachedFile(Long publicationIdx, String savedFileName, String fileSize) {
		this.publicationIdx = publicationIdx;
		this.savedFileName = savedFileName;
		this.fileSize = fileSize;
	}
}