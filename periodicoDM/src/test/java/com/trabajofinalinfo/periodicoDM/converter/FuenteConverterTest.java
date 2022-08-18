package com.trabajofinalinfo.periodicoDM.converter;

import com.trabajofinalinfo.periodicoDM.model.converter.FuenteConverter;
import com.trabajofinalinfo.periodicoDM.model.dto.FuenteDto;
import com.trabajofinalinfo.periodicoDM.model.entity.Fuente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class FuenteConverterTest {

    private FuenteConverter underTest;

    @BeforeEach
    void setUp() {
        underTest = new FuenteConverter();
    }

    @Test
    void when_given_a_entity_it_should_return_a_dto() {
        FuenteDto fuenteDto = new FuenteDto(
                null, "Diario Chaco", "diario-chaco", LocalDate.of(2022,8,1));
        Fuente fuente = new Fuente(
                null, "Diario Chaco", "diario-chaco", LocalDate.of(2022,8,1));
        //then
        assertEquals(fuenteDto, underTest.toDto(fuente));
    }
}