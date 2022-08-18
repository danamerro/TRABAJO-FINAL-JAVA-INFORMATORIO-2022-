package com.trabajofinalinfo.periodicoDM.model.converter;

import com.trabajofinalinfo.periodicoDM.model.dto.FuenteDto;
import com.trabajofinalinfo.periodicoDM.model.entity.Fuente;
import org.springframework.stereotype.Component;

@Component
public class FuenteConverter {
    public FuenteDto toDto(Fuente fuente) {
        return new FuenteDto(
                fuente.getId(),
                fuente.getName(),
                fuente.getCode(),
                fuente.getCreatedAt()
        );
    }
}
