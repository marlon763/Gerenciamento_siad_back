package br.com.siad.api.repository;

import br.com.siad.api.models.Filial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface FiliaisRepository extends JpaRepository<Filial, String> {
   Filial findByName(String name);
}
