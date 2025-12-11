package br.com.siad.api.security;

import br.com.siad.api.enums.Roles;
import br.com.siad.api.models.Role;
import br.com.siad.api.models.User;
import br.com.siad.api.repository.RoleRepository;
import br.com.siad.api.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Set;

@Configuration
public class AdminConf implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        var roleAdmin = roleRepository.findByName(Roles.ADMIN)
                .orElseGet(() -> {
                    var role = new Role();
                    role.setName(Roles.ADMIN);

                    return roleRepository.save(role);
                });

        var userAdmin = userRepository.findByEmail("admin@admin.com");

        userAdmin.ifPresentOrElse (
                user -> {
                    System.out.println("✓ Usuário admin já existe");
                },
                () -> {

                    var newUser = new User();
                    newUser.setName("Admin");
                    newUser.setEmail("admin@admin.com");
                    newUser.setPassword(passwordEncoder.encode("siad3797"));
                    newUser.setRoles(Set.of(roleAdmin));

                    userRepository.save(newUser);

                }
        );

    }

}
