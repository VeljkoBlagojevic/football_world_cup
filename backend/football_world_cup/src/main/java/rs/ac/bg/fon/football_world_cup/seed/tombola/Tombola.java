package rs.ac.bg.fon.football_world_cup.seed.tombola;


import lombok.RequiredArgsConstructor;
import rs.ac.bg.fon.football_world_cup.domain.Grupa;
import rs.ac.bg.fon.football_world_cup.repository.GrupaRepository;
import rs.ac.bg.fon.football_world_cup.repository.ReprezentacijaRepository;
import rs.ac.bg.fon.football_world_cup.repository.StatistikaReprezentacijeRepository;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public abstract class Tombola {

    private final GrupaRepository grupaRepository;
    private final ReprezentacijaRepository reprezentacijaRepository;
    private final StatistikaReprezentacijeRepository statistikaReprezentacijeRepository;

    public void izvlacenjeIzSesira() {
        if (grupaRepository.count() != 0){
            return;
        }
        List<Grupa> grupe = getGrupe();
        reprezentacijaRepository.saveAll(grupe.stream().map(Grupa::getReprezentacije).flatMap(Collection::stream).toList());
        statistikaReprezentacijeRepository.saveAll(grupe.stream().map(Grupa::getStatistikeReprezentacija).flatMap(Collection::stream).toList());
        grupaRepository.saveAll(grupe);
    }

    protected abstract List<Grupa> getGrupe();
}
