package com.kotbegemot.testtask1.service.implementation;

import com.kotbegemot.testtask1.api.dto.EditNewsDTO;
import com.kotbegemot.testtask1.api.dto.NewsDTO;
import com.kotbegemot.testtask1.api.dto.PagedNewsDTO;
import com.kotbegemot.testtask1.api.entity.Image;
import com.kotbegemot.testtask1.api.entity.News;
import com.kotbegemot.testtask1.api.mapper.NewsMapper;
import com.kotbegemot.testtask1.repository.jpa.ImageRepository;
import com.kotbegemot.testtask1.repository.jpa.NewsRepository;
import com.kotbegemot.testtask1.service.NewsService;
import com.kotbegemot.testtask1.service.exception.ImageNotFoundException;
import com.kotbegemot.testtask1.service.exception.NewsNotFoundException;
import com.kotbegemot.testtask1.service.util.PageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class NewsServiceImpl implements NewsService {
    private static final Logger logger = LoggerFactory.getLogger(NewsServiceImpl.class);
    private NewsRepository newsRepository;
    private ImageRepository imageRepository;
    private NewsMapper mapper;
    public NewsServiceImpl(NewsRepository newsRepository1, ImageRepository imageRepository1, NewsMapper mapper1)
    {
        newsRepository = newsRepository1;
        imageRepository = imageRepository1;
        mapper = mapper1;
    }

    @Override
    public PagedNewsDTO getPageByNumber(Integer pageNumber, Integer pageSize)
    {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        Page<News> pageResult = newsRepository.findAll(page);
        logger.debug("{}", pageResult.getContent());
        PageUtils.checkPageNumber(pageResult, pageNumber);
        return new PagedNewsDTO(mapper.newsListToDTO(pageResult.getContent()), pageResult.getSize(), pageResult.getNumber(), pageResult.getTotalPages());
    }

    @Override
    public NewsDTO getNewsById(Long id)
    {
        Optional<News> news = newsRepository.findById(id);
        if(news.isPresent())
        {
            return mapper.newsToDTO(news.get());
        }
        throw new NewsNotFoundException();
    }

    @Override
    public void saveOrUpdateNews(EditNewsDTO newsDTO)
    {
        News news = mapper.addNewsDTOToEntity(newsDTO);
        newsRepository.saveAndFlush(news);
    }

    @Override
    public Image getImage(Long imageId)
    {
        Optional<Image> image = imageRepository.findById(imageId);
        if(image.isPresent())
        {
            return image.get();
        }
        throw new ImageNotFoundException();
    }
}