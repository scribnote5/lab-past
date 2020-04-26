package kr.ac.univ.lab.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@Entity
@Table
@ToString
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
	private LocalDateTime createdDate;
	
	@Column
	private String memberId;

	@Builder
	public AttachedFile(Long boardId, String savedFileName, Long fileSize, LocalDateTime createdDate, String memberId) {
		this.boardId = boardId;
		this.savedFileName = savedFileName;
		this.fileSize = fileSize;
		this.createdDate = createdDate;
		this.memberId = memberId;
	}
	
}