package kr.ac.univ.lab;

import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import kr.ac.univ.lab.noticeBoard.repository.NoticeBoardRepository;
import kr.ac.univ.lab.noticeBoard.repository.NoticeBoardRepositoryImpl;

@SpringBootTest
@EnableAutoConfiguration
@ExtendWith(SpringExtension.class)
public class TimeTest {
	private static final Logger logger = LoggerFactory.getLogger(LabApplication.class);
	
	@Autowired
	NoticeBoardRepository noticeBoardRepository;

	@Autowired
	NoticeBoardRepositoryImpl noticeBoardRepositoryImpl;

	@Test
	@DisplayName("Time 테스트")
	public void Test() {
		long days = 2;
		
		LocalDateTime currentTime = LocalDateTime.now();
		LocalDateTime pastDateTime = LocalDateTime.of(2020, 5, 2, 0, 0, 0, 0);

		Assert.assertEquals(pastDateTime.isAfter(currentTime.minusDays(days)), true);
	}
}