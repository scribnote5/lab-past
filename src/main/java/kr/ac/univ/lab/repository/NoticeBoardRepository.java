package kr.ac.univ.lab.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.ac.univ.lab.domain.NoticeBoard;

@Repository
public interface NoticeBoardRepository extends JpaRepository<NoticeBoard, Long> {
	Page<NoticeBoard> findAllByTitleContaining(Pageable pageable, String title);

	Page<NoticeBoard> findAllByContentContaining(Pageable pageable, String content);

	Page<NoticeBoard> findAllByMemberIdContaining(Pageable pageable, String memberId);
}