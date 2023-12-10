package com.kotbegemot.testtask1.api.mapper;

import com.kotbegemot.testtask1.api.dto.EditNewsDTO;
import com.kotbegemot.testtask1.api.dto.NewsDTO;
import com.kotbegemot.testtask1.api.entity.News;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

/**
 * Mapper class that transforms different news DTO to {@link com.kotbegemot.testtask1.api.entity.News} or vice versa:
 * <ul>
 *     <li>{@link com.kotbegemot.testtask1.api.dto.EditNewsDTO}</li>
 *     <li>{@link com.kotbegemot.testtask1.api.dto.NewsDTO}</li>
 *     <li>{@link com.kotbegemot.testtask1.api.dto.PagedNewsDTO}</li>
 * </ul>
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface NewsMapper {
    /**
     * Method that transform single object of type {@link com.kotbegemot.testtask1.api.entity.News} to type {@link com.kotbegemot.testtask1.api.dto.NewsDTO}.
     * Inserts the image ID instead of the image itself
     * @param news Valid news in database
     * @return NewsDTO object
     */
    @Mapping(target = "header", source = "header")
    @Mapping(target = "time", source = "publicationTime")
    @Mapping(target = "text", source = "text")
    @Mapping(target = "imageId", expression = "java(Long.toString(news.getImage() == null ? null : news.getImage().getId()))")
    public NewsDTO newsToDTO(News news);

    /**
     * Method that transform list of {@link com.kotbegemot.testtask1.api.entity.News} objects to list of {@link com.kotbegemot.testtask1.api.dto.NewsDTO} objects
     * @param news List of news entity
     * @return List of news dto
     */
    public List<NewsDTO> newsListToDTO(List<News> news);

    /**
     * Method that transforms single object of type {@link com.kotbegemot.testtask1.api.dto.EditNewsDTO} to entity type {@link com.kotbegemot.testtask1.api.entity.News}.
     * Insert date and time when the news was received and convert Multipart image to binary format
     * @param dto Valid news dto
     * @return Entity to save in database
     */
    @Mapping(target = "header", source = "header")
    @Mapping(target = "publicationTime", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "id", expression = "java(dto.getId() == null ? null : dto.getId())")
    @Mapping(target = "text", source = "text")
    @Mapping(target = "image", expression = "java(new com.kotbegemot.testtask1.api.entity.Image(dto.getImage()))")
    public News addNewsDTOToEntity(EditNewsDTO dto);
}