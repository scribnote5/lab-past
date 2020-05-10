package kr.ac.univ.lab.noticeBoard.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.ac.univ.lab.noticeBoard.domain.NoticeBoard;

@Repository
public interface NoticeBoardRepository extends JpaRepository<NoticeBoard, Long> {
	Page<NoticeBoard> findAllByTitleContaining(Pageable pageable, String title);

	Page<NoticeBoard> findAllByContentContaining(Pageable pageable, String content);

	Page<NoticeBoard> findAllByCreatedByContaining(Pageable pageable, String memberId);
}