package kr.ac.univ.lab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.PagedModel.PageMetadata;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.univ.lab.domain.NoticeBoard;
import kr.ac.univ.lab.service.NoticeBoardService;

@RestController
@RequestMapping("/api/NoticeBoards")
public class NoticeBoardRestController {
	@Autowired
	private NoticeBoardService noticeBoardSerive;

    @PostMapping
    public ResponseEntity<?> postNoticeBoard(@RequestBody NoticeBoard noticeBoard) {
        //valid 체크
    	noticeBoard.setCreatedDateNow();
        noticeBoardSerive.insertNoticeBoard(noticeBoard);
        
        return new ResponseEntity<>("{}", HttpStatus.CREATED);
    }

    @PutMapping("/{idx}")
    public ResponseEntity<?> putNoticeBoard(@PathVariable("idx")Long idx, @RequestBody NoticeBoard noticeBoard) {
        //valid 체크
        NoticeBoard persistNoticeBoard = noticeBoardSerive.getNoticeBoardByIdx(idx) ;
        persistNoticeBoard.update(noticeBoard);
        noticeBoardSerive.insertNoticeBoard(persistNoticeBoard);
        
        return new ResponseEntity<>("{}", HttpStatus.OK);
    }

    @DeleteMapping("/{idx}")
    public ResponseEntity<?> deleteNoticeBoard(@PathVariable("idx")Long idx) {
        //valid 체크
    	noticeBoardSerive.deleteNoticeBoardById(idx);
    	
        return new ResponseEntity<>("{}", HttpStatus.OK);
    }
}