package com.kotbegemot.testtask1.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

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
    @Getter @Setter
    @JsonProperty("id")
    private Long id;
    @Getter @Setter
    @JsonProperty("header")
    private String header;
    @Getter @Setter
    @JsonProperty("text")
    private String text;
    @Getter @Setter
    @JsonProperty("image")
    private MultipartFile image;
}