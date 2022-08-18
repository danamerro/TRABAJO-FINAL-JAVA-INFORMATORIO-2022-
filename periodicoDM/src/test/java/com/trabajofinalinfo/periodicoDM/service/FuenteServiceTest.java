package com.trabajofinalinfo.periodicoDM.service;

import com.trabajofinalinfo.periodicoDM.model.converter.FuenteConverter;
import com.trabajofinalinfo.periodicoDM.model.converter.FuenteDtoToEntityConverter;
import com.trabajofinalinfo.periodicoDM.model.dto.FuenteDto;
import com.trabajofinalinfo.periodicoDM.config.exception.IdValueNotFoundException;
import com.trabajofinalinfo.periodicoDM.config.exception.InvalidDateException;
import com.trabajofinalinfo.periodicoDM.model.entity.Fuente;
import com.trabajofinalinfo.periodicoDM.repository.FuenteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.time.LocalDate;
import java.util.Optional;


import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FuenteServiceTest {
    @Mock
    private FuenteRepository fuenteRepository;
    private FuenteService underTest;
    @Mock
    private FuenteDtoToEntityConverter fuenteDtoToEntityConverter;

    private FuenteConverter fuenteConverter;

    @BeforeEach
    void setUp() {
        underTest = new FuenteService(fuenteDtoToEntityConverter, fuenteConverter, fuenteRepository);
    }


    @Test
    void verify_that_when_a_source_exist_then_deleteById_is_called() {
        //given
        Long sourceId = 45L;
        LocalDate createdAt = LocalDate.of(2022,8,1);
        Fuente fuente = new Fuente(45L,"Clarin","clarin",createdAt);
        //when
        given(fuenteRepository.findById(sourceId)).willReturn(Optional.of(fuente));
        underTest.deleteFuente(sourceId);
        //then
        verify(fuenteRepository).deleteById(sourceId);
    }

    @Test
    void when_sourceId_does_not_exist_should_return_a_error_message() {
        //given
        Long fuenteId = 48L;
        //then
        assertEquals(underTest.deleteFuente(fuenteId), "La fuente con ID: " + fuenteId + " no existe");
    }
}