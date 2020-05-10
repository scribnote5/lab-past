package kr.ac.univ.lab.noticeBoard.controller;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import kr.ac.univ.lab.noticeBoard.domain.NoticeBoard;
import kr.ac.univ.lab.noticeBoard.service.NoticeBoardAttachedFileService;
import kr.ac.univ.lab.noticeBoard.service.NoticeBoardService;


@RestController
@RequestMapping("/api/notice-boards")
public class NoticeBoardRestController {
	private final NoticeBoardService noticeBoardService;
	private final NoticeBoardAttachedFileService noticeBoardAttachedFileService;
	
	public NoticeBoardRestController(NoticeBoardService noticeBoardService, NoticeBoardAttachedFileService noticeBoardAttachedFileService) {
        this.noticeBoardService = noticeBoardService;
        this.noticeBoardAttachedFileService = noticeBoardAttachedFileService;
    }

	@PostMapping
	public ResponseEntity<?> postNoticeBoard(@RequestBody NoticeBoard noticeBoard) {
		Long idx = noticeBoardService.insertNoticeBoard(noticeBoard);

		return new ResponseEntity<>(idx, HttpStatus.CREATED);
	}

	@PutMapping("/{idx}")
	public ResponseEntity<?> putNoticeBoard(@PathVariable("idx") Long idx, @RequestBody NoticeBoard noticeBoard) {
		NoticeBoard persistNoticeBoard = noticeBoardService.getNoticeBoardByIdx(idx);
		persistNoticeBoard.update(noticeBoard);
		noticeBoardService.insertNoticeBoard(persistNoticeBoard);

		return new ResponseEntity<>("{}", HttpStatus.OK);
	}
	
	@DeleteMapping("/{idx}")
	public ResponseEntity<?> deleteNoticeBoard(@PathVariable("idx") Long idx) {
		noticeBoardService.deleteNoticeBoardByIdx(idx);
		noticeBoardAttachedFileService.deleteAttachedFile(idx);

		return new ResponseEntity<>("{}", HttpStatus.OK);
	}

	// 첨부 파일 업로드
	@PostMapping("/attachedFile")
	public ResponseEntity<?> uploadAttachedFile(Long idx, MultipartFile[] files) {
		noticeBoardAttachedFileService.uploadAttachedFile(idx, files);

		return new ResponseEntity<>("성공!", HttpStatus.CREATED);
	}

	// 첨부 파일 삭제
	@DeleteMapping("/attachedFile")
	public ResponseEntity<?> deleteAttachedFile(@RequestBody List<Long> deleteFileList) {
		noticeBoardAttachedFileService.deleteAttachedFile(deleteFileList);
		
		return new ResponseEntity<>("성공!", HttpStatus.OK);
	}
}