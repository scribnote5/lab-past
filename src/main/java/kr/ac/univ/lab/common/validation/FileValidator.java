package kr.ac.univ.lab.common.validation;

import java.io.IOException;

import org.apache.tika.Tika;
import org.springframework.web.multipart.MultipartFile;

import kr.ac.univ.lab.common.util.FileUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileValidator {
	public static String isFileValid(MultipartFile[] files) throws IOException {
		Tika tika = new Tika();
		String result = "valid";

		for (MultipartFile file : files) {
			String mimeType = tika.detect(file.getBytes());
			String extension = FileUtil.getExtension(file.getOriginalFilename());

			log.info(mimeType + ", " + extension);
			
			if (FileType.invalidMimTypeSet.contains(mimeType) || FileType.invalidExtensionSet.contains(extension)) {
				result = "[mimeType: " + mimeType + ", extension: " + extension + "] doesn't supprot to upload because it supposed to dangerous and malicious.";
				break;
			}
		}
		
		return result;
	}
	
	public static String isImageValid(MultipartFile[] files) throws IOException {
		Tika tika = new Tika();
		String result = "valid";

		for (MultipartFile file : files) {
			String mimeType = tika.detect(file.getBytes());
			String extension = FileUtil.getExtension(file.getOriginalFilename());

			log.info(mimeType + ", " + extension);
			
			if (!FileType.validImageTypeSet.contains(mimeType) ) {
				result = "[mimeType: " + mimeType + ", extension: " + extension + "] doesn't supprot to upload because it supposed to dangerous and malicious.";
				break;
			}
		}
		
		return result;
	}
	
	
}