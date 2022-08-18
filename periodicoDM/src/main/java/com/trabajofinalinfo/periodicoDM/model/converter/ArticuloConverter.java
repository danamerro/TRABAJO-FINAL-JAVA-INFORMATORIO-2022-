package com.trabajofinalinfo.periodicoDM.model.converter;

import com.trabajofinalinfo.periodicoDM.model.dto.ArticuloDto;
import com.trabajofinalinfo.periodicoDM.model.entity.Articulo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ArticuloConverter {

    private final AutorConverter autorConverter;
    private final FuenteConverter sourceconverter;

    public ArticuloConverter(AutorConverter autorConverter, FuenteConverter sourceconverter) {
        this.autorConverter = autorConverter;
        this.sourceconverter = sourceconverter;
    }

    public List<ArticuloDto> toDto(List<Articulo> articulos) {
        return articulos.stream().map(article -> toDto(article))
                .collect(Collectors.toList());
    }

    public ArticuloDto toDto(Articulo articulo) {
        return new ArticuloDto(
                articulo.getId(),
                articulo.getTitle(),
                articulo.getDescription(),
                articulo.getUrl(),
                articulo.getUrlToImage(),
                articulo.getPublished(),
                articulo.getPublishedAt(),
                articulo.getContent(),
                autorConverter.toDto(articulo.getAutor()),
                sourceconverter.toDto(articulo.getFuente())
        );
    }
}
