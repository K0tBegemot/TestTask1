package com.kotbegemot.testtask1.repository.jpa;

import com.kotbegemot.testtask1.api.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NewsRepository extends JpaRepository<News, Long> {
    public Optional<News> findNewsByImage_Id(Long imageId);
}