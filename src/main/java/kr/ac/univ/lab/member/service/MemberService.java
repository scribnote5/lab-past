
package kr.ac.univ.lab.member.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.ac.univ.lab.common.dto.SearchDto;
import kr.ac.univ.lab.member.domian.Member;
import kr.ac.univ.lab.member.domian.enums.AuthorityType;
import kr.ac.univ.lab.member.dto.MemberDto;
import kr.ac.univ.lab.member.dto.UserDto;
import kr.ac.univ.lab.member.mapper.MemberMapper;
import kr.ac.univ.lab.member.repository.MemberRepository;

@Service
public class MemberService implements UserDetailsService {
	private final MemberRepository memberRepository;
	
	public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;   
     }
	
	public Page<MemberDto> findMemberList(Pageable pageable, SearchDto searchDto) {
		Page<Member> memberList = null;
		Page<MemberDto> memberDtoList = null;
				
		pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1, pageable.getPageSize(), Sort.Direction.DESC, "idx");
		
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
	
	@Transactional
    public Long joinUser(MemberDto memberDto) {
        // 비밀번호 암호화
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        memberDto.setPassword(passwordEncoder.encode(memberDto.getPassword()));
 
       return memberRepository.save(MemberMapper.INSTANCE.toEntity(memberDto)).getIdx();
    }
	
	@Override
	public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {		
		Member member = memberRepository.findByMemberId(memberId);
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		boolean enabled = true; 
		boolean accountNonExpired = true; 
		boolean credentialsNonExpired = true; 
		boolean accountNonLocked = true;

		switch (member.getAuthorityType()) {
		case ROOT:
			authorities.add(new SimpleGrantedAuthority(AuthorityType.ROOT.getAuthorityType()));
			break;
		case MANAGER:
			authorities.add(new SimpleGrantedAuthority(AuthorityType.MANAGER.getAuthorityType()));
			break;
		case GENERAL:
			authorities.add(new SimpleGrantedAuthority(AuthorityType.GENERAL.getAuthorityType()));
			break;
		case NON_MEMBER:
			authorities.add(new SimpleGrantedAuthority(AuthorityType.NON_MEMBER.getAuthorityType()));
			break;
		default:
			break;
		}
		
		return new UserDto(member.getMemberId(), 
						   member.getPassword(), 
						   enabled, 
						   accountNonExpired, 
						   credentialsNonExpired, 
						   accountNonLocked, 
						   authorities, 
						   member.getIdx(), 
						   member.getKoreanName(), 
						   member.getEnglishName());
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

	public boolean isDupulicationMemberById(String memberId) {		
		return (memberRepository.countByMemberId(memberId) > 0) ? true : false;
	}
	
	public Member findByMemberId(String memberId) {
		return memberRepository.findByMemberId(memberId);
	}
}