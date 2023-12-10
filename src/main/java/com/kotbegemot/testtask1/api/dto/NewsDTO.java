package com.kotbegemot.testtask1.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NewsDTO {
    @Getter @Setter
    @JsonProperty("header")
    private String header;
    @Getter @Setter
    @JsonProperty("time")
    private String time;
    @Getter @Setter
    @JsonProperty("text")
    private String text;
    @Getter @Setter
    @JsonProperty("imageId")
    private String imageId;
}
