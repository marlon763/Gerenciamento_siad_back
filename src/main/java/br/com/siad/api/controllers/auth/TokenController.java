package br.com.siad.api.controllers.auth;

import br.com.siad.api.DTO.user.LoginRequest;
import br.com.siad.api.DTO.user.LoginResponse;
import br.com.siad.api.DTO.user.UserResponse;
import br.com.siad.api.enums.Roles;
import br.com.siad.api.models.Role;
import br.com.siad.api.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class TokenController {

    @Autowired
    private final JwtEncoder jwtEncoder;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login (@RequestBody @Valid LoginRequest data) throws Exception{

        var user = userRepository.findByEmail(data.email());

        if (user.isEmpty() || user.get().isLoginCorrect(data , bCryptPasswordEncoder)) {

            throw new BadCredentialsException("Usuario ou senha invalido !");

        }

        var now = Instant.now();
        var expriresIn = 300L;

        var claims = JwtClaimsSet.builder()
                .issuer("SIAD")
                .subject(user.get().getId())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expriresIn))
                .build();

        var jwtToken = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        List<Roles> roleNames = user.get().getRoles()
                .stream()
                .map(Role::getName)
                .toList();

        var userResponse = new UserResponse(
                user.get().getId(),
                user.get().getName(),
                user.get().getEmail(),
                roleNames
        );

        return  ResponseEntity.ok(new LoginResponse(userResponse , jwtToken, expriresIn));

    }

}
