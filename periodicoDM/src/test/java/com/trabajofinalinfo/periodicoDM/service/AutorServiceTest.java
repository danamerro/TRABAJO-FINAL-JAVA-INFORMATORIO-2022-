package com.trabajofinalinfo.periodicoDM.service;

import com.trabajofinalinfo.periodicoDM.model.converter.AutorConverter;
import com.trabajofinalinfo.periodicoDM.model.converter.AutorDtoToEntityConverter;
import com.trabajofinalinfo.periodicoDM.model.dto.AutorDto;
import com.trabajofinalinfo.periodicoDM.config.exception.IdValueNotFoundException;
import com.trabajofinalinfo.periodicoDM.config.exception.InvalidDateException;
import com.trabajofinalinfo.periodicoDM.model.entity.Autor;
import com.trabajofinalinfo.periodicoDM.repository.AutorRepository;
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
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AutorServiceTest {
    @Mock
    private AutorRepository autorRepository;
    private AutorService underTest;
    @Mock
    private AutorDtoToEntityConverter autorDtoToEntityConverter;
    @Mock
    private AutorConverter autorConverter;


    @BeforeEach
    void setUp() {
        underTest = new AutorService(autorDtoToEntityConverter, autorConverter, autorRepository);
    }

    @Test
    void given_a_valid_dto_the_method_should_return_a_valid_dto() {
        //given
        LocalDate createdAt = LocalDate.now();
        AutorDto autorDto = new AutorDto(null, "Roberto", "Carlos", null, createdAt);
        Autor autor = new Autor(null, "Roberto", "Carlos", null, createdAt);
        given(autorDtoToEntityConverter.toEntity(autorDto)).willReturn(autor);
        AutorDto expected = autorConverter.toDto(autor);
        //then
        assertEquals(expected, underTest.postAutor(autorDto));
    }

    @Test
    void given_a_valid_dto_the_author_being_save_must_be_equals_to_the_provided() {
        //given
        LocalDate createdAt = LocalDate.now();
        AutorDto autorDto = new AutorDto(null, "Roberto", "Carlos", null, createdAt);
        Autor autor = new Autor(null, "Roberto", "Carlos", null, createdAt);
        given(autorDtoToEntityConverter.toEntity(autorDto)).willReturn(autor);
        //then
        underTest.postAutor(autorDto);
        ArgumentCaptor<Autor> authorArgumentCaptor = ArgumentCaptor.forClass(Autor.class);
        verify(autorRepository).save(authorArgumentCaptor.capture());
        Autor capturedAutor = authorArgumentCaptor.getValue();
        assertThat(capturedAutor).isEqualTo(autor);
    }

    @Test
    void verify_that_when_an_author_exist_then_deleteById_is_called() {
        //given
        Long authorId = 45L;
        LocalDate createdAt = LocalDate.of(2022,8,1);
        Autor autor = new Autor(9L, "Roberto", "Carlos", "Roberto Carlos", createdAt);
        //when
        given(autorRepository.findById(authorId)).willReturn(Optional.of(autor));
        underTest.deleteAutor(authorId);
        //then
        verify(autorRepository).deleteById(authorId);
    }

    @Test
    void when_authorId_does_not_exist_then_an_exception_is_called() {
        //given
        Long autorId = 48L;
        //then
        assertEquals(underTest.deleteAutor(autorId), "El autor con ID: " + autorId + " no existe");
    }
}