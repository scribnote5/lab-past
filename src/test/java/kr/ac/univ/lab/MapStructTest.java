package kr.ac.univ.lab;

import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import kr.ac.univ.lab.noticeBoard.domain.NoticeBoard;
import kr.ac.univ.lab.noticeBoard.dto.NoticeBoardDto;
import kr.ac.univ.lab.noticeBoard.mapper.NoticeBoardMapper;

@SpringBootTest
@EnableAutoConfiguration
@ExtendWith(SpringExtension.class)
public class MapStructTest {
	private static final Logger logger = LoggerFactory.getLogger(LabApplication.class);
	
	@Test
	@DisplayName("Mapstrcut 테스트")
	public void Test() {
	
		NoticeBoardDto noticeBoardDto = new NoticeBoardDto();
		noticeBoardDto.setTitle("제목입니다");
		noticeBoardDto.setContent("글입니다");
		
		NoticeBoard noticeBoard = NoticeBoardMapper.INSTANCE.toEntity(noticeBoardDto); // DTO -> Entity 

		Assert.assertEquals(noticeBoardDto.getTitle(), noticeBoard.getTitle());
		Assert.assertEquals(noticeBoardDto.getContent(), noticeBoard.getContent());
	}
}