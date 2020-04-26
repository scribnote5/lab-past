package kr.ac.univ.lab.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import kr.ac.univ.lab.domain.AttachedFile;
import kr.ac.univ.lab.domain.QAttachedFile;;

@Repository
@Transactional
public class AttachedFileRepositoryImpl extends QuerydslRepositorySupport {
	private final JPAQueryFactory queryFactory;

	public AttachedFileRepositoryImpl(JPAQueryFactory queryFactory) {
		super(AttachedFile.class);
		this.queryFactory = queryFactory;
	}

	public List<AttachedFile> findAttachedFileByBoardId(Long boardId) {
	  QAttachedFile attachedFile = QAttachedFile.attachedFile;
	  
	  
		/* SELECT * 
		 *   FROM AttachedFile 
		 *  WHERE boardId = 'boardId' 
		 */
	  	return queryFactory 
	  			.selectFrom(attachedFile)
	  			.where(attachedFile.boardId.eq(boardId)) 
	  			.fetch(); 
	  }

	public Long deleteAttachedFileByBoardId(Long boardId) { 
		QAttachedFile attachedFile = QAttachedFile.attachedFile;
	  
	  
	 	/* SELECT * 
	 	 *   FROM AttachedFile 
	 	 *  WHERE boardId = 'boardId' 
	 	 */
	  return queryFactory 
			  .delete(attachedFile)
			  .where(attachedFile.boardId.eq(boardId)) 
			  .execute(); 
	  }
}