package com.trabajofinalinfo.periodicoDM.model.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;
    private String fullname;
    private LocalDate createdAt;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL)
    private Set<Articulo> articulos = new HashSet<>();

    public Autor(Long id, String firstname, String lastname, String fullname, LocalDate createdAt) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.fullname = fullname;
        this.createdAt = createdAt;
    }

    public Autor() {
    }
}
