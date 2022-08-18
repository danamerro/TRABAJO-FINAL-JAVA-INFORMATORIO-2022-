package com.trabajofinalinfo.periodicoDM.converter;

import com.trabajofinalinfo.periodicoDM.model.converter.FuenteDtoToEntityConverter;
import com.trabajofinalinfo.periodicoDM.model.dto.FuenteDto;
import com.trabajofinalinfo.periodicoDM.model.entity.Fuente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class FuenteDtoToEntityConverterTest {

    private FuenteDtoToEntityConverter underTest;

    @BeforeEach
    void setUp() {
        underTest = new FuenteDtoToEntityConverter();
    }

    @Test
    void when_given_a_sourceDto_it_must_return_a_source_entity() {
        //given
        FuenteDto fuenteDto = new FuenteDto(
                null, "Diario Chaco", "diario-chaco", LocalDate.of(2022,8,1));
        Fuente fuente = new Fuente(
                null, "Diario Chaco", "diario-chaco", LocalDate.of(2022,8,1));
        //then
        assertEquals(fuente, underTest.toEntity(fuenteDto));
    }
}