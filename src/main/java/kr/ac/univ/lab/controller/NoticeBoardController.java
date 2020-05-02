
package kr.ac.univ.lab.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.ac.univ.lab.dto.NoticeBoardDto;
import kr.ac.univ.lab.dto.SearchDto;
import kr.ac.univ.lab.mapper.NoticeBoardMapper;
import kr.ac.univ.lab.service.AttachedFileService;
import kr.ac.univ.lab.service.NoticeBoardService;

@Controller
@RequestMapping("/NoticeBoard")
public class NoticeBoardController {
	private final NoticeBoardService noticeBoardService;
	private final AttachedFileService attachedFileService;
	
	public NoticeBoardController(NoticeBoardService noticeBoardService, AttachedFileService attachedFileService) {
        this.noticeBoardService = noticeBoardService;
        this.attachedFileService = attachedFileService;
    }

	// Read
	@GetMapping({ "", "/" })
	public String noticeBoardRead(@RequestParam(value = "id", defaultValue = "0") Long id, Model model) {
//		model.addAttribute("noticeBoard", noticeBoardService.findNoticeBoardById(id));
//		model.addAttribute("attachedFileList", attachedFileService.findUploadFileByBoardId(id));
		
		NoticeBoardDto noticeBoardDto;
		noticeBoardDto =  NoticeBoardMapper.INSTANCE.toDto(noticeBoardService.findNoticeBoardById(id));
		noticeBoardDto = NoticeBoardMapper.INSTANCE.toDto(noticeBoardDto, attachedFileService.findUploadFileByBoardId(id));
		model.addAttribute("noticeBoardDto", noticeBoardDto);

		return "/noticeBoard/read";
	}

	// Form Update
	@GetMapping("/form{id}")
	public String noticeBoardForm(@RequestParam(value = "id", defaultValue = "0") Long id, Model model) {
//		model.addAttribute("noticeBoard", noticeBoardService.findNoticeBoardById(id));
//		model.addAttribute("attachedFileList", attachedFileService.findUploadFileByBoardId(id));
		
		NoticeBoardDto noticeBoardDto;
		noticeBoardDto =  NoticeBoardMapper.INSTANCE.toDto(noticeBoardService.findNoticeBoardById(id));
		noticeBoardDto = NoticeBoardMapper.INSTANCE.toDto(noticeBoardDto, attachedFileService.findUploadFileByBoardId(id));
		model.addAttribute("noticeBoardDto", noticeBoardDto);
		
		return "/noticeBoard/form";
	}

	// List
	@GetMapping("/list")
	public String noticeBoardList(@PageableDefault Pageable pageable, SearchDto searchDto, Model model) {
		model.addAttribute("noticeBoardDtoList", noticeBoardService.findNoticeBoardList(pageable, searchDto));
				
		return "/noticeBoard/list";
	}
}
