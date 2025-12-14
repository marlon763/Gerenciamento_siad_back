package br.com.siad.api.models;

import br.com.siad.api.DTO.funcionarios.FuncionarioDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Table(name = "funcionarios")
@Entity(name = "funcionarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String cpf;

    @CreationTimestamp
    private Instant timestamp;

    @ManyToMany(mappedBy = "funcionarios")
    @JsonIgnore  // Evita serialização circular
    private Set<Filial> filiais = new HashSet<>();

    public Funcionario(String name, String cpf, Set<Filial> filiais) {
        this.name = name;
        this.cpf = cpf;
        this.filiais = filiais;
    }
}
