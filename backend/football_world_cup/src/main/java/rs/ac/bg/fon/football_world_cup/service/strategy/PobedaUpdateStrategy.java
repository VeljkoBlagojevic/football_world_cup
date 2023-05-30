package rs.ac.bg.fon.football_world_cup.service.strategy;

import org.springframework.stereotype.Component;
import rs.ac.bg.fon.football_world_cup.domain.StatistikaReprezentacije;

@Component
public class PobedaUpdateStrategy implements StatisticsUpdateStrategy {
    private static final int POENI_ZA_POBEDU = 3;

    @Override
    public void updateStatistiku(StatistikaReprezentacije statistikaReprezentacije, int brojDatihGolova, int brojPrimljenihGolova) {
        statistikaReprezentacije.incrementBrojOdigranihUtakmica();
        statistikaReprezentacije.incrementBrojPobedjenihUtakmica();
        statistikaReprezentacije.incrementBrojDatihGolova(brojDatihGolova);
        statistikaReprezentacije.incrementBrojPrimljenihGolova(brojPrimljenihGolova);
        statistikaReprezentacije.incrementBrojOsvojenihPoena(POENI_ZA_POBEDU);
    }
}
