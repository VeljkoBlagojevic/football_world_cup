package rs.ac.bg.fon.football_world_cup.service.strategy;

import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.Map;

@Component
public class MatchResultMapper {

    private final Map<Rezultat, StatisticsUpdateStrategy> strategyMap;

    public MatchResultMapper() {
        strategyMap = new EnumMap<>(Rezultat.class);
        strategyMap.put(Rezultat.POBEDA, new PobedaUpdateStrategy());
        strategyMap.put(Rezultat.PORAZ, new PorazUpdateStrategy());
        strategyMap.put(Rezultat.NERESENO, new NeresenoUpdateStrategy());
    }

    public StatisticsUpdateStrategy getStrategy(Rezultat rezultat) {
        return strategyMap.get(rezultat);
    }


}
