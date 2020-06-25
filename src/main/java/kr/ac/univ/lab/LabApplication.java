package kr.ac.univ.lab;

import java.time.LocalDate;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.univ.lab.common.domain.enums.ActiveStatus;
import kr.ac.univ.lab.member.domian.Member;
import kr.ac.univ.lab.member.domian.enums.AuthorityType;
import kr.ac.univ.lab.member.domian.enums.MemberType;
import kr.ac.univ.lab.member.repository.MemberRepository;
import kr.ac.univ.lab.noticeBoard.domain.NoticeBoard;
import kr.ac.univ.lab.noticeBoard.repository.NoticeBoardRepository;
import kr.ac.univ.lab.publication.domain.Publication;
import kr.ac.univ.lab.publication.domain.enums.KindType;
import kr.ac.univ.lab.publication.domain.enums.PublicationType;
import kr.ac.univ.lab.publication.repository.PublicationRepository;

@RestController
@SpringBootApplication
public class LabApplication {
	private static final Logger logger = LoggerFactory.getLogger(LabApplication.class);
	 
	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	 
	public static void main(String[] args) {
		SpringApplication.run(LabApplication.class, args);
	}

	@GetMapping("/")
	public String Home() {
		logger.trace("Hello world");
	    logger.debug("Hello world");
	    logger.info("Hello world");
	    logger.warn("Hello world");
	    logger.error("Hello world");
		
		return "Hello World";
	}

	@Bean
	public CommandLineRunner runner(NoticeBoardRepository noticeBoardRepository, MemberRepository memberRepository, PublicationRepository publicationRepositroy) {		
		return (args) -> {
			/* 기존 데이터 모두 삭제 */ 
			noticeBoardRepository.deleteAll();
			
			IntStream.rangeClosed(1, 200).forEach(index -> 
				noticeBoardRepository.save(NoticeBoard.builder()
					.title("게시글" + index)
					.content("컨텐츠")
					.viewCount(0L)
					.activeStatus(ActiveStatus.ACTIVE)
					.build()));
			
			/* 기존 데이터 모두 삭제 */ 
			memberRepository.deleteAll();
			
			memberRepository.save(Member.builder()
					.memberId("root")
					.password(passwordEncoder.encode("123123123"))
					.memberType(MemberType.PART_TIME_MS)
					.authorityType(AuthorityType.ROOT)
					.activeStatus(ActiveStatus.ACTIVE)
					.build());
			
			memberRepository.save(Member.builder()
					.memberId("manager")
					.password(passwordEncoder.encode("123123123"))
					.memberType(MemberType.PART_TIME_MS)
					.authorityType(AuthorityType.MANAGER)
					.activeStatus(ActiveStatus.ACTIVE)
					.build());
			
			memberRepository.save(Member.builder()
					.memberId("manager2")
					.password(passwordEncoder.encode("123123123"))
					.memberType(MemberType.PART_TIME_MS)
					.authorityType(AuthorityType.MANAGER)
					.activeStatus(ActiveStatus.ACTIVE)
					.build());

			memberRepository.save(Member.builder()
				.memberId("sdy")
				.password(passwordEncoder.encode("123123123"))
				.memberType(MemberType.PART_TIME_MS)
				.authorityType(AuthorityType.GENERAL)
				.activeStatus(ActiveStatus.ACTIVE)
				.build());
			
			memberRepository.save(Member.builder()
				.memberId("jbk")
				.password(passwordEncoder.encode("123123123"))
				.memberType(MemberType.PART_TIME_MS)
				.authorityType(AuthorityType.GENERAL)
				.activeStatus(ActiveStatus.ACTIVE)
				.build());
			
			/* 기존 데이터 모두 삭제 */ 
			publicationRepositroy.deleteAll();
			
			IntStream.rangeClosed(1, 50).forEach(index -> 
				publicationRepositroy.save(Publication.builder()
					.title("게시글" + index)
					.authors("저자" + index)
					.publishedIn("KSC 2019")
					.publishedDate(LocalDate.now())
					.publicationType(PublicationType.JOURNAL)
					.kindType(KindType.DOMESTIC)
					.volume("" + index)
					.number("" + index)
					.build()));
			
			IntStream.rangeClosed(1, 50).forEach(index -> 
				publicationRepositroy.save(Publication.builder()
					.title("게시글" + index)
					.authors("저자" + index)
					.publishedIn("KSC 2018")
					.publishedDate(LocalDate.now())
					.publicationType(PublicationType.POSTER)
					.kindType(KindType.DOMESTIC)
					.volume("" + index)
					.number("" + index)
					.build()));
			
			IntStream.rangeClosed(1, 50).forEach(index -> 
				publicationRepositroy.save(Publication.builder()
					.title("게시글" + index)
					.authors("저자" + index)
					.publishedIn("RTAS 2017")
					.publishedDate(LocalDate.now())
					.publicationType(PublicationType.JOURNAL)
					.kindType(KindType.INTERNATIONAL)
					.volume("" + index)
					.number("" + index)
					.build()));
			
			IntStream.rangeClosed(1, 50).forEach(index -> 
				publicationRepositroy.save(Publication.builder()
					.title("게시글" + index)
					.authors("저자" + index)
					.publishedIn("ICNGC 2016")
					.publishedDate(LocalDate.now())
					.publicationType(PublicationType.POSTER)
					.kindType(KindType.INTERNATIONAL)
					.volume("" + index)
					.number("" + index)
				.build()));
		};
	}
}