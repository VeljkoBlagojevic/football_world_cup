package rs.ac.bg.fon.football_world_cup.graphql_controller;

import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import rs.ac.bg.fon.football_world_cup.domain.Reprezentacija;
import rs.ac.bg.fon.football_world_cup.service.ReprezentacijaService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@CrossOrigin
public class ReprezentacijaGraphQLController {

    private final ReprezentacijaService reprezentacijaService;

    @QueryMapping
    public List<Reprezentacija> getAllReprezentacije() {
        return reprezentacijaService.getAll();
    }

    @QueryMapping
    public Reprezentacija getReprezentacija(@Argument Long id) {
        return reprezentacijaService.getById(id);
    }
}
