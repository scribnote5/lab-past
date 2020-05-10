package kr.ac.univ.lab.member.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.ac.univ.lab.common.dto.SearchDto;
import kr.ac.univ.lab.member.dto.MemberDto;
import kr.ac.univ.lab.member.mapper.MemberMapper;
import kr.ac.univ.lab.member.service.MemberAttachedFileService;
import kr.ac.univ.lab.member.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {
	private final MemberService memberService;
	private final MemberAttachedFileService memberAttachedFileService;

	public MemberController(MemberService memberService, MemberAttachedFileService memberAttachedFileService) {
		this.memberService = memberService;
		this.memberAttachedFileService = memberAttachedFileService;
	}

	// List
	@GetMapping("/list")
	public String noticeBoardList(@PageableDefault Pageable pageable, SearchDto searchDto, Model model) {
		model.addAttribute("memberDtoList", memberService.findMemberList(pageable, searchDto));

		return "/member/list";
	}

	// Form Update
	@GetMapping("/form{idx}")
	public String loginForm(@RequestParam(value = "idx", defaultValue = "0") Long idx, Model model) {
		MemberDto memberDto;
		memberDto = MemberMapper.INSTANCE.toDto(memberService.findMemberByIdx(idx));
		memberDto = MemberMapper.INSTANCE.toDto(memberDto, memberAttachedFileService.findAttachedFileByMemberIdx(idx));

		model.addAttribute("memberDto", memberDto);

		return "/member/form";
	}

	// Read
	@GetMapping({ "", "/" })
	public String noticeBoardRead(@RequestParam(value = "idx", defaultValue = "0") Long idx, Model model) {
		MemberDto memberDto;
		memberDto = MemberMapper.INSTANCE.toDto(memberService.findMemberByIdx(idx));
		memberDto = MemberMapper.INSTANCE.toDto(memberDto, memberAttachedFileService.findAttachedFileByMemberIdx(idx));

		model.addAttribute("memberDto", memberDto);

		return "/member/read";
	}
}