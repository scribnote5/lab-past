package kr.ac.univ.lab;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LabApplicationTests {
	private static final Logger logger = LoggerFactory.getLogger(LabApplicationTests.class);
	
	@Test
	void contextLoads() {
		
		String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
		logger.info("I'm starting");
		logger.error(time);
	}

}
