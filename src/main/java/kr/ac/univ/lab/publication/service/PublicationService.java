package kr.ac.univ.lab.publication.service;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import kr.ac.univ.lab.common.dto.SearchDto;
import kr.ac.univ.lab.publication.domain.Publication;
import kr.ac.univ.lab.publication.domain.enums.KindType;
import kr.ac.univ.lab.publication.domain.enums.PublicationType;
import kr.ac.univ.lab.publication.dto.PublicationDto;
import kr.ac.univ.lab.publication.mapper.PublicationMapper;
import kr.ac.univ.lab.publication.repository.PublicationRepository;
import kr.ac.univ.lab.publication.repository.PublicationRepositoryImpl;

@Service
public class PublicationService {
	private final PublicationRepository publicationRepository;
	private final PublicationRepositoryImpl publicationRepositoryImpl;
	
	public PublicationService(PublicationRepository publicationRepository, PublicationRepositoryImpl publicationRepositoryImpl) {
        this.publicationRepository = publicationRepository;
        this.publicationRepositoryImpl = publicationRepositoryImpl;
    }

	public Page<PublicationDto> findPublicationList(Pageable pageable, KindType kindType, PublicationType publicationType, SearchDto searchDto) {
		Page<Publication> publicationList = null;
		Page<PublicationDto> publicationDtoList = null;

		pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1, pageable.getPageSize(), Sort.Direction.DESC, "idx");
		
		switch(searchDto.getSearchType()) {
		case TITLE:
			publicationList = publicationRepository.findAllByTitleContaining(pageable, searchDto.getKeyword());
			break;
		case AUTHORS:
			publicationList = publicationRepository.findAllByAuthorsContaining(pageable, searchDto.getKeyword());
			break;
		case PUBLISHED_IN:
			publicationList = publicationRepository.findAllByPublishedInContaining(pageable, searchDto.getKeyword());
			break;
		default:
			publicationList = publicationRepository.findAll(pageable);
			break;
		}
		System.out.println(publicationList.getContent());
		
		// Publication -> PublicationDto 
		publicationDtoList = new PageImpl<PublicationDto>(PublicationMapper.INSTANCE.toDto(publicationList.getContent()), pageable, publicationList.getTotalElements());
		
		return publicationDtoList;
	}
	
	public Long insertPublication(Publication publication) {
		return publicationRepository.save(publication).getIdx();
	}
	
	public Publication findPublicationByIdx(Long idx) {
		return publicationRepository.findById(idx).orElse(new Publication());
	}
	
	public Publication getPublicationByIdx(Long idx) {
		return publicationRepository.getOne(idx);
	}

	public void deletePublicationByIdx(Long idx) {
		publicationRepository.deleteById(idx);
	}
	
	public List<PublicationDto> findPublicationListAtHome(Long lastIdx, KindType kindType, PublicationType publicationType, SearchDto searchDto) {
		List<Publication> publicationList = null;
		List<PublicationDto> publicationDtoList = null;

		switch(searchDto.getSearchType()) {
		case TITLE:
			publicationList = publicationRepositoryImpl.findTop10ByTitleContaining(lastIdx, searchDto.getKeyword());
			break;
		case AUTHORS:
			publicationList = publicationRepositoryImpl.findTop10ByAuthorsContaining(lastIdx, searchDto.getKeyword());
			break;
		case PUBLISHED_IN:
			publicationList = publicationRepositoryImpl.findTop10ByPublishedInContaining(lastIdx, searchDto.getKeyword());
			break;
		default:
			if(KindType.INTERNATIONAL == kindType && PublicationType.JOURNAL == publicationType) {
				publicationList = publicationRepositoryImpl.findTop10ByInternationalAndJournal(lastIdx);
			} 
			else if (KindType.INTERNATIONAL == kindType && PublicationType.JOURNAL != publicationType){
				publicationList = publicationRepositoryImpl.findTop10ByInternationalAndConference(lastIdx);
			}
			else if (KindType.DOMESTIC == kindType && PublicationType.JOURNAL == publicationType){
				publicationList = publicationRepositoryImpl.findTop10ByDomesticAndJournal(lastIdx);
			}
			else if (KindType.DOMESTIC == kindType && PublicationType.JOURNAL != publicationType){
				publicationList = publicationRepositoryImpl.findTop10ByDomesticAndConference(lastIdx);
			} 
			else {
				publicationList = publicationRepositoryImpl.findTop10(lastIdx);
			}
			
			break;
		}
		
		// Publication -> PublicationDto 
		publicationDtoList = PublicationMapper.INSTANCE.toDto(publicationList);
		
		return publicationDtoList;
	}
	
	public Long findMaxPublicationIdx() {
		return publicationRepositoryImpl.findMaxPublicationIdx().getIdx();
	}
}