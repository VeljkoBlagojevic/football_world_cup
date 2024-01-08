package rs.ac.bg.fon.football_world_cup.graphql_controller;

import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import rs.ac.bg.fon.football_world_cup.domain.Stadion;
import rs.ac.bg.fon.football_world_cup.service.StadionService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@CrossOrigin
public class StadionGraphQLController {

    private final StadionService stadionService;

    @QueryMapping
    public List<Stadion> getAllStadioni() {
        return stadionService.getAll();
    }

    @QueryMapping
    public Stadion getStadion(@Argument Long id) {
        return stadionService.getById(id);
    }
}
