package com.kotbegemot.testtask1.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * DTO used to edit or add news in the {@link com.kotbegemot.testtask1.controller.NewsController#addNews(String, String, MultipartFile, HttpServletRequest)}
 * or in {@link com.kotbegemot.testtask1.controller.NewsController#editNews(Long, String, String, MultipartFile, HttpServletRequest)}controller methods
 */
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EditNewsDTO
{
    public EditNewsDTO(String header1, String text1, MultipartFile image1)
    {
        header = header1;
        text = text1;
        image = image1;
    }
    /**
     * Unique identifier of the news in the database. When adding a new news it should be equal to null
     */
    @Getter @Setter
    @JsonProperty("id")
    private Long id;
    /**
     *Header of the news in the database. Should be not null
     */
    @Getter @Setter
    @NotNull
    @JsonProperty("header")
    private String header;
    /**
     * Text of the news in the database. Should be not null
     */
    @Getter @Setter
    @NotNull
    @JsonProperty("text")
    private String text;
    /**
     * Image of the news, transferred as MultipartImage
     */
    @Getter @Setter
    @JsonProperty("image")
    private MultipartFile image;
}