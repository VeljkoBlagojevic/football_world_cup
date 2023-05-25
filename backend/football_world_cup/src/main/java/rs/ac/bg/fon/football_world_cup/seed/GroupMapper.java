package rs.ac.bg.fon.football_world_cup.seed;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import rs.ac.bg.fon.football_world_cup.domain.Grupa;
import rs.ac.bg.fon.football_world_cup.domain.Reprezentacija;
import rs.ac.bg.fon.football_world_cup.domain.StatistikaReprezentacije;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GroupMapper {

    private final TeamMapper teamMapper;

    public Grupa onlineToDomain(Group group) {

        List<Reprezentacija> reprezentacije = group.getTeams().stream().map(teamMapper::onlineToDomain).toList();

        return Grupa.builder()
                .naziv(group.getCode())
                .reprezentacije(reprezentacije)
                .statistikeReprezentacija(
                        reprezentacije.stream()
                                .map(reprezentacija -> StatistikaReprezentacije.builder().reprezentacija(reprezentacija).build())
                                .toList())
                .build();
    }
}
