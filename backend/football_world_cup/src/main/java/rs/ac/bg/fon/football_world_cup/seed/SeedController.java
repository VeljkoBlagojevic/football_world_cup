package rs.ac.bg.fon.football_world_cup.seed;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/seed")
@RequiredArgsConstructor
public class SeedController {

    private final StadionSeeder stadionSeeder;
    private final GrupaIReprezentacijaSeeder grupaIReprezentacijaSeeder;

    @GetMapping
    public void seed() {
        stadionSeeder.seedStadionData();
        grupaIReprezentacijaSeeder.seedGrupeAndReprezentacijeData();
    }
}
