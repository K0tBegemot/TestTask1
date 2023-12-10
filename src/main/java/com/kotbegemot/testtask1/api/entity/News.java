package com.kotbegemot.testtask1.api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

import java.time.LocalDateTime;

/**
 * Entity class to save news
 */
@Entity
@Table(name = "NEWS_TABLE", indexes = {@Index(name = "header_index", columnList = "news_header", unique = false)})
@NamedEntityGraph(
        name = "news_graph",
        attributeNodes = {
                @NamedAttributeNode("header"),
                @NamedAttributeNode("publicationTime"),
                @NamedAttributeNode("text"),
                @NamedAttributeNode("image")
        })
@NoArgsConstructor
@ToString
public class News {
    /**
     * unique identifier of news
     */
    @Id
    @GeneratedValue(generator = "news-sequence")
    @GenericGenerator(
            name = "news-sequence",
            type = SequenceStyleGenerator.class,
            parameters = {
                    @Parameter(name = "sequence_name", value = "NEWS_TABLE_SEQ1"),
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    @Column(name = "news_id", nullable = true)
    @Getter @Setter
    private Long id;
    /**
     * Text header of news
     */
    @Column(name = "news_header", nullable = false)
    @Getter @Setter
    private String header;
    /**
     * Publication time of news
     */
    @Column(name = "news_pub_time", nullable = false)
    @Getter @Setter
    private LocalDateTime publicationTime;
    /**
     * Text of news
     */
    @Column(name = "news_text", nullable = false)
    @Getter @Setter
    private String text;
    /**
     * Image of news
     * @see com.kotbegemot.testtask1.api.entity.Image
     */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    @Getter @Setter
    private Image image;
}
