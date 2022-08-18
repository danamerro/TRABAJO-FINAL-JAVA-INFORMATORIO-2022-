package com.trabajofinalinfo.periodicoDM.service;

import com.trabajofinalinfo.periodicoDM.model.converter.ArticuloConverter;
import com.trabajofinalinfo.periodicoDM.model.converter.ArticuloDtoToEntityConverter;
import com.trabajofinalinfo.periodicoDM.model.dto.ArticuloDto;
import com.trabajofinalinfo.periodicoDM.config.exception.IdValueNotFoundException;
import com.trabajofinalinfo.periodicoDM.config.exception.InvalidDateException;
import com.trabajofinalinfo.periodicoDM.config.exception.InvalidIdException;
import com.trabajofinalinfo.periodicoDM.model.entity.Articulo;
import com.trabajofinalinfo.periodicoDM.model.entity.Autor;
import com.trabajofinalinfo.periodicoDM.model.entity.Fuente;
import com.trabajofinalinfo.periodicoDM.repository.ArticuloRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class ArticuloService {

    private final ArticuloDtoToEntityConverter articuloDtoToEntityConverter;
    private final ArticuloConverter articuloConverter;
    private final ArticuloRepository articuloRepository;
    private final AutorService autorService;
    private final FuenteService fuenteService;

    public ArticuloService(ArticuloDtoToEntityConverter articuloDtoToEntityConverter,
                           ArticuloConverter articuloConverter,
                           ArticuloRepository articuloRepository,
                           AutorService autorService,
                           FuenteService fuenteService) {
        this.articuloDtoToEntityConverter = articuloDtoToEntityConverter;
        this.articuloConverter = articuloConverter;
        this.articuloRepository = articuloRepository;
        this.autorService = autorService;
        this.fuenteService = fuenteService;
    }

    public ArticuloDto postArticulo(ArticuloDto articleDto) {
        LocalDate today = LocalDate.now();

        //Verifica si el ID de la fuente o del autoor es nulo
        if (articleDto.getAuthor().getId() == null || articleDto.getSource().getId() == null) {
            throw new InvalidIdException("El ID de la fuente o del autor no puede ser nulo");
        }

        //Verifica si el ID de la fuente y del autor existen, sino tira un exception
        autorService.findById(articleDto.getAuthor().getId());
        fuenteService.findById(articleDto.getSource().getId());

        Articulo articulo = articuloDtoToEntityConverter.toEntity(articleDto);
        articulo.setPublishedAt(today);
        return articuloConverter.toDto(articuloRepository.save(articulo));
    }

    public ArticuloDto putArticulo(Long articleId, ArticuloDto articuloDto) {
        //primero se verifica que el articulo existe
        Articulo articulo = articuloRepository.findById(articleId)
                .orElseThrow(
                        () -> new IdValueNotFoundException("El articulo con ID: " + articleId + " no existe")
                );

        // Setear atributos y guardar articulo
        articulo.setTitle(articuloDto.getTitle());
        articulo.setDescription(articuloDto.getDescription());
        articulo.setUrl(articuloDto.getUrl());
        articulo.setUrlToImage(articuloDto.getUrlToImage());
        articulo.setPublished(articuloDto.getPublished());
        articulo.setContent(articuloDto.getContent());
        articuloRepository.save(articulo);

        Optional<Articulo> articuloEntity = articuloRepository.findById(articleId);
        ArticuloDto articuloResponse = articuloConverter.toDto(articuloEntity.get());
        return articuloResponse;
    }

    public String deleteArticulo(Long articuloId) {
        //Verifica si existe el articulo con el ID y lo elimina si es asi, sino tira mensaje de error
        if (articuloRepository.findById(articuloId).isPresent()){
            articuloRepository.deleteById(articuloId);
            return "Articulo eliminado correctamente";
        } else {
            return "El articulo con ID: " + articuloId + " no existe";
        }
    }

    public Page<ArticuloDto> findAll(Pageable pageable) {
        Page<ArticuloDto> articlesDto = articuloRepository.findAll(pageable)
                .map(articuloConverter::toDto);
        return articlesDto;
    }

    public Page<ArticuloDto> findByFilter(String filter, Pageable pageable) {
        Page<ArticuloDto> articlesDto = articuloRepository.findByFilter(filter,pageable)
                .map(articuloConverter::toDto);
        return articlesDto;
    }
}
