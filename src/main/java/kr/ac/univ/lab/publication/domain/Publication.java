package kr.ac.univ.lab.publication.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import kr.ac.univ.lab.common.domain.GeneralAudit;
import kr.ac.univ.lab.publication.domain.enums.KindType;
import kr.ac.univ.lab.publication.domain.enums.PublicationType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@Entity
@Table
@ToString
public class Publication extends GeneralAudit {
	@Column
	private String title;

	@Column
	private String authors;

	@Column
	@Enumerated(EnumType.STRING)
	private PublicationType publicationType;

	@Column
	@Enumerated(EnumType.STRING)
	private KindType kindType;

	@Column
	private String publishedIn;

	@Column
	private double impactFactor;

	@Column
	private LocalDate publishedDate;

	@Column
	private String pages;

	@Column
	private String volume;

	@Column
	private String number;

	@Column
	private String doiUrl;

	@Column
	private String issn;

	@Column
	private String remark;

	@Builder
	public Publication(String title, String authors, PublicationType publicationType, KindType kindType,
			String publishedIn, double impactFactor, LocalDate publishedDate, String pages, String volume,
			String number, String doiUrl, String issn, String remark) {
		this.title = title;
		this.authors = authors;
		this.publicationType = publicationType;
		this.kindType = kindType;
		this.publishedIn = publishedIn;
		this.impactFactor = impactFactor;
		this.publishedDate = publishedDate;
		this.pages = pages;
		this.volume = volume;
		this.number = number;
		this.doiUrl = doiUrl;
		this.issn = issn;
		this.remark = remark;
	}

	public void update(Publication publication) {
		this.title = publication.getTitle();
		this.authors = publication.getAuthors();
		this.publicationType = publication.getPublicationType();
		this.kindType = publication.getKindType();
		this.publishedIn = publication.getPublishedIn();
		this.impactFactor = publication.getImpactFactor();
		this.publishedDate = publication.getPublishedDate();
		this.pages = publication.getPages();
		this.volume = publication.getVolume();
		this.number = publication.getNumber();
		this.doiUrl = publication.getDoiUrl();
		this.issn = publication.getIssn();
		this.remark = publication.getRemark();
	}

}