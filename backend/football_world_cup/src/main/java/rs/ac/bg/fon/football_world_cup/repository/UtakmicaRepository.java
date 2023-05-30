package rs.ac.bg.fon.football_world_cup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.bg.fon.football_world_cup.domain.Utakmica;

import java.util.List;

@Repository
public interface UtakmicaRepository extends JpaRepository<Utakmica, Long> {
    List<Utakmica> findByOdigrana(boolean odigrana);

    List<Utakmica> findByDomacinIdOrGostId(Long domacinId, Long gostId);

    List<Utakmica> findByStadionId(Long stadionId);

}
