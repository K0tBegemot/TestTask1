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
    @Column(name = "news_header", nullable = false)
    @Getter @Setter
    private String header;
    @Column(name = "news_pub_time", nullable = false)
    @Getter @Setter
    private LocalDateTime publicationTime;
    @Column(name = "news_text", nullable = false)
    @Getter @Setter
    private String text;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    @Getter @Setter
    private Image image;
}
