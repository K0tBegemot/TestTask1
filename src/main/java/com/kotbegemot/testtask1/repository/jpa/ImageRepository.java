package com.kotbegemot.testtask1.repository.jpa;

import com.kotbegemot.testtask1.api.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
