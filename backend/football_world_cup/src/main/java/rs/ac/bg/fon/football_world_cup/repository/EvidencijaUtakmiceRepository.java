package rs.ac.bg.fon.football_world_cup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.bg.fon.football_world_cup.domain.EvidencijaUtakmice;

@Repository
public interface EvidencijaUtakmiceRepository extends JpaRepository<EvidencijaUtakmice, Long> {
}
