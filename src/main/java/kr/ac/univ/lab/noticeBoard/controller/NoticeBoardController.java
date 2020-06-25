package kr.ac.univ.lab.noticeBoard.controller;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.ac.univ.lab.common.dto.SearchDto;
import kr.ac.univ.lab.common.error.exception.UserAccessException;
import kr.ac.univ.lab.common.util.AccessCheck;
import kr.ac.univ.lab.common.util.CommonUtil;
import kr.ac.univ.lab.member.service.MemberService;
import kr.ac.univ.lab.noticeBoard.dto.NoticeBoardCommentDto;
import kr.ac.univ.lab.noticeBoard.dto.NoticeBoardDto;
import kr.ac.univ.lab.noticeBoard.mapper.NoticeBoardMapper;
import kr.ac.univ.lab.noticeBoard.service.NoticeBoardAttachedFileService;
import kr.ac.univ.lab.noticeBoard.service.NoticeBoardCommentService;
import kr.ac.univ.lab.noticeBoard.service.NoticeBoardService;

@Controller
@RequestMapping("/notice-board")
public class NoticeBoardController {
	private final NoticeBoardService noticeBoardService;
	private final NoticeBoardAttachedFileService noticeBoardAttachedFileService;
	private final NoticeBoardCommentService noticeBoardCommentService;

	public NoticeBoardController(NoticeBoardService noticeBoardService,	NoticeBoardAttachedFileService noticeBoardAttachedFileService, NoticeBoardCommentService noticeBoardCommentService) {
		this.noticeBoardService = noticeBoardService;
		this.noticeBoardAttachedFileService = noticeBoardAttachedFileService;
		this.noticeBoardCommentService = noticeBoardCommentService;
	}

	// List
	@GetMapping("/list")
	public String noticeBoardList(@PageableDefault Pageable pageable, SearchDto searchDto, Model model) {
		model.addAttribute("noticeBoardDtoList", noticeBoardService.findNoticeBoardList(pageable, searchDto));

		return "/noticeBoard/list";
	}

	// Form Update
	@GetMapping("/form{idx}")
	public String noticeBoardForm(@RequestParam(value = "idx", defaultValue = "0") Long idx, Model model) {
		NoticeBoardDto noticeBoardDto;
		noticeBoardDto = NoticeBoardMapper.INSTANCE.toDto(noticeBoardService.findNoticeBoardByIdx(idx));
		noticeBoardDto.setAccess(AccessCheck.isAccess(noticeBoardDto.getCreatedBy())); // 접근 권한 여부 확인
		
		if(!CommonUtil.isEmpty(noticeBoardDto.getIdx()) && !noticeBoardDto.isAccess()) {
			throw new UserAccessException();
		}
		
		noticeBoardDto = NoticeBoardMapper.INSTANCE.toDto(noticeBoardDto, noticeBoardAttachedFileService.findAttachedFileByNoticeBoardIdx(idx));
		
		model.addAttribute("noticeBoardDto", noticeBoardDto);

		return "/noticeBoard/form";
	}

	// Read
	@GetMapping({ "", "/" })
	public String noticeBoardRead(@RequestParam(value = "idx", defaultValue = "0") Long idx, Model model) {
		NoticeBoardDto noticeBoardDto;
		List<NoticeBoardCommentDto> noticeBoardCommentDtoList;
		
		noticeBoardDto = NoticeBoardMapper.INSTANCE.toDto(noticeBoardService.findNoticeBoardByIdx(idx));
		noticeBoardDto.setAccess(AccessCheck.isAccess(noticeBoardDto.getCreatedBy())); 	// 접근 권한 여부 확인
		noticeBoardDto.setAnonymousUser(AccessCheck.isAnonymousUser()); // 익명 사용자 여부 확인
		noticeBoardDto = NoticeBoardMapper.INSTANCE.toDto(noticeBoardDto, noticeBoardAttachedFileService.findAttachedFileByNoticeBoardIdx(idx));
		
		noticeBoardCommentDtoList = noticeBoardCommentService.findAllByNoticeBoardIdxOrderByCreatedDateDesc(idx);
		
		model.addAttribute("noticeBoardDto", noticeBoardDto);
		model.addAttribute("noticeBoardCommentDtoList", noticeBoardCommentDtoList);
		
		return "/noticeBoard/read";
	}
}