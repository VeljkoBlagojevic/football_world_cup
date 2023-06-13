package rs.ac.bg.fon.football_world_cup.seed;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import rs.ac.bg.fon.football_world_cup.repository.ReprezentacijaRepository;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class CountryCodeSeeder {

    private final ReprezentacijaRepository reprezentacijaRepository;

    public void seedCountryCodes() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://public.opendatasoft.com/api/records/1.0/search/?dataset=countries-codes&q={countryName}";
        Map<String, String> uriVariables = new HashMap<>();
        reprezentacijaRepository.findAll()
                .stream()
                .filter(reprezentacija -> !reprezentacija.getNaziv().equals("England"))
                .filter(reprezentacija -> !reprezentacija.getNaziv().equals("Wales"))
                .forEach(reprezentacija -> {
                    if (!reprezentacija.getDvoslovniNaziv().isBlank()) {
                        return;
                    }
                    uriVariables.put("countryName", reprezentacija.getNaziv());
                    ResponseEntity<Response> response = restTemplate.getForEntity(url, Response.class, uriVariables);
                    reprezentacija.setDvoslovniNaziv(response.getBody().getRecords().get(0).getFields().getIso2_code());
                    reprezentacijaRepository.save(reprezentacija);
                });


    }

}
