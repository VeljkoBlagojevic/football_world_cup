package rs.ac.bg.fon.football_world_cup.seed.tombola.http;

import org.springframework.stereotype.Component;
import rs.ac.bg.fon.football_world_cup.domain.Reprezentacija;

@Component
public class TeamMapper {

    public Reprezentacija onlineToDomain(Team team) {
        return Reprezentacija.builder()
                .naziv(team.getAlternateName())
                .troslovniNaziv(team.getCountry())
                .build();
    }

}
