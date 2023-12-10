package com.kotbegemot.testtask1.api.mapper;

import com.kotbegemot.testtask1.api.dto.EditNewsDTO;
import com.kotbegemot.testtask1.api.dto.NewsDTO;
import com.kotbegemot.testtask1.api.entity.News;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface NewsMapper {
    @Mapping(target = "header", source = "header")
    @Mapping(target = "time", source = "publicationTime")
    @Mapping(target = "text", source = "text")
    @Mapping(target = "imageId", expression = "java(Long.toString(news.getImage() == null ? null : news.getImage().getId()))")
    public NewsDTO newsToDTO(News news);
    public List<NewsDTO> newsListToDTO(List<News> news);
    @Mapping(target = "header", source = "header")
    @Mapping(target = "publicationTime", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "id", expression = "java(dto.getId() == null ? null : dto.getId())")
    @Mapping(target = "text", source = "text")
    @Mapping(target = "image", expression = "java(new com.kotbegemot.testtask1.api.entity.Image(dto.getImage()))")
    public News addNewsDTOToEntity(EditNewsDTO dto);
}