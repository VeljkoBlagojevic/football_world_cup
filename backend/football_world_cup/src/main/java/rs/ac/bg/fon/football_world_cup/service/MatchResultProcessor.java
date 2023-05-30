package rs.ac.bg.fon.football_world_cup.service;

import org.springframework.stereotype.Service;
import rs.ac.bg.fon.football_world_cup.domain.Utakmica;
import rs.ac.bg.fon.football_world_cup.service.strategy.MatchResultMapper;
import rs.ac.bg.fon.football_world_cup.service.strategy.Rezultat;

@Service
public class MatchResultProcessor {
    private final StatistikaReprezentacijeService statistikaReprezentacijeService;

    private final MatchResultMapper matchResultMapper;

    public MatchResultProcessor(StatistikaReprezentacijeService statistikaReprezentacijeService, MatchResultMapper matchResultMapper) {
        this.statistikaReprezentacijeService = statistikaReprezentacijeService;
        this.matchResultMapper = matchResultMapper;
    }

    public Rezultat mapRezultatUtakmice(int brojDatihGolova, int brojPrimljenihGolova) {
        return switch (Integer.compare(brojDatihGolova, brojPrimljenihGolova)) {
            case 1 -> Rezultat.POBEDA;
            case -1 -> Rezultat.PORAZ;
            case 0 -> Rezultat.NERESENO;
            default ->
                    throw new IllegalStateException("Unexpected value: " + Integer.compare(brojDatihGolova, brojPrimljenihGolova));
        };
    }

    public void processRezultatUtakmice(Utakmica utakmica) {
        switch (utakmica.getEvidencijaUtakmice().getPredaja()) {
            case PREDAO_GOST -> {
                matchResultMapper.getStrategy(Rezultat.POBEDA)
                        .updateStatistiku(
                                statistikaReprezentacijeService.getByReprezentacija(utakmica.getDomacin()),
                                0,
                                0);
                matchResultMapper.getStrategy(Rezultat.PORAZ)
                        .updateStatistiku(
                                statistikaReprezentacijeService.getByReprezentacija(utakmica.getGost()),
                                0,
                                0);
            }
            case PREDAO_DOMACIN -> {
                matchResultMapper.getStrategy(Rezultat.PORAZ)
                        .updateStatistiku(
                                statistikaReprezentacijeService.getByReprezentacija(utakmica.getDomacin()),
                                0,
                                0);
                matchResultMapper.getStrategy(Rezultat.POBEDA)
                        .updateStatistiku(
                                statistikaReprezentacijeService.getByReprezentacija(utakmica.getGost()),
                                0,
                                0);
            }
            case NEMA_PREDAJE -> {
                matchResultMapper.getStrategy(
                                mapRezultatUtakmice(utakmica.getEvidencijaUtakmice().getBrojGolovaDomacina(), utakmica.getEvidencijaUtakmice().getBrojGolovaGosta()))
                        .updateStatistiku(statistikaReprezentacijeService.getByReprezentacija(
                                        utakmica.getDomacin()),
                                utakmica.getEvidencijaUtakmice().getBrojGolovaDomacina(),
                                utakmica.getEvidencijaUtakmice().getBrojGolovaGosta());
                matchResultMapper.getStrategy(
                                mapRezultatUtakmice(utakmica.getEvidencijaUtakmice().getBrojGolovaGosta(), utakmica.getEvidencijaUtakmice().getBrojGolovaDomacina()))
                        .updateStatistiku(statistikaReprezentacijeService.getByReprezentacija(
                                        utakmica.getGost()),
                                utakmica.getEvidencijaUtakmice().getBrojGolovaGosta(),
                                utakmica.getEvidencijaUtakmice().getBrojGolovaDomacina());
            }
        }
        statistikaReprezentacijeService.save(statistikaReprezentacijeService.getByReprezentacija(utakmica.getDomacin()));
        statistikaReprezentacijeService.save(statistikaReprezentacijeService.getByReprezentacija(utakmica.getGost()));
    }

}
