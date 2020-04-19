package kr.ac.univ.lab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.ac.univ.lab.service.NoticeBoardService;

@Controller
@RequestMapping("/NoticeBoard")
public class NoticeBoardController {
	@Autowired
	private NoticeBoardService noticeBoardService;

	// Read
	@GetMapping({ "", "/" })
	public String noticeBoardRead(@RequestParam(value = "idx", defaultValue = "0") Long idx, Model model) {
		model.addAttribute("noticeBoard", noticeBoardService.findNoticeBoardByIdx(idx));
		
		return "/noticeBoard/read";
	}

	// Form Update
	@GetMapping("/form{idx}")
	public String noticeBoardForm(@RequestParam(value = "idx", defaultValue = "0") Long idx, Model model) {
		model.addAttribute("noticeBoard", noticeBoardService.findNoticeBoardByIdx(idx));
		
		return "/noticeBoard/form";
	}
	
	// List
	@GetMapping("/list")
	public String noticeBoardList(@PageableDefault Pageable pageable, Model model) {
		model.addAttribute("noticeBoardList", noticeBoardService.findNoticeBoardList(pageable));

		return "/noticeBoard/list";
	}
}