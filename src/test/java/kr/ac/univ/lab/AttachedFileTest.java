package kr.ac.univ.lab;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Before;
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
public class AttachedFileTest {
	private static final Logger logger = LoggerFactory.getLogger(LabApplication.class);
	
	@Autowired
	NoticeBoardRepository noticeBoardRepository;

	@Autowired
	NoticeBoardRepositoryImpl noticeBoardRepositoryImpl;

	String uuid;
	String savedFileName;
	
	@Before
	public void init() {
		uuid = UUID.randomUUID().toString().replaceAll("-", "");
		savedFileName = uuid + "_" + "test.txt";
	}

	@Test
	@DisplayName("파일 업로드 테스트")
	public void Test() {
		// "./upload/test.txt" 경로에 test.txt 파일을 미리 생성한다.
		Path path = Paths.get("./upload/test.txt");
		Path savedFilePath = Paths.get("./upload/" + savedFileName);
		
		// 파일에 "Test" 문자열 입력
		try (FileChannel channel = FileChannel.open(path, StandardOpenOption.WRITE,
				StandardOpenOption.TRUNCATE_EXISTING)) {
			ByteBuffer byteBuffer = Charset.defaultCharset().encode("Test");
			channel.write(byteBuffer);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 파일을 복사한다. 
		try {
			Files.write(savedFilePath, Files.readAllBytes(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Assert.assertEquals(Files.exists(savedFilePath), true);
	}

	@Test
	@DisplayName("파일 삭제 테스트")
	public void Test2() {
		Path savedFilePath = Paths.get("./upload/" + savedFileName);

		// 파일을 삭제한다.
		try {
			Files.delete(savedFilePath);
		} catch (IOException e) {
			e.printStackTrace();
		}

		Assert.assertEquals(Files.exists(savedFilePath), false);
	}
}