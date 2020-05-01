package kr.ac.univ.lab.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import kr.ac.univ.lab.domain.AttachedFile;
import kr.ac.univ.lab.domain.NoticeBoard;
import kr.ac.univ.lab.dto.NoticeBoardDto;

@Mapper(componentModel = "spring")
public interface NoticeBoardMapper extends EntityMapper<NoticeBoardDto, NoticeBoard> {
	NoticeBoardMapper INSTANCE = Mappers.getMapper(NoticeBoardMapper.class);

	default NoticeBoardDto toDto(NoticeBoardDto noticeBoardDto, List<AttachedFile> attachedFileList) {
//		System.out.println("attachedFileList.size(): " + attachedFileList.size());
//		System.out.println(attachedFileList);

		for (AttachedFile attachedFile : attachedFileList) {
			noticeBoardDto.getAttachedFileList().add(attachedFile);	
		}

		return noticeBoardDto;
	}

}