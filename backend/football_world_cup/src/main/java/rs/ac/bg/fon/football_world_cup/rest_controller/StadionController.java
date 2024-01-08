package rs.ac.bg.fon.football_world_cup.rest_controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import rs.ac.bg.fon.football_world_cup.domain.Stadion;
import rs.ac.bg.fon.football_world_cup.service.StadionService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stadioni")
@CrossOrigin
@RequiredArgsConstructor
public class StadionController {

    private final StadionService stadionService;

    @GetMapping
    public List<Stadion> getAll() {
        return stadionService.getAll();
    }

    @GetMapping("/{id}")
    public Stadion getById(@PathVariable Long id) {
        return stadionService.getById(id);
    }
}
