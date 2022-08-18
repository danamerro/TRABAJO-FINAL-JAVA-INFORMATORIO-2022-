package com.trabajofinalinfo.periodicoDM.controller;

import com.trabajofinalinfo.periodicoDM.model.dto.AutorDto;
import com.trabajofinalinfo.periodicoDM.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/autor")
public class AutorController {

    private final AutorService autorService;

    @Autowired
    public AutorController(AutorService autorService) {
        this.autorService = autorService;
    }

    @GetMapping
    public ResponseEntity<?> getAutor(@RequestParam(name = "fullname", required = false) String fullname,
                                                 @RequestParam(name = "date", required = false)
                                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                                 Pageable pageable) {
        if (fullname != null && date == null) {
            return new ResponseEntity<>(autorService.findByFullname(fullname, pageable), HttpStatus.OK);
        } else if (date != null && fullname == null) {
            return new ResponseEntity<>(autorService.findByCreatedAfterDate(date, pageable), HttpStatus.OK);
        } else if (fullname != null && date != null){
            return new ResponseEntity<>(autorService.findByFullnameAndDateAfter(fullname, date, pageable),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>(autorService.findAll(pageable),HttpStatus.OK);
        }
    }

    @PostMapping("/crear")
    public ResponseEntity<?> postAutor(@RequestBody AutorDto autorDto) {
        return new ResponseEntity<>(autorService.postAutor(autorDto), HttpStatus.CREATED);
    }

    @PutMapping("/modificar")
    public ResponseEntity<?> putAutor(@RequestParam Long autorId, @RequestBody @Valid AutorDto autorDto) {
        return new ResponseEntity<>(autorService.putAutor(autorId, autorDto), HttpStatus.OK);
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<?> deleteAutor(@RequestParam Long autorId) {
        return new ResponseEntity<>(autorService.deleteAutor(autorId), HttpStatus.OK);
    }
}
