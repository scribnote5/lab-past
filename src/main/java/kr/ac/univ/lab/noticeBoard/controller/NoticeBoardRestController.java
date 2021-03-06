package kr.ac.univ.lab.noticeBoard.controller;

import java.util.List;

import javax.validation.Valid;

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

import kr.ac.univ.lab.common.error.exception.FileTypeException;
import kr.ac.univ.lab.common.validation.FileValidator;
import kr.ac.univ.lab.noticeBoard.domain.NoticeBoard;
import kr.ac.univ.lab.noticeBoard.dto.NoticeBoardDto;
import kr.ac.univ.lab.noticeBoard.mapper.NoticeBoardMapper;
import kr.ac.univ.lab.noticeBoard.service.NoticeBoardAttachedFileService;
import kr.ac.univ.lab.noticeBoard.service.NoticeBoardCommentService;
import kr.ac.univ.lab.noticeBoard.service.NoticeBoardService;

@RestController
@RequestMapping("/api/notice-boards")
public class NoticeBoardRestController {
	private final NoticeBoardService noticeBoardService;
	private final NoticeBoardAttachedFileService noticeBoardAttachedFileService;
	private final NoticeBoardCommentService noticeBoardCommentService;

	public NoticeBoardRestController(NoticeBoardService noticeBoardService,	NoticeBoardAttachedFileService noticeBoardAttachedFileService, NoticeBoardCommentService noticeBoardCommentService) {
		this.noticeBoardService = noticeBoardService;
		this.noticeBoardAttachedFileService = noticeBoardAttachedFileService;
		this.noticeBoardCommentService = noticeBoardCommentService;
	}

	@PostMapping
	public ResponseEntity<?> postNoticeBoard(@RequestBody @Valid NoticeBoardDto noticeBoardDto) {
		Long idx = noticeBoardService.insertNoticeBoard(NoticeBoardMapper.INSTANCE.toEntity(noticeBoardDto));

		return new ResponseEntity<>(idx, HttpStatus.CREATED);
	}

	@PutMapping("/{idx}")
	public ResponseEntity<?> putNoticeBoard(@PathVariable("idx") Long idx, @RequestBody @Valid NoticeBoardDto noticeBoardDto) {
		NoticeBoard persistNoticeBoard = noticeBoardService.getNoticeBoardByIdx(idx);
		persistNoticeBoard.update(NoticeBoardMapper.INSTANCE.toEntity(noticeBoardDto));
		noticeBoardService.insertNoticeBoard(persistNoticeBoard);

		return new ResponseEntity<>("{}", HttpStatus.OK);
	}
	
	@DeleteMapping("/{idx}")
	public ResponseEntity<?> deleteNoticeBoard(@PathVariable("idx") Long idx) {
		noticeBoardService.deleteNoticeBoardByIdx(idx);
		noticeBoardAttachedFileService.deleteAttachedFile(idx);
		noticeBoardCommentService.deleteAllByNoticeBoardIdx(idx);
		
		return new ResponseEntity<>("{}", HttpStatus.OK);
	}

	// 첨부 파일 업로드
	@PostMapping("/attachedFile")
	public ResponseEntity<?> uploadAttachedFile(Long idx, MultipartFile[] files) throws Exception {
		// File type validation
		String fileValidationResult = FileValidator.isFileValid(files);
		
		if(!"valid".equals(fileValidationResult)) 
			throw new FileTypeException(fileValidationResult);
		
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