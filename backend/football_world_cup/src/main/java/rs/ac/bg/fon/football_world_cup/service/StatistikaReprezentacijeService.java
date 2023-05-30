package rs.ac.bg.fon.football_world_cup.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rs.ac.bg.fon.football_world_cup.domain.Reprezentacija;
import rs.ac.bg.fon.football_world_cup.domain.StatistikaReprezentacije;
import rs.ac.bg.fon.football_world_cup.repository.StatistikaReprezentacijeRepository;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class StatistikaReprezentacijeService {

    private final StatistikaReprezentacijeRepository statistikaReprezentacijeRepository;

    public StatistikaReprezentacije getByReprezentacijaId(Long reprezentacijaId) {
        return statistikaReprezentacijeRepository.findByReprezentacijaId(reprezentacijaId).orElseThrow(NoSuchElementException::new);
    }

    public StatistikaReprezentacije getByReprezentacija(Reprezentacija reprezentacija) {
        return statistikaReprezentacijeRepository.findByReprezentacijaId(reprezentacija.getId()).orElseThrow(NoSuchElementException::new);
    }

    public void save(StatistikaReprezentacije statistikaReprezentacije) {
        statistikaReprezentacijeRepository.save(statistikaReprezentacije);
    }
}
