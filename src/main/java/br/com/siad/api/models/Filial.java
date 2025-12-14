package br.com.siad.api.models;

import br.com.siad.api.DTO.filiais.RegisterFilialDTO;
import br.com.siad.api.enums.FilialTypes;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.Set;

@Table(name = "filiais")
@Entity(name = "filiais")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Filial {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private FilialTypes type;

    @CreationTimestamp
    private Instant timestamp;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "filial_func",
            joinColumns = @JoinColumn(name = "filial_id"),
            inverseJoinColumns = @JoinColumn(name = "func_id")
    )
    private Set<Funcionario> funcionarios;


    public Filial(RegisterFilialDTO data) {
        this.name = data.name();
        this.type = data.type();
    }

}
