package kr.ac.univ.lab.noticeBoard.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import kr.ac.univ.lab.common.dto.SearchDto;
import kr.ac.univ.lab.common.util.NewIconCheck;
import kr.ac.univ.lab.noticeBoard.domain.NoticeBoard;
import kr.ac.univ.lab.noticeBoard.dto.NoticeBoardDto;
import kr.ac.univ.lab.noticeBoard.mapper.NoticeBoardMapper;
import kr.ac.univ.lab.noticeBoard.repository.NoticeBoardRepository;
import kr.ac.univ.lab.noticeBoard.repository.NoticeBoardRepositoryImpl;

@Service
public class NoticeBoardService {
	private final NoticeBoardRepository noticeBoardRepository;
	private final NoticeBoardRepositoryImpl noticeBoardRepositoryImpl;
	
	public NoticeBoardService(NoticeBoardRepository noticeBoardRepository, NoticeBoardRepositoryImpl noticeBoardRepositoryImpl) {
        this.noticeBoardRepository = noticeBoardRepository;
        this.noticeBoardRepositoryImpl = noticeBoardRepositoryImpl;
    }

	public Page<NoticeBoardDto> findNoticeBoardList(Pageable pageable, SearchDto searchDto) {
		Page<NoticeBoard> noticeBoardList = null;
		Page<NoticeBoardDto> noticeBoardDtoList = null;

		pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1, pageable.getPageSize(), Sort.Direction.DESC, "idx");
		
		switch(searchDto.getSearchType()) {
		case TITLE:
			noticeBoardList = noticeBoardRepository.findAllByTitleContaining(pageable, searchDto.getKeyword());
			break;
		case CONTENT:
			noticeBoardList = noticeBoardRepository.findAllByContentContaining(pageable, searchDto.getKeyword());
			break;
		case MEMBER_ID:
			noticeBoardList = noticeBoardRepository.findAllByCreatedByContaining(pageable, searchDto.getKeyword());
			break;
		default:
			noticeBoardList = noticeBoardRepository.findAll(pageable);
			break;
		}
		
		// NoticeBoard -> NoticeBoardDto 
		noticeBoardDtoList = new PageImpl<NoticeBoardDto>(NoticeBoardMapper.INSTANCE.toDto(noticeBoardList.getContent()), pageable, noticeBoardList.getTotalElements());
		
		// newIcon 판별
		for(NoticeBoardDto noticeBoardDto : noticeBoardDtoList) {	
			noticeBoardDto.setNewIcon(NewIconCheck.isNew(noticeBoardDto.getCreatedDate()));
		}
		
		return noticeBoardDtoList;
	}
	
	public Long insertNoticeBoard(NoticeBoard noticeBoard) {
		return noticeBoardRepository.save(noticeBoard).getIdx();
	}

	public NoticeBoard findNoticeBoardByIdx(Long idx) {
		noticeBoardRepositoryImpl.updateViewCountById(idx);
		
		return noticeBoardRepository.findById(idx).orElse(new NoticeBoard());
	}
	
	public NoticeBoard getNoticeBoardByIdx(Long idx) {
		return noticeBoardRepository.getOne(idx);
	}

	public void deleteNoticeBoardByIdx(Long idx) {
		noticeBoardRepository.deleteById(idx);
	}
}