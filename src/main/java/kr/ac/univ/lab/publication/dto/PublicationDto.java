package kr.ac.univ.lab.publication.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import kr.ac.univ.lab.common.dto.CommonDto;
import kr.ac.univ.lab.publication.domain.PublicationAttachedFile;
import kr.ac.univ.lab.publication.domain.enums.KindType;
import kr.ac.univ.lab.publication.domain.enums.PublicationType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class PublicationDto extends CommonDto{
	/*
	 * JPA Audit
	 */
	
	/*
	 * 기본 정보
	 */
	private String title;
	private String authors;
	private PublicationType publicationType;
	private KindType kindType;
	private String publishedIn;
	private double impactFactor;
	private LocalDate publishedDate;
	private String pages;
	private String volume;
	private String number;
	private String doiUrl;
	private String issn;
	private String remark;
	
	/*
	 * 첨부 파일
	 */
	private List<PublicationAttachedFile> attachedFileList = new ArrayList<PublicationAttachedFile>();
}