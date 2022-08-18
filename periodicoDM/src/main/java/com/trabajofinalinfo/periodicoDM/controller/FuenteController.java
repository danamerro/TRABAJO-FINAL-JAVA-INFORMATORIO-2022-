package com.trabajofinalinfo.periodicoDM.controller;

import com.trabajofinalinfo.periodicoDM.model.dto.FuenteDto;
import com.trabajofinalinfo.periodicoDM.service.FuenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/fuente")
public class FuenteController {

    private final FuenteService fuenteService;

    @Autowired
    public FuenteController(FuenteService fuenteService) {
        this.fuenteService = fuenteService;
    }

    @GetMapping
    public ResponseEntity<?> getFuentes(@RequestParam(required = false) String name, Pageable pageable) {
        if (name != null) {
            return new ResponseEntity<>(fuenteService.findByName(name, pageable), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(fuenteService.findAll(pageable), HttpStatus.OK);
        }
    }

    @PostMapping("/crear")
    public ResponseEntity<?> postFuente(@RequestBody FuenteDto fuenteDto) {
        return new ResponseEntity<>(fuenteService.postFuente(fuenteDto), HttpStatus.CREATED);
    }

    @PutMapping("/modificar")
    public ResponseEntity<?> putFuente(@RequestParam Long fuenteId, @RequestBody FuenteDto fuenteDto) {
        return new ResponseEntity<>(fuenteService.putFuente(fuenteId, fuenteDto), HttpStatus.OK);
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<?> deleteFuente(@RequestParam Long fuenteId) {
        return new ResponseEntity<>(fuenteService.deleteFuente(fuenteId), HttpStatus.OK);
    }
}
