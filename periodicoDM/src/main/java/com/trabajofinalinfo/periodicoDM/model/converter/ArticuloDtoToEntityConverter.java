package com.trabajofinalinfo.periodicoDM.model.converter;

import com.trabajofinalinfo.periodicoDM.model.dto.ArticuloDto;
import com.trabajofinalinfo.periodicoDM.model.entity.Articulo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ArticuloDtoToEntityConverter {

    private final AutorDtoToEntityConverter autorDtoToEntityConverter;
    private final FuenteDtoToEntityConverter fuenteDtoToEntityConverter;

    public ArticuloDtoToEntityConverter(AutorDtoToEntityConverter autorDtoToEntityConverter, FuenteDtoToEntityConverter fuenteDtoToEntityConverter) {
        this.autorDtoToEntityConverter = autorDtoToEntityConverter;
        this.fuenteDtoToEntityConverter = fuenteDtoToEntityConverter;
    }

    public List<Articulo> toEntity(List<ArticuloDto> articlesDto) {
        return articlesDto.stream().map( articleDto -> toEntity(articleDto))
                .collect(Collectors.toList());
    }

    public Articulo toEntity(ArticuloDto articuloDto) {
        return new Articulo(
                articuloDto.getTitle(),
                articuloDto.getDescription(),
                articuloDto.getUrl(),
                articuloDto.getUrlToImage(),
                articuloDto.getPublished(),
                articuloDto.getPublishedAt(),
                articuloDto.getContent(),
                autorDtoToEntityConverter.toEntity(articuloDto.getAuthor()),
                fuenteDtoToEntityConverter.toEntity(articuloDto.getSource())
        );
    }
}
