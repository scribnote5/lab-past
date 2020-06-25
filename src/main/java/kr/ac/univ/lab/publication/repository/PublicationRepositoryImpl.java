package kr.ac.univ.lab.publication.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import kr.ac.univ.lab.member.domian.MemberAttachedFile;
import kr.ac.univ.lab.publication.domain.Publication;
import kr.ac.univ.lab.publication.domain.QPublication;
import kr.ac.univ.lab.publication.domain.enums.KindType;
import kr.ac.univ.lab.publication.domain.enums.PublicationType;

@Repository
@Transactional
public class PublicationRepositoryImpl extends QuerydslRepositorySupport {
	private final JPAQueryFactory queryFactory;

	public PublicationRepositoryImpl(JPAQueryFactory queryFactory) {
		super(MemberAttachedFile.class);
		this.queryFactory = queryFactory;
	}

	public List<Publication> findTop10(Long lastIdx) {
		QPublication publication = QPublication.publication;
	  
		/* SELECT * 
		 *   FROM publication 
		 *  WHERE idx < 'lastIdx'
		 *  LIMIT 10 
		 */
	  	return queryFactory 
	  			.selectFrom(publication)
	  			.where(publication.idx.loe(lastIdx))
	  			.orderBy(publication.idx.desc())
	  			.limit(10)
	  			.fetch(); 
	  }

	
	public Publication findMaxPublicationIdx() {
		QPublication publication = QPublication.publication;
		
		return queryFactory 
	  			.selectFrom(publication)
	  			.orderBy(publication.idx.desc())
	  			.fetchFirst(); 
	}
	
	
	public List<Publication> findTop10ByTitleContaining(Long lastIdx, String keyword) {
		QPublication publication = QPublication.publication;
		
		return queryFactory 
				.selectFrom(publication)
	  			.where(publication.idx.loe(lastIdx),
	  				   publication.title.contains(keyword))
	  			.orderBy(publication.idx.desc())
	  			.limit(10)
	  			.fetch();  
	}
	
	public List<Publication> findTop10ByAuthorsContaining(Long lastIdx, String keyword) {
		QPublication publication = QPublication.publication;
		
		return queryFactory 
				.selectFrom(publication)
	  			.where(publication.idx.loe(lastIdx),
	  				   publication.authors.contains(keyword))
	  			.orderBy(publication.idx.desc())
	  			.limit(10)
	  			.fetch();  
	}
	
	public List<Publication> findTop10ByPublishedInContaining(Long lastIdx, String keyword) {
		QPublication publication = QPublication.publication;
		
		return queryFactory 
				.selectFrom(publication)
	  			.where(publication.idx.loe(lastIdx),
	  				   publication.publishedIn.contains(keyword))
	  			.orderBy(publication.idx.desc())
	  			.limit(10)
	  			.fetch();  
	}
	
	public List<Publication> findTop10ByInternationalAndJournal(Long lastIdx) {
		QPublication publication = QPublication.publication;
		
		return queryFactory 
				.selectFrom(publication)
	  			.where(publication.idx.loe(lastIdx),
	  				   publication.kindType.eq(KindType.INTERNATIONAL),
	  				   publication.publicationType.eq(PublicationType.JOURNAL))
	  			.orderBy(publication.idx.desc())
	  			.limit(10)
	  			.fetch();  
	}
	
	public List<Publication> findTop10ByInternationalAndConference(Long lastIdx) {
		QPublication publication = QPublication.publication;
		
		return queryFactory 
				.selectFrom(publication)
	  			.where(publication.idx.loe(lastIdx),
	  					publication.kindType.eq(KindType.INTERNATIONAL),
		  				   publication.publicationType.ne(PublicationType.JOURNAL))
	  			.orderBy(publication.idx.desc())
	  			.limit(10)
	  			.fetch();  
	}
	
	public List<Publication> findTop10ByDomesticAndJournal(Long lastIdx) {
		QPublication publication = QPublication.publication;
		
		return queryFactory 
				.selectFrom(publication)
	  			.where(publication.idx.loe(lastIdx),
	  					publication.kindType.eq(KindType.DOMESTIC),
		  				publication.publicationType.eq(PublicationType.JOURNAL))
	  			.orderBy(publication.idx.desc())
	  			.limit(10)
	  			.fetch();  
	}
	
	public List<Publication> findTop10ByDomesticAndConference(Long lastIdx) {
		QPublication publication = QPublication.publication;
		
		return queryFactory 
				.selectFrom(publication)
	  			.where(publication.idx.loe(lastIdx),
	  					publication.kindType.eq(KindType.DOMESTIC),
		  				publication.publicationType.ne(PublicationType.JOURNAL))
	  			.orderBy(publication.idx.desc())
	  			.limit(10)
	  			.fetch();  
	}
}