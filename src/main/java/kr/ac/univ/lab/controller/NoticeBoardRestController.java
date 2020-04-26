
package kr.ac.univ.lab.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.univ.lab.domain.NoticeBoard;
import kr.ac.univ.lab.domain.AttachedFile;
import kr.ac.univ.lab.service.NoticeBoardService;
import kr.ac.univ.lab.service.AttachedFileService;

@RestController
@RequestMapping("/api/NoticeBoards")
public class NoticeBoardRestController {
	@Autowired
	private NoticeBoardService noticeBoardSerive;

	@Autowired
	private AttachedFileService attachedFileService;

	@PostMapping
	public ResponseEntity<?> postNoticeBoard(@RequestBody NoticeBoard noticeBoard) {
		noticeBoard.setCreatedDateNow();
		Long id = noticeBoardSerive.insertNoticeBoard(noticeBoard);

		return new ResponseEntity<>(id, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> putNoticeBoard(@PathVariable("id") Long id, @RequestBody NoticeBoard noticeBoard) {
		NoticeBoard persistNoticeBoard = noticeBoardSerive.getNoticeBoardById(id);
		persistNoticeBoard.update(noticeBoard);
		noticeBoardSerive.insertNoticeBoard(persistNoticeBoard);

		return new ResponseEntity<>("{}", HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteNoticeBoard(@PathVariable("id") Long id) {
		noticeBoardSerive.deleteNoticeBoardById(id);

		List<AttachedFile> attachedFileList = attachedFileService.findUploadFileByBoardId(id);

		for (AttachedFile attachedFile : attachedFileList) {
			Path path = Paths.get("./upload/" + attachedFile.getSavedFileName());

			try {
				Files.delete(path);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		attachedFileService.deleteUploadFileByBoardId(id);

		return new ResponseEntity<>("{}", HttpStatus.OK);
	}
}