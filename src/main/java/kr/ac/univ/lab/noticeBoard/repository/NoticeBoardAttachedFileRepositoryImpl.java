package kr.ac.univ.lab.noticeBoard.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import kr.ac.univ.lab.member.domian.MemberAttachedFile;
import kr.ac.univ.lab.noticeBoard.domain.NoticeBoardAttachedFile;
import kr.ac.univ.lab.noticeBoard.domain.QNoticeBoardAttachedFile;;

@Repository
@Transactional
public class NoticeBoardAttachedFileRepositoryImpl extends QuerydslRepositorySupport {
	private final JPAQueryFactory queryFactory;

	public NoticeBoardAttachedFileRepositoryImpl(JPAQueryFactory queryFactory) {
		super(MemberAttachedFile.class);
		this.queryFactory = queryFactory;
	}

	public List<NoticeBoardAttachedFile> findAttachedFileByPostId(Long postId) {
		QNoticeBoardAttachedFile noticeBoardAttachedFile = QNoticeBoardAttachedFile.noticeBoardAttachedFile;
	  
		/* SELECT * 
		 *   FROM AttachedFile 
		 *  WHERE postId = 'postId' 
		 */
	  	return queryFactory 
	  			.selectFrom(noticeBoardAttachedFile)
	  			.where(noticeBoardAttachedFile.postIdx.eq(postId)) 
	  			.fetch(); 
	  }

	public Long deleteAttachedFileByPostId(Long postIdx) { 
		QNoticeBoardAttachedFile noticeBoardAttachedFile = QNoticeBoardAttachedFile.noticeBoardAttachedFile;
		  
	 	/* DELETE FROM AttachedFile  
	 	 *  WHERE postId = 'postId' 
	 	 */
	  return queryFactory 
			  .delete(noticeBoardAttachedFile)
			  .where(noticeBoardAttachedFile.postIdx.eq(postIdx)) 
			  .execute(); 
	  }
}