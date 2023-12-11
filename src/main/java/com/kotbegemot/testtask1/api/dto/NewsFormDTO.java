package com.kotbegemot.testtask1.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kotbegemot.testtask1.controller.NewsViewController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * DTO used to edit or add news in the {@link NewsViewController#addNews(NewsFormDTO, BindingResult, HttpServletRequest)}
 * or in {@link NewsViewController#editNews(NewsFormDTO, HttpServletRequest)}controller methods
 */
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NewsFormDTO {
    public NewsFormDTO(String header1, String text1, MultipartFile image1) {
        header = header1;
        text = text1;
        image = image1;
    }

    /**
     * Unique identifier of the news in the database. When adding a new news it should be equal to null
     *
     * @param id Identifier
     */
    @PositiveOrZero
    @JsonProperty("id")
    @Getter
    @Setter
    private Long id;
    /**
     * Header of the news in the database. Should be not null
     */
    @Getter
    @Setter
    @NotEmpty
    @JsonProperty("header")
    private String header;
    /**
     * Text of the news in the database. Should be not null
     */
    @Getter
    @Setter
    @NotEmpty
    @JsonProperty("text")
    private String text;
    /**
     * Image of the news, transferred as MultipartImage
     */
    @Getter
    @Setter
    @JsonProperty("image")
    private MultipartFile image;
}