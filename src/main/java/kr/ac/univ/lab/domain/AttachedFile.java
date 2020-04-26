package kr.ac.univ.lab.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
public class AttachedFile {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private Long boardId;
	
	@Column
	private String savedFileName;

	@Column
	private Long fileSize;
	
	@Column
	private Long downloadCount;
	
	@Column
	@CreatedDate
	private LocalDateTime createdDate;
	
	@Column
	private String memberId;

	@Builder
	public AttachedFile(Long boardId, String savedFileName, Long fileSize, String memberId) {
		this.boardId = boardId;
		this.savedFileName = savedFileName;
		this.fileSize = fileSize;
		this.memberId = memberId;
	}
}