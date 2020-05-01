
package kr.ac.univ.lab.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import kr.ac.univ.lab.domain.NoticeBoard;
import kr.ac.univ.lab.dto.SearchDto;
import kr.ac.univ.lab.repository.NoticeBoardRepository;
import kr.ac.univ.lab.repository.NoticeBoardRepositoryImpl;

@Service
public class NoticeBoardService {
	private final NoticeBoardRepository noticeBoardRepository;
	private final NoticeBoardRepositoryImpl noticeBoardRepositoryImpl;
	
	public NoticeBoardService(NoticeBoardRepository noticeBoardRepository, NoticeBoardRepositoryImpl noticeBoardRepositoryImpl) {
        this.noticeBoardRepository = noticeBoardRepository;
        this.noticeBoardRepositoryImpl = noticeBoardRepositoryImpl;
    }

	public Page<NoticeBoard> findNoticeBoardList(Pageable pageable, SearchDto searchDto) {
		Page<NoticeBoard> noticeBoardList = null;
		
		pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1, pageable.getPageSize(), Sort.Direction.DESC, "createdDate");
		
		switch(searchDto.getSearchType()) {
		case TITLE:
			noticeBoardList = noticeBoardRepository.findAllByTitleContaining(pageable, searchDto.getKeyword());
			break;
		case CONTENT:
			noticeBoardList = noticeBoardRepository.findAllByContentContaining(pageable, searchDto.getKeyword());
			break;
		case MEMBER_ID:
			noticeBoardList = noticeBoardRepository.findAllByMemberIdContaining(pageable, searchDto.getKeyword());
			break;
		default:
			noticeBoardList = noticeBoardRepository.findAll(pageable);
			break;
		}
		
//		for(NoticeBoard noticeBoard : noticeBoardList) {
//			System.out.println("noticeBoard.getTitle(): " + noticeBoard.getTitle());
//			System.out.println("noticeBoard.getContent(): " + noticeBoard.getContent());
//		}
		
		return noticeBoardList;
	}

	public NoticeBoard findNoticeBoardById(Long id) {
		noticeBoardRepositoryImpl.updateViewCountById(id);
		
		return noticeBoardRepository.findById(id).orElse(new NoticeBoard());
	}

	public Long insertNoticeBoard(NoticeBoard noticeBoard) {
		return noticeBoardRepository.save(noticeBoard).getId();
	}

	public NoticeBoard getNoticeBoardById(Long id) {
		return noticeBoardRepository.getOne(id);
	}

	public void deleteNoticeBoardById(Long id) {
		noticeBoardRepository.deleteById(id);
	}
	
}