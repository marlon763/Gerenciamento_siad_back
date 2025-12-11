package br.com.siad.api.repository;

import br.com.siad.api.models.Filial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FiliaisRepository extends JpaRepository<Filial, String> {
   Filial findByName(String name);
}
