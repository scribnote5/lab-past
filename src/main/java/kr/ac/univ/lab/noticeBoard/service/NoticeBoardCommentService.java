package kr.ac.univ.lab.noticeBoard.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.ac.univ.lab.common.util.CommentAccess;
import kr.ac.univ.lab.common.util.NewIconCheck;
import kr.ac.univ.lab.noticeBoard.domain.NoticeBoardComment;
import kr.ac.univ.lab.noticeBoard.dto.NoticeBoardCommentDto;
import kr.ac.univ.lab.noticeBoard.mapper.NoticeBoardCommentMapper;
import kr.ac.univ.lab.noticeBoard.repository.NoticeBoardCommentRepository;
import kr.ac.univ.lab.noticeBoard.repository.NoticeBoardCommentRepositoryImpl;

@Service
public class NoticeBoardCommentService {
	private final NoticeBoardCommentRepository noticeBoardCommentRepository;
	private final NoticeBoardCommentRepositoryImpl noticeBoardCommentRepositoryImpl;
	
	public NoticeBoardCommentService(NoticeBoardCommentRepository noticeBoardCommentRepository, NoticeBoardCommentRepositoryImpl noticeBoardCommentRepositoryImpl) {
        this.noticeBoardCommentRepository = noticeBoardCommentRepository;	
        this.noticeBoardCommentRepositoryImpl = noticeBoardCommentRepositoryImpl;
    }

	public List<NoticeBoardCommentDto> findAllByNoticeBoardIdxOrderByCreatedDateDesc(Long noticeBoardIdx) {
		List<NoticeBoardComment> noticeBoardCommentList = null;
		List<NoticeBoardCommentDto> noticeBoardCommentDtoList = null;

		noticeBoardCommentList = noticeBoardCommentRepository.findAllByNoticeBoardIdxOrderByCreatedDateDesc(noticeBoardIdx);
		
		// NoticeBoard -> NoticeBoardDto 
		noticeBoardCommentDtoList = NoticeBoardCommentMapper.INSTANCE.toDto(noticeBoardCommentList);
		
		// newIcon 판별
		for(NoticeBoardCommentDto noticeBoardCommentDto : noticeBoardCommentDtoList) {	
			noticeBoardCommentDto.setNewIcon(NewIconCheck.isNew(noticeBoardCommentDto.getCreatedDate()));
			
			if(CommentAccess.isAccess(noticeBoardCommentDto.getCreatedBy())) {
				noticeBoardCommentDto.setAccess(true);
			} else {
				noticeBoardCommentDto.setAccess(false);
			}
		}
		
		return noticeBoardCommentDtoList;
	}
	
	public Long insertNoticeBoardComment(NoticeBoardComment noticeBoardComment) {
		return noticeBoardCommentRepository.save(noticeBoardComment).getIdx();
	}
	
	public NoticeBoardComment getNoticeBoardCommentByIdx(Long idx) {
		return noticeBoardCommentRepository.getOne(idx);
	}

	public void deleteNoticeBoardCommentByIdx(Long idx) {
		noticeBoardCommentRepository.deleteById(idx);
	}
	
	public void deleteAllByNoticeBoardIdx(Long idx) {
		noticeBoardCommentRepositoryImpl.deleteAllByNoticeBoardIdx(idx);
	}

}