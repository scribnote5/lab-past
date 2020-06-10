package kr.ac.univ.lab.noticeBoard.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import kr.ac.univ.lab.common.mapper.EntityMapper;
import kr.ac.univ.lab.noticeBoard.domain.NoticeBoard;
import kr.ac.univ.lab.noticeBoard.domain.NoticeBoardAttachedFile;
import kr.ac.univ.lab.noticeBoard.dto.NoticeBoardDto;

@Mapper(componentModel = "spring")
public interface NoticeBoardMapper extends EntityMapper<NoticeBoardDto, NoticeBoard> {
	NoticeBoardMapper INSTANCE = Mappers.getMapper(NoticeBoardMapper.class);

	default NoticeBoardDto toDto(NoticeBoardDto noticeBoardDto, List<NoticeBoardAttachedFile> attachedFileList) {
		for (NoticeBoardAttachedFile attachedFile : attachedFileList) {
			noticeBoardDto.getAttachedFileList().add(attachedFile);	
		}

		return noticeBoardDto;
	}
}