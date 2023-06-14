package rs.ac.bg.fon.football_world_cup.seed;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import rs.ac.bg.fon.football_world_cup.seed.stadion.AbstractStadionSeeder;
import rs.ac.bg.fon.football_world_cup.seed.tombola.Tombola;
import rs.ac.bg.fon.football_world_cup.seed.tombola.http.CountryCodeSeeder;
import rs.ac.bg.fon.football_world_cup.seed.tombola.http.CountryFlagsSeeder;
import rs.ac.bg.fon.football_world_cup.seed.tombola.http.GrupaIReprezentacijaSeeder;

@Component
public class Seeder {

    private final AbstractStadionSeeder stadionSeeder;
    private final GrupaIReprezentacijaSeeder grupaIReprezentacijaSeeder;
    private final CountryFlagsSeeder countryFlagsSeeder;
    private final CountryCodeSeeder countryCodeSeeder;
    private final StatistikaSeeder statistikaSeeder;
    private final Tombola tombola;

    public Seeder(@Qualifier("webScrapeStadionSeeder") AbstractStadionSeeder stadionSeeder
            , GrupaIReprezentacijaSeeder grupaIReprezentacijaSeeder,
                  CountryFlagsSeeder countryFlagsSeeder,
                  CountryCodeSeeder countryCodeSeeder,
                  StatistikaSeeder statistikaSeeder,
                  @Qualifier("tombolaHttpClient") Tombola tombola) {
        this.stadionSeeder = stadionSeeder;
        this.grupaIReprezentacijaSeeder = grupaIReprezentacijaSeeder;
        this.countryFlagsSeeder = countryFlagsSeeder;
        this.countryCodeSeeder = countryCodeSeeder;
        this.statistikaSeeder = statistikaSeeder;
        this.tombola = tombola;
    }

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        stadionSeeder.seedStadionData();
//        grupaIReprezentacijaSeeder.seedGrupeAndReprezentacijeData();
        tombola.izvlacenjeIzSesira();
//        countryCodeSeeder.seedCountryCodes();
//        countryFlagsSeeder.seedCountryFlags();
//        statistikaSeeder.seedStatistiika();
    }

}
