package kr.ac.univ.lab.member.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import kr.ac.univ.lab.common.mapper.EntityMapper;
import kr.ac.univ.lab.member.domian.Member;
import kr.ac.univ.lab.member.domian.MemberAttachedFile;
import kr.ac.univ.lab.member.dto.MemberDto;

@Mapper(componentModel = "spring")
public interface MemberMapper extends EntityMapper<MemberDto, Member> {
	MemberMapper INSTANCE = Mappers.getMapper(MemberMapper.class);
	
	default MemberDto toDto(MemberDto memberDto, List<MemberAttachedFile> attachedFileList) {
//		System.out.println("attachedFileList.size(): " + attachedFileList.size());
//		System.out.println(attachedFileList);

		for (MemberAttachedFile attachedFile : attachedFileList) {
			memberDto.getAttachedFileList().add(attachedFile);	
		}

		return memberDto;
	}
}