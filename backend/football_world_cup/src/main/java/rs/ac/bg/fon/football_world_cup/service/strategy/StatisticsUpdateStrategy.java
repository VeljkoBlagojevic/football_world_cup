package rs.ac.bg.fon.football_world_cup.service.strategy;

import rs.ac.bg.fon.football_world_cup.domain.StatistikaReprezentacije;

public interface StatisticsUpdateStrategy {
    void updateStatistiku(StatistikaReprezentacije statistikaReprezentacije, int brojDatihGolova, int brojPrimljenihGolova);
}
