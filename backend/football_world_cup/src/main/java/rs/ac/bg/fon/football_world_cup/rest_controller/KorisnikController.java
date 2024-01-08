package rs.ac.bg.fon.football_world_cup.rest_controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.bg.fon.football_world_cup.domain.Korisnik;
import rs.ac.bg.fon.football_world_cup.service.KorisnikService;

@RestController
@RequestMapping("/api/v1/korisnici")
@CrossOrigin
@RequiredArgsConstructor
public class KorisnikController {

    private final KorisnikService korisnikService;

    @GetMapping("/trenutnoUlogovani")
    public Korisnik getTrenutnogUlogovanog() {
        return korisnikService.getTrenutnoUlogovanogKorisnika();
    }
}
