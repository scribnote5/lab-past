package kr.ac.univ.lab.publication.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import kr.ac.univ.lab.common.mapper.EntityMapper;
import kr.ac.univ.lab.publication.domain.Publication;
import kr.ac.univ.lab.publication.domain.PublicationAttachedFile;
import kr.ac.univ.lab.publication.dto.PublicationDto;

@Mapper(componentModel = "spring")
public interface PublicationMapper extends EntityMapper<PublicationDto, Publication> {
	PublicationMapper INSTANCE = Mappers.getMapper(PublicationMapper.class);
	
	default PublicationDto toDto(PublicationDto publicationDto, List<PublicationAttachedFile> attachedFileList) {
		for (PublicationAttachedFile attachedFile : attachedFileList) {
			publicationDto.getAttachedFileList().add(attachedFile);	
		}

		return publicationDto;
	}
}