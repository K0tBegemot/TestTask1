package com.kotbegemot.testtask1.api.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PagedNewsDTO {
    @Getter @Setter
    private List<NewsDTO> news;
    @Getter @Setter
    private Integer size;
    @Getter @Setter
    private Integer number;
    @Getter @Setter
    private Integer allPageNumber;
}