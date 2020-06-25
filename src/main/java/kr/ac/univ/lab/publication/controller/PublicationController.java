package kr.ac.univ.lab.publication.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.ac.univ.lab.common.dto.SearchDto;
import kr.ac.univ.lab.publication.domain.enums.KindType;
import kr.ac.univ.lab.publication.domain.enums.PublicationType;
import kr.ac.univ.lab.publication.dto.PublicationDto;
import kr.ac.univ.lab.publication.mapper.PublicationMapper;
import kr.ac.univ.lab.publication.service.PublicationAttachedFileService;
import kr.ac.univ.lab.publication.service.PublicationService;

@Controller
@RequestMapping("/publication")
public class PublicationController {
	private final PublicationService publicationService;
	private final PublicationAttachedFileService publicationAttachedFileService;

	public PublicationController(PublicationService publicationService, PublicationAttachedFileService publicationAttachedFileService) {
		this.publicationService = publicationService;
		this.publicationAttachedFileService = publicationAttachedFileService;
	}

	// List
	@GetMapping("/list")
	public String publicationList(@PageableDefault Pageable pageable, 
			SearchDto searchDto, 
			@RequestParam(value = "kindType", required = false) KindType kindType, 
			@RequestParam(value = "publicationType", required = false) PublicationType publicationType, 
			Model model) {
		
		System.out.println("searchDto: " + searchDto);
		System.out.println("kindType: " + kindType +", publicationType: " + publicationType);
		model.addAttribute("publicationDtoList", publicationService.findPublicationList(pageable, kindType, publicationType, searchDto));
		
		return "/publication/list";
	}

	// Form Update
	@GetMapping("/form{idx}")
	public String publicationForm(@RequestParam(value = "idx", defaultValue = "0") Long idx, Model model) {
		PublicationDto publicationDto;		
		
		publicationDto = PublicationMapper.INSTANCE.toDto(publicationService.findPublicationByIdx(idx));
		publicationDto = PublicationMapper.INSTANCE.toDto(publicationDto, publicationAttachedFileService.findAttachedFileByPublicationIdx(idx));
		
		model.addAttribute("publicationDto", publicationDto);

		return "/publication/form";
	}

	// Read
	@GetMapping({ "", "/" })
	public String publicationRead(@RequestParam(value = "idx", defaultValue = "0") Long idx, Model model) {
		PublicationDto publicationDto;
		
		publicationDto = PublicationMapper.INSTANCE.toDto(publicationService.findPublicationByIdx(idx));
		publicationDto = PublicationMapper.INSTANCE.toDto(publicationDto, publicationAttachedFileService.findAttachedFileByPublicationIdx(idx));
		
		model.addAttribute("publicationDto", publicationDto);
		
		return "/publication/read";
	}
	
	// Home List
	@GetMapping("/home/list")
	public String publicationHomeList(@RequestParam(value = "idx", defaultValue = "0") Long lastIdx, 
			@RequestParam(value = "kindType", required = false) KindType kindType, 
			@RequestParam(value = "publicationType", required = false) PublicationType publicationType,
			SearchDto searchDto,
			Model model) {
		
		lastIdx = publicationService.findMaxPublicationIdx();
		
		System.out.println("lastIdx: " + lastIdx);
		System.out.println("searchDto: " + searchDto);
		System.out.println("kindType: " + kindType +", publicationType: " + publicationType);
		System.out.println(publicationService.findPublicationListAtHome(lastIdx, kindType, publicationType, searchDto));
		model.addAttribute("publicationDtoList", publicationService.findPublicationListAtHome(lastIdx, kindType, publicationType, searchDto));
		
		return "/home/publicationList";
	}
}