package kr.ac.univ.lab.noticeBoard.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import kr.ac.univ.lab.member.domian.MemberAttachedFile;
import kr.ac.univ.lab.noticeBoard.domain.QNoticeBoardComment;;

@Repository
@Transactional
public class NoticeBoardCommentRepositoryImpl extends QuerydslRepositorySupport {
	private final JPAQueryFactory queryFactory;

	public NoticeBoardCommentRepositoryImpl(JPAQueryFactory queryFactory) {
		super(MemberAttachedFile.class);
		this.queryFactory = queryFactory;
	}

	public Long deleteAllByNoticeBoardIdx(Long noticeBoardIdx) { 
		QNoticeBoardComment noticeBoardComment = QNoticeBoardComment.noticeBoardComment;
		  
	 	/* DELETE FROM AttachedFile  
	 	 *  WHERE noticeBoardIdx = 'noticeBoardIdx' 
	 	 */
	  return queryFactory 
			  .delete(noticeBoardComment)
			  .where(noticeBoardComment.noticeBoardIdx.eq(noticeBoardIdx)) 
			  .execute(); 
	  }
}