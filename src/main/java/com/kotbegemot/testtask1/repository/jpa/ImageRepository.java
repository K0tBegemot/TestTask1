package com.kotbegemot.testtask1.repository.jpa;

import com.kotbegemot.testtask1.api.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository to save Image entity
 *
 * @see Image
 * @see <a href="https://docs.spring.io/spring-data/jpa/docs/3.2.0/api/org/springframework/data/jpa/repository/JpaRepository.html">JPARepository</a>
 */
public interface ImageRepository extends JpaRepository<Image, Long> {
}
