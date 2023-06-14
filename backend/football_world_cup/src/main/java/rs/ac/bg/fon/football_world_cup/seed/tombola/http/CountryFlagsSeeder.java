package rs.ac.bg.fon.football_world_cup.seed.tombola.http;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import rs.ac.bg.fon.football_world_cup.domain.Reprezentacija;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class CountryFlagsSeeder {

    public void seedCountryFlags(List<Reprezentacija> reprezentacije) {
        Map<String, String> uriVariables = new HashMap<>();
        reprezentacije.stream()
                .filter(reprezentacija -> !reprezentacija.getNaziv().equals("England"))
                .filter(reprezentacija -> !reprezentacija.getNaziv().equals("Wales"))
                .forEach(reprezentacija -> {
                    reprezentacija.setZastava("https://flagcdn.com/256x192/" + reprezentacija.getDvoslovniNaziv().toLowerCase() + ".png");
                    uriVariables.put("countryCode", reprezentacija.getDvoslovniNaziv().toLowerCase());
                });
    }

}
