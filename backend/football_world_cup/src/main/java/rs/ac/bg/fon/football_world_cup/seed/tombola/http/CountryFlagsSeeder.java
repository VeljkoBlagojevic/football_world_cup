package rs.ac.bg.fon.football_world_cup.seed.tombola.http;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import rs.ac.bg.fon.football_world_cup.repository.ReprezentacijaRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class CountryFlagsSeeder {

    private final ReprezentacijaRepository reprezentacijaRepository;

    public void seedCountryFlags() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://flagcdn.com/256x192/{countryCode}.png";
        Map<String, String> uriVariables = new HashMap<>();
        reprezentacijaRepository.findAll().stream()
                .filter(reprezentacija -> !reprezentacija.getNaziv().equals("England"))
                .filter(reprezentacija -> !reprezentacija.getNaziv().equals("Wales"))
                .forEach(reprezentacija -> {
                    reprezentacija.setZastava("https://flagcdn.com/256x192/" + reprezentacija.getDvoslovniNaziv().toLowerCase() + ".png");
                    reprezentacijaRepository.save(reprezentacija);
                    uriVariables.put("countryCode", reprezentacija.getDvoslovniNaziv().toLowerCase());
                    ResponseEntity<byte[]> response = restTemplate.getForEntity(url, byte[].class, uriVariables);
                    try {
                        Files.write(Paths.get("zastave/" + reprezentacija.getDvoslovniNaziv() + ".jpg"), response.getBody());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

}
