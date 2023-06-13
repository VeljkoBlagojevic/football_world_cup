package rs.ac.bg.fon.football_world_cup.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import rs.ac.bg.fon.football_world_cup.domain.EvidencijaUtakmice;
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

    @GetMapping(params = "odigrana")
    public List<Utakmica> getAll(@RequestParam boolean odigrana) {
        return utakmicaService.getAll(odigrana);
    }

    @GetMapping("/{utakmicaId}")
    public Utakmica getById(@PathVariable Long utakmicaId) {
        return utakmicaService.getById(utakmicaId);
    }

    @GetMapping("/reprezentacija/{reprezentacijaId}")
    public List<Utakmica> getAllByReprezentacijaId(@PathVariable Long reprezentacijaId) {
        return utakmicaService.getAllByReprezentacija(reprezentacijaId);
    }

    @PostMapping
    public Utakmica zakaziUtakmicu(@RequestBody @Valid Utakmica utakmica) {
        return utakmicaService.zakaziUtakmicu(utakmica);
    }

    @PostMapping("/{utakmicaId}")
    public Utakmica evidentirajRezultat(@PathVariable Long utakmicaId, @RequestBody EvidencijaUtakmice evidencijaUtakmice) {
        return utakmicaService.evidentirajRezultat(utakmicaId, evidencijaUtakmice);
    }

}
