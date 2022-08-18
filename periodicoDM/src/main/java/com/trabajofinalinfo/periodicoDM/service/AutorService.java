package com.trabajofinalinfo.periodicoDM.service;

import com.trabajofinalinfo.periodicoDM.model.converter.AutorConverter;
import com.trabajofinalinfo.periodicoDM.model.converter.AutorDtoToEntityConverter;
import com.trabajofinalinfo.periodicoDM.model.dto.AutorDto;
import com.trabajofinalinfo.periodicoDM.config.exception.IdValueNotFoundException;
import com.trabajofinalinfo.periodicoDM.config.exception.InvalidDateException;
import com.trabajofinalinfo.periodicoDM.model.entity.Autor;
import com.trabajofinalinfo.periodicoDM.repository.AutorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class AutorService {

    private final AutorDtoToEntityConverter autorDtoToEntityConverter;
    private final AutorConverter autorConverter;
    private final AutorRepository autorRepository;

    public AutorService(AutorDtoToEntityConverter autorDtoToEntityConverter, AutorConverter autorConverter, AutorRepository autorRepository) {
        this.autorDtoToEntityConverter = autorDtoToEntityConverter;
        this.autorConverter = autorConverter;
        this.autorRepository = autorRepository;
    }

    public AutorDto postAutor(AutorDto autorDto) {
        LocalDate today = LocalDate.now();
        Autor autor = autorDtoToEntityConverter.toEntity(autorDto);
        autor.setCreatedAt(today);
        autor.setFullname(autor.getFirstname() + " " + autor.getLastname());
        return autorConverter.toDto(autorRepository.save(autor));
    }

    public AutorDto putAutor(Long autorId, AutorDto autorDto) {
        autorRepository.findById(autorId).map(author -> {
            author.setFirstname(autorDto.getFirstname());
            author.setFullname(autorDto.getFirstname() + " " + autorDto.getLastname());
            author.setLastname(autorDto.getLastname());
            return autorRepository.save(author);
        }).orElseThrow(() -> new IdValueNotFoundException("El autor con ID: " + autorId + " no existe"));

        Optional<Autor> authorEntity = autorRepository.findById(autorId);
        AutorDto authorResponse = autorConverter.toDto(authorEntity.get());
        return authorResponse;
    }

    public String deleteAutor(Long autorId) {
        if (autorRepository.findById(autorId).isPresent()) {
            autorRepository.deleteById(autorId);
            return "Autor eliminado exitosamente";
        }
        else {
            return "El autor con ID: " + autorId + " no existe";
        }
    }

    public Page<AutorDto> findAll(Pageable pageable) {
        Page<AutorDto> autorsDto = autorRepository.findAll(pageable)
                .map(autorConverter::toDto);
        return autorsDto;
    }

    public Page<AutorDto> findByFullname(String fullname, Pageable pageable) {
        Page<AutorDto> autorsDto = autorRepository.findByFullnameContaining(fullname, pageable)
                .map(autorConverter::toDto);
        return autorsDto;
    }

    public Page<AutorDto> findByCreatedAfterDate(LocalDate date, Pageable pageable) {
        Page<AutorDto> autorsDto = autorRepository.findByCreatedAtAfter(date, pageable)
                .map(autorConverter::toDto);
        return autorsDto;
    }

    public Page<AutorDto> findByFullnameAndDateAfter(String fullname, LocalDate date, Pageable pageable) {
        Page<AutorDto> autorsDto = autorRepository.findByFullnameAndDateAfter(fullname, date, pageable)
                .map(autorConverter::toDto);
        return autorsDto;
    }

    public Autor findById(Long id) {
        Autor autor = autorRepository
                .findById(id)
                .orElseThrow(
                        () -> new IdValueNotFoundException("El autor con ID: " + id + " no existe")
                );
        return autor;
    }
}
