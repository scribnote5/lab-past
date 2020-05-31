package kr.ac.univ.lab.common.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;

@MappedSuperclass
@Getter
@EntityListeners(AuditingEntityListener.class)
public abstract class GeneralAudit {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long idx;

	@Column(nullable = false, updatable = false)
	@CreatedDate
	private LocalDateTime createdDate;

	@LastModifiedDate
	private LocalDateTime lastModifiedDate;

	@CreatedBy
	private String createdBy;

	@LastModifiedBy
	private String lastModifiedBy;
}