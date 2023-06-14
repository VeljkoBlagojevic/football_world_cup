package rs.ac.bg.fon.football_world_cup.seed.tombola.http;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import rs.ac.bg.fon.football_world_cup.domain.Grupa;
import rs.ac.bg.fon.football_world_cup.repository.GrupaRepository;
import rs.ac.bg.fon.football_world_cup.repository.ReprezentacijaRepository;
import rs.ac.bg.fon.football_world_cup.repository.StatistikaReprezentacijeRepository;
import rs.ac.bg.fon.football_world_cup.seed.tombola.Tombola;

import java.util.Arrays;
import java.util.List;

@Component
public class TombolaHttpClient extends Tombola {
    private final GroupMapper groupMapper;
    private final CountryCodeSeederV2 countryCodeSeederV2;

    private final CountryFlagsSeederV2 countryFlagsSeederV2;
    public TombolaHttpClient(GrupaRepository grupaRepository, ReprezentacijaRepository reprezentacijaRepository, StatistikaReprezentacijeRepository statistikaReprezentacijeRepository, GroupMapper groupMapper, CountryCodeSeederV2 countryCodeSeederV2, CountryFlagsSeederV2 countryFlagsSeederV2) {
        super(grupaRepository, reprezentacijaRepository, statistikaReprezentacijeRepository);
        this.groupMapper = groupMapper;
        this.countryCodeSeederV2 = countryCodeSeederV2;
        this.countryFlagsSeederV2 = countryFlagsSeederV2;
    }
    @Override
    protected List<Grupa> getGrupe() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://copa22.medeiro.tech/groups";
        ResponseEntity<Group[]> groups = restTemplate.getForEntity(url, Group[].class);
        return Arrays.stream(groups.getBody())
                .map(groupMapper::onlineToDomain)
                .peek(grupa -> countryCodeSeederV2.seedCountryCodes(grupa.getReprezentacije()))
                .peek(grupa -> countryFlagsSeederV2.seedCountryFlags(grupa.getReprezentacije()))
                .toList();
    }
}
