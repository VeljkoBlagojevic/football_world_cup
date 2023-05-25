package rs.ac.bg.fon.football_world_cup.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import rs.ac.bg.fon.football_world_cup.domain.Utakmica;
import rs.ac.bg.fon.football_world_cup.service.UtakmicaService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/utakmice")
@CrossOrigin
@RequiredArgsConstructor
public class UtakmicaController {

    private final UtakmicaService utakmicaService;

    @GetMapping
    public List<Utakmica> getAll() {
        return utakmicaService.getAll();
    }

    @PostMapping
    public Utakmica zakaziUtakmicu(@RequestBody @Valid Utakmica utakmica) {
        return utakmicaService.zakaziUtakmicu(utakmica);
    }

    @PostMapping("/validacija")
    public void validacija(@RequestBody @Valid Utakmica utakmica) {
        System.out.println(utakmica);
    }
}
