
package kr.ac.univ.lab.member.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import kr.ac.univ.lab.member.domian.Member;
import kr.ac.univ.lab.member.dto.MemberDto;
import kr.ac.univ.lab.member.mapper.MemberMapper;
import kr.ac.univ.lab.member.service.MemberAttachedFileService;
import kr.ac.univ.lab.member.service.MemberService;

@RestController
@RequestMapping("/api/members")
public class MemberRestController {
	private final MemberService memberService;
	private final MemberAttachedFileService memberAttachedFileService;
	
	public MemberRestController(MemberService memberService, MemberAttachedFileService memberAttachedFileService) {
        this.memberService = memberService;
        this.memberAttachedFileService = memberAttachedFileService;
    }

	@PostMapping
	public ResponseEntity<?> postMember(@RequestBody MemberDto memberDto) {
		Long idx = memberService.insertMember(MemberMapper.INSTANCE.toEntity(memberDto));
		
		return new ResponseEntity<>(idx, HttpStatus.CREATED);
	}

	@PutMapping("/{idx}")
	public ResponseEntity<?> putMember(@PathVariable("idx") Long idx, @RequestBody MemberDto memberDto) {
		Member persistMember = memberService.getMemberByIdx(idx);
		persistMember.update(MemberMapper.INSTANCE.toEntity(memberDto));
		
		memberService.insertMember(persistMember);

		return new ResponseEntity<>("{}", HttpStatus.OK);
	}

	@DeleteMapping("/{idx}")
	public ResponseEntity<?> deleteMember(@PathVariable("idx") Long idx) {
		memberService.deleteMemberByIdx(idx);
		memberAttachedFileService.deleteAttachedFile(idx);

		return new ResponseEntity<>("{}", HttpStatus.OK);
	}
	
	@GetMapping("/validation/memberId/{memberId}")
	public ResponseEntity<?> checkDuplicateMemberId(@PathVariable("memberId") String memberId) {

		boolean isDuplicateMemberId = memberService.findDupulicateMemberById(memberId);
		
		return new ResponseEntity<>(isDuplicateMemberId, HttpStatus.CREATED);
	}
	
	// 첨부 파일 업로드
	@PostMapping("/attachedFile")
	public ResponseEntity<?> uploadAttachedFile(Long idx, MultipartFile[] files) {
		memberAttachedFileService.uploadAttachedFile(idx, files);

		return new ResponseEntity<>("성공!", HttpStatus.CREATED);
	}
	
	// 첨부 파일 삭제
	@DeleteMapping("/attachedFile/{idx}")
	public ResponseEntity<?> deleteAttachedFile(@PathVariable("idx") Long idx) {
		memberAttachedFileService.deleteAttachedFile(idx);
		
		return new ResponseEntity<>("성공!", HttpStatus.OK);
	}
}