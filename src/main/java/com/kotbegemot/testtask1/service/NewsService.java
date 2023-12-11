package com.kotbegemot.testtask1.service;

import com.kotbegemot.testtask1.api.dto.NewsDTO;
import com.kotbegemot.testtask1.api.dto.NewsFormDTO;
import com.kotbegemot.testtask1.api.dto.PagedNewsDTO;
import com.kotbegemot.testtask1.api.entity.Image;

/**
 * NewsService interface
 */
public interface NewsService {
    public PagedNewsDTO getPageByNumber(Integer pageNumber, Integer pageSize);

    public NewsDTO getNewsById(Long id);

    public void saveOrUpdateNews(NewsFormDTO newsDTO);

    public Image getImage(Long imageId);
}