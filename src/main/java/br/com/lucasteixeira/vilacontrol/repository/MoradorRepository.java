package br.com.lucasteixeira.vilacontrol.repository;

import br.com.lucasteixeira.vilacontrol.models.Morador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoradorRepository extends JpaRepository<Morador,Long> {
}
