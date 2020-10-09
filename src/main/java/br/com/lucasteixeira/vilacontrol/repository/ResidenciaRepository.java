package br.com.lucasteixeira.vilacontrol.repository;

import br.com.lucasteixeira.vilacontrol.models.Residencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResidenciaRepository extends JpaRepository<Residencia, Long> {
}
