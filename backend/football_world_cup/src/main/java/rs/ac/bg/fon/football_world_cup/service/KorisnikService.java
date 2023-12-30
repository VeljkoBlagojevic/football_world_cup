package rs.ac.bg.fon.football_world_cup.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import rs.ac.bg.fon.football_world_cup.domain.Korisnik;

@Service
public class KorisnikService {

    public Korisnik getTrenutnoUlogovanogKorisnika() {
        return (Korisnik) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
