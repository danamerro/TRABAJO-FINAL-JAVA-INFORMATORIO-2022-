package com.trabajofinalinfo.periodicoDM.model.converter;

import com.trabajofinalinfo.periodicoDM.model.dto.AutorDto;
import com.trabajofinalinfo.periodicoDM.model.entity.Autor;
import org.springframework.stereotype.Component;

@Component
public class AutorConverter {
    public AutorDto toDto(Autor autor) {
        return new AutorDto(
                autor.getId(),
                autor.getFirstname(),
                autor.getLastname(),
                autor.getFullname(),
                autor.getCreatedAt()
        );
    }
}
