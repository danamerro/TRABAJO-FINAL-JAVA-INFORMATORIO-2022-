package com.trabajofinalinfo.periodicoDM.model.converter;

import com.trabajofinalinfo.periodicoDM.model.dto.FuenteDto;
import com.trabajofinalinfo.periodicoDM.model.entity.Fuente;
import org.springframework.stereotype.Component;

@Component
public class FuenteDtoToEntityConverter {
    public Fuente toEntity(FuenteDto fuenteDto) {
        return new Fuente(
                fuenteDto.getId(),
                fuenteDto.getName(),
                fuenteDto.getCode(),
                fuenteDto.getCreatedAt()
        );
    }
}
