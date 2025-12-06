package br.com.siad.api.controllers;

import br.com.siad.api.models.LoginResponseDTO;
import br.com.siad.api.models.RegisterDTO;
import br.com.siad.api.models.Users;
import br.com.siad.api.repository.UserRepository;
import br.com.siad.api.security.TokenService;
import jakarta.validation.Valid;
import br.com.siad.api.models.AuthenticationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository repository;

    @Autowired
    TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody  @Valid AuthenticationDTO data){

        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((Users) auth.getPrincipal());
        Users user = (Users) auth.getPrincipal();

        return ResponseEntity.ok(new LoginResponseDTO(
                user.getName(),
                user.getEmail(),
                user.getRoles(),
                token
        ));
    }

    @PostMapping("/cadastrar")
    public ResponseEntity register(@RequestBody  @Valid RegisterDTO data){
        System.out.println(data);

        if(this.repository.findByEmail(data.email())!=null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());

        Users newUser = new Users(data.name(), data.email(), encryptedPassword,data.role());

        this.repository.save(newUser);

        return ResponseEntity.ok().build();
    }

}
