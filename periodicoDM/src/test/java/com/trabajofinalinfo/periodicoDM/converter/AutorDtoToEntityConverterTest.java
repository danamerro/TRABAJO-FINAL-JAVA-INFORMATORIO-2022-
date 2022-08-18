package com.trabajofinalinfo.periodicoDM.converter;

import com.trabajofinalinfo.periodicoDM.model.converter.AutorDtoToEntityConverter;
import com.trabajofinalinfo.periodicoDM.model.dto.AutorDto;
import com.trabajofinalinfo.periodicoDM.model.entity.Autor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AutorDtoToEntityConverterTest {

    private AutorDtoToEntityConverter underTest;

    @BeforeEach
    void setUp() {
        underTest = new AutorDtoToEntityConverter();
    }

    @Test
    void when_given_an_authorDto_it_should_return_an_author_entity() {
        //given
        AutorDto autorDto = new AutorDto(null, "Roberto", "Carlos", "Roberto Carlos", LocalDate.of(2022,8,1));
        Autor autor = new Autor(null, "Roberto", "Carlos", "Roberto Carlos", LocalDate.of(2022,8,1));
        //then
        assertEquals(autor, underTest.toEntity(autorDto));
    }
}