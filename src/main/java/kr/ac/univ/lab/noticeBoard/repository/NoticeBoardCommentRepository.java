package kr.ac.univ.lab.noticeBoard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.ac.univ.lab.noticeBoard.domain.NoticeBoardComment;

@Repository
public interface NoticeBoardCommentRepository extends JpaRepository<NoticeBoardComment, Long> {
	
	List<NoticeBoardComment> findAllByNoticeBoardIdxOrderByCreatedDateDesc(Long noticeBoardIdx);
	
	void deleteAllByNoticeBoardIdx(Long noticeBoardIdx);
}