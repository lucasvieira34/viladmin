package br.com.lucasteixeira.vilacontrol.repository;

import br.com.lucasteixeira.vilacontrol.models.Contas;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ContasRepository extends JpaRepository<Contas, Long> {

    List<Contas> findById(long id);

    // LISTA DE CONTAS ORDENADAS DESCRESCENTE PELO VALOR
    List<Contas> findAllByOrderByValorDesc();

    // LISTA DE CONTAS ORDENADO PELA RESIDÊNCIA
    @Query(value = "SELECT * FROM Contas order by id_residencia", nativeQuery = true)
    List<Contas> findAllByResidencia();

    // LISTA DE CONTAS PELO ANO
    @Query(value = "SELECT * FROM Contas WHERE extract (year from pagamento) = :ano AND id_residencia = :id_residencia", nativeQuery = true)
    List<Contas> findByYear(@Param("ano") Integer ano, @Param("id_residencia") long id_residencia);

}
