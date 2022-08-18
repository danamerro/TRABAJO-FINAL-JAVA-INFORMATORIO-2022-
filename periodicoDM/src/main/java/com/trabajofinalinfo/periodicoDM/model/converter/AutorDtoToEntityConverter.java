package com.trabajofinalinfo.periodicoDM.model.converter;

import com.trabajofinalinfo.periodicoDM.model.dto.AutorDto;
import com.trabajofinalinfo.periodicoDM.model.entity.Autor;
import org.springframework.stereotype.Component;

@Component
public class AutorDtoToEntityConverter {
    public Autor toEntity(AutorDto autorDto) {
        return new Autor(
                autorDto.getId(),
                autorDto.getFirstname(),
                autorDto.getLastname(),
                autorDto.getFullname(),
                autorDto.getCreatedAt()
        );
    }
}
