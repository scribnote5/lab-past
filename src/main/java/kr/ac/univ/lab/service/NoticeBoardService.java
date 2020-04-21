package kr.ac.univ.lab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import kr.ac.univ.lab.domain.NoticeBoard;
import kr.ac.univ.lab.repository.NoticeBoardRepository;
import kr.ac.univ.lab.repository.NoticeBoardRepositoryImpl;

@Service
public class NoticeBoardService {
	@Autowired
    private NoticeBoardRepository noticeBoardRepository;

	@Autowired
    private NoticeBoardRepositoryImpl noticeBoardRepositoryImpl;
	
    public Page<NoticeBoard> findNoticeBoardList(Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1, pageable.getPageSize(), Sort.Direction.DESC, "createdDate");
        return noticeBoardRepository.findAll(pageable);
    }

    public NoticeBoard findNoticeBoardByIdx(Long idx) {
    	noticeBoardRepositoryImpl.updateViewCountByIdx(idx); 
        return noticeBoardRepository.findById(idx).orElse(new NoticeBoard());
    }    
    
    public void insertNoticeBoard(NoticeBoard noticeBoard) {
    	noticeBoardRepository.save(noticeBoard);
    }
    
    public NoticeBoard getNoticeBoardByIdx(Long idx) {
    	return noticeBoardRepository.getOne(idx) ;
    }

    public void deleteNoticeBoardById(Long idx) {
    	noticeBoardRepository.deleteById(idx) ;
    }
    
}