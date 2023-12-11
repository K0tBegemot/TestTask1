package com.kotbegemot.testtask1.api.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Entity class to save image of news
 */
@Entity
@Table(name = "IMAGE_TABLE")
@NoArgsConstructor
@ToString(exclude = "image")
public class Image {
    private static final Logger logger = LoggerFactory.getLogger(Image.class);

    public Image(MultipartFile image1) {
        try {
            image = image1.getBytes();
        } catch (IOException e) {
            logger.warn("Image will be lost due to IOException. Warning: ", e);
        }
    }

    /**
     * Unique identifier of image in database
     */
    @Id
    @GeneratedValue(generator = "image-sequence")
    @GenericGenerator(
            name = "image-sequence",
            type = SequenceStyleGenerator.class,
            parameters = {
                    @Parameter(name = "sequence_name", value = "IMAGE_TABLE_SEQ1"),
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    @Column(name = "id", nullable = true)
    @Getter
    @Setter
    private Long id;
    /**
     * Blob image in database
     */
    @Column(name = "image", nullable = true)
    @Lob
    @Getter
    @Setter
    private byte[] image;
}