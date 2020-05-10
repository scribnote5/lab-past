package kr.ac.univ.lab.member.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import kr.ac.univ.lab.member.domian.MemberAttachedFile;
import kr.ac.univ.lab.member.domian.QMemberAttachedFile;;

@Repository
@Transactional
public class MemberAttachedFileRepositoryImpl extends QuerydslRepositorySupport {
	private final JPAQueryFactory queryFactory;

	public MemberAttachedFileRepositoryImpl(JPAQueryFactory queryFactory) {
		super(MemberAttachedFile.class);
		this.queryFactory = queryFactory;
	}

	public List<MemberAttachedFile> findAttachedFileByMemberId(Long memberIdx) {
		QMemberAttachedFile memberAttachedFile = QMemberAttachedFile.memberAttachedFile;
	  
		/* SELECT * 
		 *   FROM AttachedFile 
		 *  WHERE memberId = 'memberId' 
		 */
	  	return queryFactory 
	  			.selectFrom(memberAttachedFile)
	  			.where(memberAttachedFile.memberIdx.eq(memberIdx)) 
	  			.fetch(); 
	  }

	public Long deleteAttachedFileByMemberId(Long memberIdx) { 
		QMemberAttachedFile memberAttachedFile = QMemberAttachedFile.memberAttachedFile;
	  
	 	/* DELETE FROM AttachedFile  
	 	 *  WHERE memberId = 'memberId' 
	 	 */
	  return queryFactory 
			  .delete(memberAttachedFile)
			  .where(memberAttachedFile.memberIdx.eq(memberIdx)) 
			  .execute(); 
	  }
}