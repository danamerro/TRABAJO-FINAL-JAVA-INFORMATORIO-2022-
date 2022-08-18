package com.trabajofinalinfo.periodicoDM.service;

import com.trabajofinalinfo.periodicoDM.model.converter.FuenteConverter;
import com.trabajofinalinfo.periodicoDM.model.converter.FuenteDtoToEntityConverter;
import com.trabajofinalinfo.periodicoDM.model.dto.FuenteDto;
import com.trabajofinalinfo.periodicoDM.config.exception.IdValueNotFoundException;
import com.trabajofinalinfo.periodicoDM.config.exception.InvalidDateException;
import com.trabajofinalinfo.periodicoDM.model.entity.Fuente;
import com.trabajofinalinfo.periodicoDM.repository.FuenteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class FuenteService {
    private final FuenteDtoToEntityConverter fuenteDtoToEntityConverter;
    private final FuenteConverter fuenteConverter;
    private final FuenteRepository fuenteRepository;

    public FuenteService(FuenteDtoToEntityConverter fuenteDtoToEntityConverter, FuenteConverter fuenteConverter, FuenteRepository fuenteRepository) {
        this.fuenteDtoToEntityConverter = fuenteDtoToEntityConverter;
        this.fuenteConverter = fuenteConverter;
        this.fuenteRepository = fuenteRepository;
    }

    public FuenteDto postFuente(FuenteDto fuenteDto) {
        LocalDate today = LocalDate.now();
        Fuente fuente = fuenteDtoToEntityConverter.toEntity(fuenteDto);
        fuente.setCreatedAt(today);
        fuente.setCode(fuente.getName().replace(" ","-"));
        return fuenteConverter.toDto(fuenteRepository.save(fuente));
    }

    public FuenteDto putFuente(Long fuenteId, FuenteDto fuenteDto) {
        fuenteRepository.findById(fuenteId).map(f -> {
            f.setName(fuenteDto.getName());
            f.setCode(fuenteDto.getName().replace(" ","-"));
            return fuenteRepository.save(f);
        }).orElseThrow(() -> new IdValueNotFoundException("La fuente con ID: " + fuenteId + " no existe"));

        Optional<Fuente> fuenteEntity = fuenteRepository.findById(fuenteId);
        FuenteDto fuenteResponse = fuenteConverter.toDto(fuenteEntity.get());
        return fuenteResponse;
    }

    public String deleteFuente(Long fuenteId) {
        if (fuenteRepository.findById(fuenteId).isPresent()){
            fuenteRepository.deleteById(fuenteId);
            return "Fuente eliminada exitosamente";
        } else {
            return "La fuente con ID: " + fuenteId + " no existe";
        }
    }

    public Page<FuenteDto> findAll(Pageable pageable) {
        Page<FuenteDto> sourcesDto = fuenteRepository.findAll(pageable)
                .map(fuenteConverter::toDto);
        return sourcesDto;
    }

    public Page<FuenteDto> findByName(String name, Pageable pageable) {
        Page<FuenteDto> sourcesDto = fuenteRepository.findByNameContaining(name, pageable)
                .map(fuenteConverter::toDto);
        return sourcesDto;
    }

    public Fuente findById(Long id) {
        Fuente fuente = fuenteRepository
                .findById(id)
                .orElseThrow(
                        () -> new IdValueNotFoundException("La fuente con ID: " + id + " no existe")
                );
        return fuente;
    }
}
