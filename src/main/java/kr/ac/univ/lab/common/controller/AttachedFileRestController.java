
package kr.ac.univ.lab.common.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/attachedFiles")
public class AttachedFileRestController {

	@GetMapping("/download/{savedFileName}")
	public ResponseEntity<?> downloadAttachedFile(@PathVariable("savedFileName") String savedFileName) {
		// 파일 이름이 한글인 경우 인코디잉 깨지지 않도록 변경 
		String encordedSavedFileName = null;
		
		try {
			encordedSavedFileName = URLEncoder.encode(savedFileName,"UTF-8").replace("+", "%20");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		
		// 헤더 추가
		HttpHeaders header = new HttpHeaders();

        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + encordedSavedFileName.substring(33));
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");
        
        // 파일 다운로드
        Path path = Paths.get("./upload/" + savedFileName);

        ByteArrayResource resource = null;
        
		try {
			resource = new ByteArrayResource(Files.readAllBytes(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
       
        return ResponseEntity.ok()
            .headers(header)
            .contentType(MediaType.parseMediaType("application/octet-stream"))
            .body(resource);
	}
}