package com.trabajofinalinfo.periodicoDM.controller;

import com.trabajofinalinfo.periodicoDM.model.dto.ArticuloDto;
import com.trabajofinalinfo.periodicoDM.service.ArticuloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;


@Validated
@RestController
@RequestMapping("/api/v1/articulo")
public class ArticuloController {

    private final ArticuloService articuloService;

    @Autowired
    public ArticuloController(ArticuloService articuloService) {
        this.articuloService = articuloService;
    }

    @GetMapping
    public ResponseEntity<?> getArticulos(@Valid @Size(min=3) @RequestParam(required = false)
                                          String filter, Pageable pageable) {
        if (filter != null) {
            return new ResponseEntity<>(articuloService.findByFilter(filter, pageable), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(articuloService.findAll(pageable), HttpStatus.OK);
        }
    }

    @PostMapping("/crear")
    public ResponseEntity<?> postArticulo(@RequestBody @Valid ArticuloDto articuloDto) {
        return new ResponseEntity<>(articuloService.postArticulo(articuloDto), HttpStatus.CREATED);
    }

    @PutMapping("/modificar")
    public ResponseEntity<?> putArticulo(@RequestParam Long articuloId, @RequestBody ArticuloDto articuloDto) {
        return new ResponseEntity<>(articuloService.putArticulo(articuloId, articuloDto), HttpStatus.OK);
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<?> deleteArticulo(@RequestParam Long articuloId) {
        return new ResponseEntity<>(articuloService.deleteArticulo(articuloId), HttpStatus.OK);
    }
}
