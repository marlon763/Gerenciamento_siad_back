package br.com.siad.api.repository;

import br.com.siad.api.models.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    Funcionario findByCpf(String cpf);
}
