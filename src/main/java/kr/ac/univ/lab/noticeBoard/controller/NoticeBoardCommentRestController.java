package kr.ac.univ.lab.noticeBoard.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.univ.lab.noticeBoard.domain.NoticeBoardComment;
import kr.ac.univ.lab.noticeBoard.service.NoticeBoardCommentService;


@RestController
@RequestMapping("/api/notice-boards-comments")
public class NoticeBoardCommentRestController {
	private final NoticeBoardCommentService noticeBoardCommentService;
	
	public NoticeBoardCommentRestController(NoticeBoardCommentService noticeBoardCommentService) {
        this.noticeBoardCommentService = noticeBoardCommentService;
    }

	@PostMapping
	public ResponseEntity<?> postNoticeBoard(@RequestBody NoticeBoardComment noticeBoardComment) {
		Long idx = noticeBoardCommentService.insertNoticeBoardComment(noticeBoardComment);

		return new ResponseEntity<>(idx, HttpStatus.CREATED);
	}

	@PutMapping("/{idx}")
	public ResponseEntity<?> putNoticeBoard(@PathVariable("idx") Long idx, @RequestBody NoticeBoardComment noticeBoardComment) {
		NoticeBoardComment persistNoticeBoardComment = noticeBoardCommentService.getNoticeBoardCommentByIdx(idx);
		persistNoticeBoardComment.update(noticeBoardComment);
		noticeBoardCommentService.insertNoticeBoardComment(persistNoticeBoardComment);

		return new ResponseEntity<>("{}", HttpStatus.OK);
	}
	
	@DeleteMapping("/{idx}")
	public ResponseEntity<?> deleteNoticeBoard(@PathVariable("idx") Long idx) {
		noticeBoardCommentService.deleteNoticeBoardCommentByIdx(idx);

		return new ResponseEntity<>("{}", HttpStatus.OK);
	}
}