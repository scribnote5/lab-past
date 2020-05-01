package kr.ac.univ.lab;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import kr.ac.univ.lab.domain.NoticeBoard;
import kr.ac.univ.lab.domain.enums.PostStatus;
import kr.ac.univ.lab.repository.NoticeBoardRepository;
import kr.ac.univ.lab.repository.NoticeBoardRepositoryImpl;

@SpringBootTest
@EnableAutoConfiguration
@ExtendWith(SpringExtension.class)
public class QuerydslTest {
	@Autowired
	NoticeBoardRepository noticeBoardRepository;

	@Autowired
	NoticeBoardRepositoryImpl noticeBoardRepositoryImpl;

	@BeforeEach
	public void init() {
		IntStream.rangeClosed(1, 200)
			.forEach(index -> noticeBoardRepository.save(NoticeBoard.builder()
				.title("게시글" + index)
				.content("컨텐츠")
				.viewCount(0L)
				.postStatus(PostStatus.ACTIVE)
				.build()));
	}

	
	@Test
	@DisplayName("Querydsl로 구현한 findByTitle 테스트")  
	public void Test() {
		List<NoticeBoard> list = noticeBoardRepositoryImpl.findByTitle("게시글1");

		for (NoticeBoard noticeboard : list) {
			assertEquals(noticeboard.getTitle(), "게시글1");
		}
	}
	
	@Test
	@DisplayName("Querydsl로 구현한 updateViewCountByIdx 테스트")  
	public void Test2() {
		// update된 column 개수 반환
		Long cnt = noticeBoardRepositoryImpl.updateViewCountById(1L);
		assertEquals(cnt, 1);
	}
	
	@Test
	@DisplayName("JPA findById를 사용한 viewCount 테스트")  
	public void Test3() {
		Optional<NoticeBoard> optionalNoticeBoard = noticeBoardRepository.findById(1L);
		NoticeBoard noticeBoard = null;
		
		if (optionalNoticeBoard .isPresent()) {
		    noticeBoard = optionalNoticeBoard.get();
		}
		
		// idx 1인 column을 조회한다.
		assertEquals(noticeBoard.getId(), 1L);
		assertEquals(noticeBoard.getViewCount(), 1L);
	}
}