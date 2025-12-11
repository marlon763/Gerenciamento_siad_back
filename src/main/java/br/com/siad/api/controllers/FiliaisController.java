package br.com.siad.api.controllers;

import br.com.siad.api.models.Filial;
import jakarta.validation.Valid;
import br.com.siad.api.DTO.filiais.RegisterFilialDTO;
import br.com.siad.api.repository.FiliaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/filiais")
public class FiliaisController {

    @Autowired
    private FiliaisRepository filiaisRepository;

    @GetMapping("/listar")
    public ResponseEntity listar () {
        var lista = filiaisRepository.findAll();
        return ResponseEntity.ok(lista);
    };

    @PostMapping("/cadastrar")
    public ResponseEntity cadastrar (@RequestBody @Valid RegisterFilialDTO data) {

        var existFilial = filiaisRepository.findByName(data.name());

        if (existFilial != null) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Filial já cadastrada com esse nome !");

        }

        Filial newFilial = new Filial(data);

        filiaisRepository.save(newFilial);

        return ResponseEntity.status(HttpStatus.CREATED).body(newFilial);

    };

}
