package kr.ac.univ.lab.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import kr.ac.univ.lab.domain.NoticeBoard;
import kr.ac.univ.lab.domain.QNoticeBoard;;

@Repository
@Transactional
public class NoticeBoardRepositoryImpl extends QuerydslRepositorySupport {
	private final JPAQueryFactory queryFactory;

	public NoticeBoardRepositoryImpl(JPAQueryFactory queryFactory ) {
		super(NoticeBoard.class);
		this.queryFactory = queryFactory;
	}
	
	public List<NoticeBoard> findByTitle(String title) {
		QNoticeBoard noticeBoard = QNoticeBoard.noticeBoard;
		/*
		 * SELECT *
		 *   FROM notice_board
		 *  WHERE title = 'title'
		 */
		return queryFactory
				.selectFrom(noticeBoard)
				.where(noticeBoard.title.eq(title))
				.fetch();
	}
	
	public long updateViewCountById(Long id) {
		QNoticeBoard noticeBoard = QNoticeBoard.noticeBoard;
		/*
		 * UPDATE notice_board
		 *    SET view_count = view_count + 1 
		 *  WHERE id = 'id';
		 */
		return queryFactory
				.update(noticeBoard)
				.set(noticeBoard.viewCount, noticeBoard.viewCount.add(1))
				.where(noticeBoard.id.eq(id))
				.execute();
	}

}