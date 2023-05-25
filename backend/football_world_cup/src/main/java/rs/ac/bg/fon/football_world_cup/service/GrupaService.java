package rs.ac.bg.fon.football_world_cup.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rs.ac.bg.fon.football_world_cup.domain.Grupa;
import rs.ac.bg.fon.football_world_cup.domain.Reprezentacija;
import rs.ac.bg.fon.football_world_cup.repository.GrupaRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class GrupaService {

    private final GrupaRepository grupaRepository;

    public List<Grupa> getAll() {
        return grupaRepository.findAll();
    }

    public Grupa getById(Long id) {
        return grupaRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public boolean izIsteGrupe(Reprezentacija domacin, Reprezentacija gost) {
        Grupa grupaDomacina = grupaRepository.findByReprezentacije(domacin).orElseThrow(NoSuchElementException::new);
        Grupa grupaGosta = grupaRepository.findByReprezentacije(gost).orElseThrow(NoSuchElementException::new);
        return grupaDomacina.equals(grupaGosta);
    }

    public List<Reprezentacija> getReprezentacijeIzGrupe(Long grupaId) {
        return getById(grupaId).getReprezentacije();
    }
}
