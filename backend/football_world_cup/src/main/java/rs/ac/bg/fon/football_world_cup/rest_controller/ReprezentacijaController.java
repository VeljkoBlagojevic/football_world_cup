package rs.ac.bg.fon.football_world_cup.rest_controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import rs.ac.bg.fon.football_world_cup.domain.Reprezentacija;
import rs.ac.bg.fon.football_world_cup.service.ReprezentacijaService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reprezentacije")
@CrossOrigin
@RequiredArgsConstructor
public class ReprezentacijaController {

    private final ReprezentacijaService reprezentacijaService;

    @GetMapping
    public List<Reprezentacija> getAll() {
        return reprezentacijaService.getAll();
    }

    @GetMapping("/{id}")
    public Reprezentacija getById(@PathVariable Long id) {
        return reprezentacijaService.getById(id);
    }
}
