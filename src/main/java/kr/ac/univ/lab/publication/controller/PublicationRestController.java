package kr.ac.univ.lab.publication.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import kr.ac.univ.lab.common.dto.SearchDto;
import kr.ac.univ.lab.publication.domain.Publication;
import kr.ac.univ.lab.publication.domain.enums.KindType;
import kr.ac.univ.lab.publication.domain.enums.PublicationType;
import kr.ac.univ.lab.publication.service.PublicationAttachedFileService;
import kr.ac.univ.lab.publication.service.PublicationService;

@RestController
@RequestMapping("/api/publications")
public class PublicationRestController {
	private final PublicationService publicationService;
	private final PublicationAttachedFileService publicationAttachedFileService;

	public PublicationRestController(PublicationService publicationService,	PublicationAttachedFileService publicationAttachedFileService) {
		this.publicationService = publicationService;
		this.publicationAttachedFileService = publicationAttachedFileService;
	}

	@PostMapping
	public ResponseEntity<?> postPublication(@RequestBody Publication publication) {
		System.out.println(publication);
		Long idx = publicationService.insertPublication(publication);

		return new ResponseEntity<>(idx, HttpStatus.CREATED);
	}

	@PutMapping("/{idx}")
	public ResponseEntity<?> putPublication(@PathVariable("idx") Long idx, @RequestBody Publication publication) {
		Publication persistPublication = publicationService.getPublicationByIdx(idx);
		persistPublication.update(publication);
		publicationService.insertPublication(persistPublication);

		return new ResponseEntity<>("{}", HttpStatus.OK);
	}
	
	@DeleteMapping("/{idx}")
	public ResponseEntity<?> deletePublication(@PathVariable("idx") Long idx) {
		publicationService.deletePublicationByIdx(idx);
		publicationAttachedFileService.deleteAttachedFile(idx);
		
		return new ResponseEntity<>("{}", HttpStatus.OK);
	}

	// 첨부 파일 업로드
	@PostMapping("/attachedFile")
	public ResponseEntity<?> uploadAttachedFile(Long idx, MultipartFile[] files) {
		publicationAttachedFileService.uploadAttachedFile(idx, files);

		return new ResponseEntity<>("성공!", HttpStatus.CREATED);
	}

	// 첨부 파일 삭제
	@DeleteMapping("/attachedFile")
	public ResponseEntity<?> deleteAttachedFile(@RequestBody List<Long> deleteFileList) {
		publicationAttachedFileService.deleteAttachedFile(deleteFileList);
		
		return new ResponseEntity<>("성공!", HttpStatus.OK);
	}
	
	// Home List
	@GetMapping("/home/list")
	public ResponseEntity<?> publicationHomeList(@RequestParam(value = "lastIdx", defaultValue = "-1") Long lastIdx, 
			@RequestParam(value = "kindType", required = false) KindType kindType, 
			@RequestParam(value = "publicationType", required = false) PublicationType publicationType,
			SearchDto searchDto,
			Model model) {
		
		System.out.println("fist lastIdx: " + lastIdx);
		
		if(lastIdx == -1L) {
			lastIdx = publicationService.findMaxPublicationIdx();
		}
		
		System.out.println("lastIdx: " + lastIdx);
		System.out.println("searchDto: " + searchDto);
		System.out.println("kindType: " + kindType +", publicationType: " + publicationType);
		System.out.println(publicationService.findPublicationListAtHome(lastIdx, kindType, publicationType, searchDto));
		
		return new ResponseEntity<>(publicationService.findPublicationListAtHome(lastIdx, kindType, publicationType, searchDto), HttpStatus.OK);
	}
	
}
