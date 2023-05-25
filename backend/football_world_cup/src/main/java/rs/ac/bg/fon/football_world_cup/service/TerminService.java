package rs.ac.bg.fon.football_world_cup.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rs.ac.bg.fon.football_world_cup.domain.Termin;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TerminService {

    public boolean areOverlapping(Termin termin, List<Termin> list) {
        return list.stream().anyMatch(t -> areOverlapping(termin, t));
    }

    public boolean areOverlapping(Termin t1, Termin t2) {
        return t1.getPocetak().isBefore(t2.getKraj()) && t2.getPocetak().isBefore(t1.getKraj());
    }

}
