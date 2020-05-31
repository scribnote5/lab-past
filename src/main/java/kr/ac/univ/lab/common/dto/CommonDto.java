package kr.ac.univ.lab.common.dto;

import java.time.LocalDateTime;

import kr.ac.univ.lab.member.domian.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CommonDto {
	private Long idx;
	private LocalDateTime createdDate;
	private LocalDateTime lastModifiedDate;
	private String createdBy;
	private String lastModifiedBy;
}