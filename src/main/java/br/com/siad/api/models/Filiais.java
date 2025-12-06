package br.com.siad.api.models;
import jakarta.persistence.*;

@Entity
public class Filiais {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private String nome_empresa;

    @Column(nullable = false)
    private String descricao_empresa;

}
