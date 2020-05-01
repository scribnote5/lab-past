package kr.ac.univ.lab.mapper;

public interface EntityMapper <Dto, Entitiy> {
	Entitiy toEntity(Dto dto);
    Dto toDto(Entitiy entity);
}