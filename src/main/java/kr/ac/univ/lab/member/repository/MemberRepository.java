package kr.ac.univ.lab.member.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.ac.univ.lab.member.domian.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
	Page<Member> findAllByMemberIdContaining(Pageable pageable, String memberId);

	Page<Member> findAllByKoreanNameContaining(Pageable pageable, String koreanName);

	Page<Member> findAllByEmailContaining(Pageable pageable, String email);
	
	Long countByMemberId(String memberId);
	
	Member findByMemberId(String memberId);
}