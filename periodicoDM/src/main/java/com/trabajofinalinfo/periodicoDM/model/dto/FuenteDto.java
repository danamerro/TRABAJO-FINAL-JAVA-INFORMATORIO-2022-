package com.trabajofinalinfo.periodicoDM.model.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class FuenteDto {

    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String code;

    private LocalDate createdAt;

    public FuenteDto(Long id, String name, String code, LocalDate createdAt) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.createdAt = createdAt;
    }

    public FuenteDto() {
    }
}
