package rs.ac.bg.fon.football_world_cup.seed;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Seeder {

    private final StadionSeeder stadionSeeder;
    private final GrupaIReprezentacijaSeeder grupaIReprezentacijaSeeder;
    private final CountryFlagsSeeder countryFlagsSeeder;
    private final CountryCodeSeeder countryCodeSeeder;
    private final StatistikaSeeder statistikaSeeder;

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        stadionSeeder.seedStadionData();
        grupaIReprezentacijaSeeder.seedGrupeAndReprezentacijeData();
        countryCodeSeeder.seedCountryCodes();
        countryFlagsSeeder.seedCountryFlags();
        statistikaSeeder.seedStatistiika();
    }

}
