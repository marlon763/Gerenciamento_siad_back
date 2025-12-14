package br.com.siad.api.controllers;

import br.com.siad.api.DTO.error.BadReqDTO;
import br.com.siad.api.DTO.funcionarios.FuncionarioDTO;
import br.com.siad.api.models.Filial;
import br.com.siad.api.models.Funcionario;
import br.com.siad.api.repository.FiliaisRepository;
import br.com.siad.api.repository.FuncionarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/funcionarios")
public class FuncionariosController {

    @Autowired
    private FiliaisRepository filiaisRepository;

    @Autowired
    FuncionarioRepository funcionarioRepository;

    @PostMapping("/cadastrar")
    public ResponseEntity cadastrar (@RequestBody @Valid FuncionarioDTO data) {

        var existFunc = funcionarioRepository.findByCpf(data.cpf());

        if (existFunc != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BadReqDTO("Funcionário já cadastrado !"));
        }

        var existFilial = filiaisRepository.findById(data.id_filial()).orElse(null);

        if (existFilial == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BadReqDTO("Filial inexistente !"));
        }

        //cria Set com a filial
        Set<Filial> filiais = new HashSet<>();
        filiais.add(existFilial);

        Funcionario newFunc = new Funcionario(data.name(), data.cpf(), filiais);

        funcionarioRepository.save(newFunc);

        //vincula o funcionario a filial
        existFilial.getFuncionarios().add(newFunc);
        filiaisRepository.save(existFilial);

        return ResponseEntity.status(HttpStatus.CREATED).body(newFunc);

    }

}
