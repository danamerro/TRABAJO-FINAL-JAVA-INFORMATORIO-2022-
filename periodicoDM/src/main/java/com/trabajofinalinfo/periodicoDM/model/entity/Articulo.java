package com.trabajofinalinfo.periodicoDM.model.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
public class Articulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String description;
    private String url;
    private String urlToImage;
    private Boolean published;
    private LocalDate publishedAt;
    @Column(columnDefinition = "TEXT")
    private String content;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="author_id")
    private Autor autor;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="source_id")
    private Fuente fuente;

    public Articulo(String title, String description, String url, String urlToImage, Boolean published, LocalDate publishedAt, String content, Autor autor, Fuente fuente) {
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.published = published;
        this.publishedAt = publishedAt;
        this.content = content;
        this.autor = autor;
        this.fuente = fuente;
    }

    public Articulo() {
    }
}
