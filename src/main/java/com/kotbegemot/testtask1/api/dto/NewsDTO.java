package com.kotbegemot.testtask1.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.ui.Model;

/**
 * DTO used to populate thymeleaf template with data
 * @see com.kotbegemot.testtask1.controller.NewsController#getNewsLineMainPage(Integer, Integer, Model)
 */
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NewsDTO {
    /**
     * Header of the news. Must be not null
     */
    @NotNull
    @Getter @Setter
    @JsonProperty("header")
    private String header;
    /**
     * Time of creation or editing news. Must be not null
     */
    @NotNull
    @Getter @Setter
    @JsonProperty("time")
    private String time;
    /**
     * Text of the news. Must be not null
     */
    @Getter @Setter
    @JsonProperty("text")
    private String text;
    /**
     * Unique identifier of news image in database
     */
    @Getter @Setter
    @JsonProperty("imageId")
    private String imageId;
}
