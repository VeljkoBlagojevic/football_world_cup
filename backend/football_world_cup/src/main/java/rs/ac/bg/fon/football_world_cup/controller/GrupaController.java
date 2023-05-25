package rs.ac.bg.fon.football_world_cup.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import rs.ac.bg.fon.football_world_cup.domain.Grupa;
import rs.ac.bg.fon.football_world_cup.domain.Reprezentacija;
import rs.ac.bg.fon.football_world_cup.service.GrupaService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/grupe")
@CrossOrigin
@RequiredArgsConstructor
public class GrupaController {

    private final GrupaService grupaService;

    @GetMapping
    public List<Grupa> getAll() {
        return grupaService.getAll();
    }

    @GetMapping("/{grupaId}")
    public Grupa getById(@PathVariable Long grupaId) {
        return grupaService.getById(grupaId);
    }

    @GetMapping("/{grupaId}/reprezentacije")
    public List<Reprezentacija> getReprezentacijeIzGrupe(@PathVariable Long grupaId) {
        return grupaService.getReprezentacijeIzGrupe(grupaId);
    }

}
