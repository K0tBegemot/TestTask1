package com.kotbegemot.testtask1.controller;

import com.kotbegemot.testtask1.service.NewsService;
import com.kotbegemot.testtask1.service.implementation.NewsServiceImpl;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/news")
public class NewsRestfulController {
    public NewsService newsService;

    public NewsRestfulController(NewsServiceImpl newsService1) {
        newsService = newsService1;
    }

    /**
     * Method, that return image with given id in database
     *
     * @param imageId image id in database
     * @return binary formatted image
     */
    @GetMapping(path = "/image/{imageId}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public @ResponseBody byte[] getImage(@PathVariable(name = "imageId") @NotNull @PositiveOrZero Long imageId) {
        return newsService.getImage(imageId).getImage();
    }
}