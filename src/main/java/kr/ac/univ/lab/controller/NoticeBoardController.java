
package kr.ac.univ.lab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.ac.univ.lab.service.NoticeBoardService;
import kr.ac.univ.lab.service.AttachedFileService;

@Controller
@RequestMapping("/NoticeBoard")
public class NoticeBoardController {
	@Autowired
	private NoticeBoardService noticeBoardService;

	@Autowired
	private AttachedFileService attachedFileService;

	// Read
	@GetMapping({ "", "/" })
	public String noticeBoardRead(@RequestParam(value = "id", defaultValue = "0") Long id, Model model) {
		model.addAttribute("noticeBoard", noticeBoardService.findNoticeBoardById(id));
		model.addAttribute("attachedFileList", attachedFileService.findUploadFileByBoardId(id));

		return "/noticeBoard/read";
	}

	// Form Update
	@GetMapping("/form{id}")
	public String noticeBoardForm(@RequestParam(value = "id", defaultValue = "0") Long id, Model model) {
		model.addAttribute("noticeBoard", noticeBoardService.findNoticeBoardById(id));
		model.addAttribute("attachedFileList", attachedFileService.findUploadFileByBoardId(id));

		return "/noticeBoard/form";
	}

	// List
	@GetMapping("/list")
	public String noticeBoardList(@PageableDefault Pageable pageable, Model model) {
		model.addAttribute("noticeBoardList", noticeBoardService.findNoticeBoardList(pageable));

		return "/noticeBoard/list";
	}
}
