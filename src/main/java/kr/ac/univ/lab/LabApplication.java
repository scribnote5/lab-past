package kr.ac.univ.lab;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.univ.lab.domain.NoticeBoard;
import kr.ac.univ.lab.domain.enums.PostStatus;
import kr.ac.univ.lab.repository.NoticeBoardRepository;

@RestController
@SpringBootApplication
public class LabApplication {
	private static final Logger logger = LoggerFactory.getLogger(LabApplication.class);
	
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
	public CommandLineRunner runner(NoticeBoardRepository noticeBoardRepository) {
		return (args) -> {
			IntStream.rangeClosed(1, 200).forEach(index -> 
				noticeBoardRepository.save(NoticeBoard.builder()
					.title("게시글" + index)
					.content("컨텐츠")
					.viewCount(0L)
					.postStatus(PostStatus.active)
					.createdDate(LocalDateTime.now())
					.updatedDate(LocalDateTime.now()).build()));
		};
	}
}