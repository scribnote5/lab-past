package kr.ac.univ.lab.noticeBoard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.ac.univ.lab.noticeBoard.domain.NoticeBoardAttachedFile;

@Repository
public interface NoticeBoardAttachedFileRepository extends JpaRepository<NoticeBoardAttachedFile, Long> {

}