package rs.ac.bg.fon.football_world_cup.seed.tombola.http;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import rs.ac.bg.fon.football_world_cup.domain.Reprezentacija;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CountryFlagsSeeder {

    public void seedCountryFlags(List<Reprezentacija> reprezentacije) {
        reprezentacije.stream()
                .filter(reprezentacija -> !reprezentacija.getNaziv().equals("England"))
                .filter(reprezentacija -> !reprezentacija.getNaziv().equals("Wales"))
                .forEach(reprezentacija -> reprezentacija.setZastava("https://flagcdn.com/256x192/" + reprezentacija.getDvoslovniNaziv().toLowerCase() + ".png"));
    }

}
