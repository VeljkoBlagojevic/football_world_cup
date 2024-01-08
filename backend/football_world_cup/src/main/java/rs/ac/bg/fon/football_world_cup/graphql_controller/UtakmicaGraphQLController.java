package rs.ac.bg.fon.football_world_cup.graphql_controller;

import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import rs.ac.bg.fon.football_world_cup.domain.Reprezentacija;
import rs.ac.bg.fon.football_world_cup.domain.Stadion;
import rs.ac.bg.fon.football_world_cup.domain.Termin;
import rs.ac.bg.fon.football_world_cup.domain.Utakmica;
import rs.ac.bg.fon.football_world_cup.service.UtakmicaService;


@Controller
@RequiredArgsConstructor
@CrossOrigin
public class UtakmicaGraphQLController {

    private final UtakmicaService utakmicaService;

    @MutationMapping
    public Utakmica zakaziUtakmicu(@Argument UtakmicaInput utakmicaInput) {
        Utakmica utakmica = Utakmica.builder()
                .domacin(Reprezentacija.builder().id(utakmicaInput.domacinId()).build())
                .gost(Reprezentacija.builder().id(utakmicaInput.gostId()).build())
                .stadion(Stadion.builder().id(utakmicaInput.stadionId()).build())
                .termin(utakmicaInput.terminInput())
                .odigrana(utakmicaInput.odigrana())
                .build();
        return utakmicaService.zakaziUtakmicu(utakmica);
    }
}

record UtakmicaInput(Long domacinId, Long gostId, Long stadionId, Termin terminInput, Boolean odigrana) {}
