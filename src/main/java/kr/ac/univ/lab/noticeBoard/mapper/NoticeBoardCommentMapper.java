package kr.ac.univ.lab.noticeBoard.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import kr.ac.univ.lab.common.mapper.EntityMapper;
import kr.ac.univ.lab.noticeBoard.domain.NoticeBoardComment;
import kr.ac.univ.lab.noticeBoard.dto.NoticeBoardCommentDto;

@Mapper(componentModel = "spring")
public interface NoticeBoardCommentMapper extends EntityMapper<NoticeBoardCommentDto, NoticeBoardComment> {
	NoticeBoardCommentMapper INSTANCE = Mappers.getMapper(NoticeBoardCommentMapper.class);
}