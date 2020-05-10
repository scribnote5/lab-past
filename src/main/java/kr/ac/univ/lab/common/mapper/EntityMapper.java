package kr.ac.univ.lab.common.mapper;

import java.util.List;

public interface EntityMapper <Dto, Entity> {
	Entity toEntity(Dto dto);
    Dto toDto(Entity entity);
    List<Entity> toEntity(List<Dto> dto);
    List<Dto> toDto(List<Entity> entity);
}