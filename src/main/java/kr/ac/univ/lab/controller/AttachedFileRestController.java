
package kr.ac.univ.lab.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import kr.ac.univ.lab.service.AttachedFileService;

@RestController
@RequestMapping("/api/AttachedFile")
public class AttachedFileRestController {
	@Autowired
	private AttachedFileService attachedFileService;

	@PostMapping("/Upload")
	public ResponseEntity<?> uploadAttachedFile(String id, MultipartFile[] files) {
		Long boardId = Long.parseLong(id);

		attachedFileService.uploadAttachedFile(boardId, files);

		return new ResponseEntity<>("성공!", HttpStatus.CREATED);
	}

	@PostMapping("/Download/{savedFileName}")
	public ResponseEntity<?> downloadAttachedFile(@PathVariable("savedFileName") String savedFileName) {
		HttpHeaders header = new HttpHeaders();
		
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; savedFileName="+savedFileName);
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");
        
        ByteArrayResource resource = attachedFileService.downloadAttachedFile(savedFileName);
       
        return ResponseEntity.ok()
            .headers(header)
            .contentType(MediaType.parseMediaType("application/octet-stream"))
            .body(resource);
	}

	@PostMapping("/Delete/{Id}/{savedFileName}")
	public ResponseEntity<?> deleteAttachedFile(@PathVariable("Id") String Id, @PathVariable("savedFileName") String savedFileName) throws IOException {
		Long fileId = Long.parseLong(Id);
		
		attachedFileService.deleteUploadFileById(fileId);
		attachedFileService.deleteAttachedFile(savedFileName);
		
		return new ResponseEntity<>("성공!", HttpStatus.OK);
	}

}