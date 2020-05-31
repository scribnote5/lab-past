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
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import kr.ac.univ.lab.member.domian.Member;
import lombok.Getter;

@MappedSuperclass
@Getter
@EntityListeners(AuditingEntityListener.class)
public abstract class AttachedFileAudit {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long idx;
	
	@Column(nullable = false, updatable = false)
	@CreatedDate
	private LocalDateTime createdDate;
	
	@CreatedBy
	private String createdBy;
}