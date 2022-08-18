package com.trabajofinalinfo.periodicoDM.model.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class ArticuloDto {
    private Long id;
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @NotBlank
    private String url;
    @NotBlank
    private String urlToImage;
    @NotNull
    private Boolean published;
    private LocalDate publishedAt;
    @NotBlank
    private String content;
    private AutorDto author;
    private FuenteDto source;

    public ArticuloDto(Long id,
                       String title,
                       String description,
                       String url,
                       String urlToImage,
                       Boolean published,
                       LocalDate publishedAt,
                       String content,
                       AutorDto author,
                       FuenteDto source) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.published = published;
        this.publishedAt = publishedAt;
        this.content = content;
        this.author = author;
        this.source = source;
    }

    public ArticuloDto() {
    }
}
