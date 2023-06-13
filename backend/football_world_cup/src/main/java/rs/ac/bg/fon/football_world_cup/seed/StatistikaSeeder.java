package rs.ac.bg.fon.football_world_cup.seed;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import rs.ac.bg.fon.football_world_cup.domain.Grupa;
import rs.ac.bg.fon.football_world_cup.domain.Reprezentacija;
import rs.ac.bg.fon.football_world_cup.domain.StatistikaReprezentacije;
import rs.ac.bg.fon.football_world_cup.repository.GrupaRepository;
import rs.ac.bg.fon.football_world_cup.repository.StatistikaReprezentacijeRepository;

@Component
@RequiredArgsConstructor
public class StatistikaSeeder {

    private final StatistikaReprezentacijeRepository statistikaReprezentacijeRepository;
    private final GrupaRepository grupaRepository;

    public void seedStatistiika() {
        if(grupaRepository.findAll().stream().noneMatch(grupa -> grupa.getStatistikeReprezentacija().isEmpty())) {
            return;
        }
        for (Grupa grupa : grupaRepository.findAll()) {
            for (Reprezentacija reprezentacija : grupa.getReprezentacije()) {
                StatistikaReprezentacije statistikaReprezentacije = StatistikaReprezentacije.builder()
                        .reprezentacija(reprezentacija)
                        .brojDatihGolova(0)
                        .brojIzgubljenihUtakmica(0)
                        .brojNeresenihUtakmica(0)
                        .brojOdigranihUtakmica(0)
                        .brojOsvojenihPoena(0)
                        .brojPobedjenihUtakmica(0)
                        .brojPrimljenihGolova(0)
                        .build();
                statistikaReprezentacijeRepository.save(statistikaReprezentacije);
                grupa.addStatistikaReprezentacija(statistikaReprezentacije);
            }
            grupaRepository.save(grupa);
        }
    }

}
