package com.trabajofinalinfo.periodicoDM.converter;

import com.trabajofinalinfo.periodicoDM.model.converter.AutorConverter;
import com.trabajofinalinfo.periodicoDM.model.dto.AutorDto;
import com.trabajofinalinfo.periodicoDM.model.entity.Autor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AutorConverterTest {

    private AutorConverter underTest;

    @BeforeEach
    void setUp() {
        underTest = new AutorConverter();
    }

    @Test
    void when_given_a_entity_it_should_return_a_dto() {
        //given
        AutorDto autorDto = new AutorDto(null, "Roberto", "Carlos", "Roberto Carlos", LocalDate.of(2022,8,1));
        Autor autor = new Autor(null, "Roberto", "Carlos", "Roberto Carlos", LocalDate.of(2022,8,1));
        //then
        assertEquals(autorDto, underTest.toDto(autor));
    }

}