package rs.ac.bg.fon.football_world_cup.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rs.ac.bg.fon.football_world_cup.domain.Grupa;
import rs.ac.bg.fon.football_world_cup.domain.Reprezentacija;
import rs.ac.bg.fon.football_world_cup.domain.StatistikaReprezentacije;
import rs.ac.bg.fon.football_world_cup.repository.GrupaRepository;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class GrupaService {

    private final GrupaRepository grupaRepository;

    public List<Grupa> getAll() {
        return grupaRepository.findAll().stream()
                .peek(grupa -> grupa.getStatistikeReprezentacija()
                        .sort(Comparator
                                .comparing(StatistikaReprezentacije::getBrojOsvojenihPoena).reversed()
                                .thenComparingInt(s -> (s.getBrojPrimljenihGolova() - s.getBrojDatihGolova()))))
                .toList();
    }

    public Grupa getById(Long id) {
        Grupa grupa = grupaRepository.findById(id).orElseThrow(NoSuchElementException::new);
        grupa.getStatistikeReprezentacija().
                sort(Comparator
                        .comparing(StatistikaReprezentacije::getBrojOsvojenihPoena).reversed()
                        .thenComparingInt(s -> (s.getBrojPrimljenihGolova() - s.getBrojDatihGolova())));
        return grupa;
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
