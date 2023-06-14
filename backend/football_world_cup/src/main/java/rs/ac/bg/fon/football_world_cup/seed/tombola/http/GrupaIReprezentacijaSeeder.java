package rs.ac.bg.fon.football_world_cup.seed.tombola.http;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import rs.ac.bg.fon.football_world_cup.domain.Grupa;
import rs.ac.bg.fon.football_world_cup.repository.GrupaRepository;
import rs.ac.bg.fon.football_world_cup.repository.ReprezentacijaRepository;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Component
@RequiredArgsConstructor
public class GrupaIReprezentacijaSeeder {

    private final ReprezentacijaRepository reprezentacijaRepository;
    private final GrupaRepository grupaRepository;
    private final GroupMapper groupMapper;

    public void seedGrupeAndReprezentacijeData() {
        if (reprezentacijaRepository.count() != 0 || grupaRepository.count() != 0) {
            return;
        }
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://copa22.medeiro.tech/groups";
        ResponseEntity<Group[]> groups = restTemplate.getForEntity(url, Group[].class);
        List<Grupa> grupe = Arrays.stream(groups.getBody())
                .map(groupMapper::onlineToDomain)
                .toList();
        reprezentacijaRepository.saveAll(grupe.stream()
                .map(Grupa::getReprezentacije)
                .flatMap(Collection::stream)
                .toList());
        grupaRepository.saveAll(grupe);
    }
}
