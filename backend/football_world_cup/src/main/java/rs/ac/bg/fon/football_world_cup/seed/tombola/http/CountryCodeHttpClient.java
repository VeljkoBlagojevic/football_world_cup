package rs.ac.bg.fon.football_world_cup.seed.tombola.http;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import rs.ac.bg.fon.football_world_cup.domain.Reprezentacija;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class CountryCodeHttpClient {

    public void seedCountryCodes(List<Reprezentacija> reprezentacije) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://public.opendatasoft.com/api/records/1.0/search/?dataset=countries-codes&q={countryName}";
        Map<String, String> uriVariables = new HashMap<>();
        reprezentacije
                .stream()
                .filter(reprezentacija -> !reprezentacija.getNaziv().equals("England"))
                .filter(reprezentacija -> !reprezentacija.getNaziv().equals("Wales"))
                .forEach(reprezentacija -> {
                    if (reprezentacija.getDvoslovniNaziv() != null && !reprezentacija.getDvoslovniNaziv().isBlank()) {
                        return;
                    }
                    uriVariables.put("countryName", reprezentacija.getNaziv());
                    ResponseEntity<Response> response = restTemplate.getForEntity(url, Response.class, uriVariables);
                    reprezentacija.setDvoslovniNaziv(response.getBody().getRecords().get(0).getFields().getIso2_code());
                });
    }

}
