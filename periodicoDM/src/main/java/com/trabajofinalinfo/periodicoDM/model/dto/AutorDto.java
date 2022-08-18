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
public class AutorDto {
    private Long id;
    @NotBlank
    private String firstname;
    @NotBlank
    private String lastname;
    private String fullname;
    private LocalDate createdAt;

    public AutorDto(Long id, String firstname, String lastname, String fullname, LocalDate createdAt) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.fullname = fullname;
        this.createdAt = createdAt;
    }

    public AutorDto() {
    }
}
