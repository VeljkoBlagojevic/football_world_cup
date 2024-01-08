package rs.ac.bg.fon.football_world_cup.seed;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import rs.ac.bg.fon.football_world_cup.seed.stadion.AbstractStadionSeeder;
import rs.ac.bg.fon.football_world_cup.seed.tombola.Tombola;

@Component
public class Seeder {

    private final AbstractStadionSeeder stadionSeeder;
    private final Tombola tombola;

    public Seeder(@Qualifier("webScrapeStadionSeeder") AbstractStadionSeeder stadionSeeder,
                  @Qualifier("tombolaHttpClient") Tombola tombola) {
        this.stadionSeeder = stadionSeeder;
        this.tombola = tombola;
    }

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        stadionSeeder.seedStadionData();
        tombola.izvlacenjeIzSesira();
    }

}
