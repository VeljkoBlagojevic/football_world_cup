package rs.ac.bg.fon.football_world_cup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.bg.fon.football_world_cup.domain.StatistikaReprezentacije;

import java.util.Optional;

@Repository
public interface StatistikaReprezentacijeRepository extends JpaRepository<StatistikaReprezentacije, Long> {
    Optional<StatistikaReprezentacije> findByReprezentacijaId(Long id);
}
