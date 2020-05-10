
package kr.ac.univ.lab.member.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import kr.ac.univ.lab.common.dto.SearchDto;
import kr.ac.univ.lab.member.domian.Member;
import kr.ac.univ.lab.member.dto.MemberDto;
import kr.ac.univ.lab.member.mapper.MemberMapper;
import kr.ac.univ.lab.member.repository.MemberRepository;

@Service
public class MemberService {
	private final MemberRepository memberRepository;
	
	public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;   
     }
	
	public Page<MemberDto> findMemberList(Pageable pageable, SearchDto searchDto) {
		Page<Member> memberList = null;
		Page<MemberDto> memberDtoList = null;
				
		pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1, pageable.getPageSize(), Sort.Direction.DESC, "createdDate");
		
		switch(searchDto.getSearchType()) {
		case MEMBER_ID:
			memberList = memberRepository.findAllByMemberIdContaining(pageable, searchDto.getKeyword());
			break;
		case KOREAN_NAME:
			memberList = memberRepository.findAllByKoreanNameContaining(pageable, searchDto.getKeyword());
			break;
		case EMAIL:
			memberList = memberRepository.findAllByEmailContaining(pageable, searchDto.getKeyword());
			break;
		default:
			memberList = memberRepository.findAll(pageable);
			break;
		}
		
		// Member -> MemberDto 
		memberDtoList = new PageImpl<MemberDto>(MemberMapper.INSTANCE.toDto(memberList.getContent()), pageable, memberList.getTotalElements());
		
		return memberDtoList;
	}
	
	public Long insertMember(Member member) {
		return memberRepository.save(member).getIdx();
	}
	
	public Member findMemberByIdx(Long idx) {
		return memberRepository.findById(idx).orElse(new Member());
	}

	public Member getMemberByIdx(Long idx) {
		return memberRepository.getOne(idx);
	}

	public void deleteMemberByIdx(Long idx) {
		memberRepository.deleteById(idx);
	}	

	public boolean findDupulicateMemberById(String memberId) {
		System.out.println("memberRepositoryImpl.findCountByMemberId(memberId): " + memberRepository.countByMemberId(memberId));
		
		boolean isDuplicateMemberId = (memberRepository.countByMemberId(memberId) > 0) ? true : false ;
		
		return isDuplicateMemberId;
	}
}