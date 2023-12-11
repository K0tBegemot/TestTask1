package com.kotbegemot.testtask1.api.dto;

import com.kotbegemot.testtask1.controller.NewsViewController;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import org.springframework.ui.Model;

import java.util.List;

/**
 * DTO used to populate template with several objects of type {@link com.kotbegemot.testtask1.api.dto.NewsDTO}
 *
 * @see NewsViewController#getNewsLineMainPage(Integer, Integer, Model)
 */
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PagedNewsDTO {
    /**
     * List of news on this page
     */
    @Getter
    @Setter
    private List<NewsDTO> news;
    /**
     * Number of news on this page
     */
    @Getter
    @Setter
    @NotNull
    @Positive
    private Integer size;
    /**
     * Zero based page number
     */
    @Getter
    @Setter
    @NotNull
    @PositiveOrZero
    private Integer number;
    /**
     * Number of all pages. Guaranteed greater than or equal to {@link com.kotbegemot.testtask1.api.dto.PagedNewsDTO#number}
     */
    @Getter
    @Setter
    @NotNull
    @PositiveOrZero
    private Integer allPageNumber;
}