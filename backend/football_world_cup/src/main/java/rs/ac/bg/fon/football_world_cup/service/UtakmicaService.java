package rs.ac.bg.fon.football_world_cup.service;

import com.google.common.base.Preconditions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rs.ac.bg.fon.football_world_cup.domain.*;
import rs.ac.bg.fon.football_world_cup.repository.EvidencijaUtakmiceRepository;
import rs.ac.bg.fon.football_world_cup.repository.UtakmicaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UtakmicaService {
    public static final int TRAJANJE_UTAKMICE = 90;

    private final UtakmicaRepository utakmicaRepository;
    private final GrupaService grupaService;
    private final TerminService terminService;
    private final EvidencijaUtakmiceRepository evidencijaUtakmiceRepository;
    private final MatchResultProcessor matchResultProcessor;
    private final ReprezentacijaService reprezentacijaService;
    private final StadionService stadionService;

    public List<Utakmica> getAll() {
        return utakmicaRepository.findAll();
    }

    public List<Utakmica> getAll(boolean odigrana) {
        return utakmicaRepository.findByOdigrana(odigrana);
    }

    public Utakmica zakaziUtakmicu(Utakmica utakmica) {
        checkPreconditions(utakmica);
        return utakmicaRepository.save(utakmica);
    }

    private void checkPreconditions(Utakmica utakmica) {
        Preconditions.checkNotNull(utakmica, "Morate uneti utakmicu");

        Preconditions.checkNotNull(utakmica.getDomacin(), "Morate izabrati reprezentaciju domacina utakmice");
        Reprezentacija domacin = reprezentacijaService.getById(utakmica.getDomacin().getId());

        Preconditions.checkNotNull(utakmica.getGost(), "Morate izabrati reprezentaciju gosta utakmice");
        Reprezentacija gost = reprezentacijaService.getById(utakmica.getGost().getId());

        Preconditions.checkNotNull(utakmica.getStadion(), "Morate izabrati stadion");
        Stadion stadion = stadionService.getById(utakmica.getStadion().getId());

        Preconditions.checkNotNull(utakmica.getTermin(), "Morate izabrati vremenski termin");


        Preconditions.checkArgument(!domacin.equals(gost), "Ne moze reprezentacija da igra sama sa sobom utakmicu");
        Preconditions.checkArgument(!utakmica.isOdigrana(), "Ne mozete zakazati utakmicu koja je odigrana vec");
        Preconditions.checkArgument(utakmica.getTermin().getPocetak().isAfter(LocalDateTime.now()), "Vreme odvijanja zakazane utakmice mora biti u buducnosti");
        Preconditions.checkArgument(utakmica.getTermin().getPocetak().isEqual(utakmica.getTermin().getKraj().minusMinutes(TRAJANJE_UTAKMICE)), "Utakmica mora trajati " + TRAJANJE_UTAKMICE + " minuta");
        Preconditions.checkArgument(grupaService.izIsteGrupe(domacin, gost), "Reprezentacije moraju biti iz iste grupe");


        List<Utakmica> zakazaneUtakmiceDomacina = utakmicaRepository.findByDomacinIdOrGostId(domacin.getId(), domacin.getId());
        boolean domacinVecImaZakazanuUtakmicuUToVreme = terminService.areOverlapping(utakmica.getTermin(), zakazaneUtakmiceDomacina.stream().map(Utakmica::getTermin).toList());
        Preconditions.checkState(!domacinVecImaZakazanuUtakmicuUToVreme, "Postoji vec zakazana utakmica za " + domacin + " u preklapajucem terminu");

        List<Utakmica> zakazaneUtakmiceGosta = utakmicaRepository.findByDomacinIdOrGostId(gost.getId(), gost.getId());
        boolean gostVecImaZakazanuUtakmicuUToVreme = terminService.areOverlapping(utakmica.getTermin(), zakazaneUtakmiceGosta.stream().map(Utakmica::getTermin).toList());
        Preconditions.checkState(!gostVecImaZakazanuUtakmicuUToVreme, "Postoji vec zakazana utakmica za " + gost + " u preklapajucem terminu");


        List<Utakmica> zakazaneUtakmiceNaDatomStadionu = utakmicaRepository.findByStadionId(stadion.getId());
        boolean stadionVecImaZakazanuUtakmicuUToVreme = terminService.areOverlapping(utakmica.getTermin(), zakazaneUtakmiceNaDatomStadionu.stream().map(Utakmica::getTermin).toList());
        Preconditions.checkState(!stadionVecImaZakazanuUtakmicuUToVreme, "Postoji vec zakazana utakmica na datom stadionu u preklapajucem vremenu");


        List<Utakmica> sveZakazaneUtakmice = utakmicaRepository.findAll();
        boolean reprezentacijeSuIgraliVecMedjusobno = sveZakazaneUtakmice.stream()
                .anyMatch(
                        zakazanaUtakmica ->
                                (domacin.equals(zakazanaUtakmica.getDomacin()) && gost.equals(zakazanaUtakmica.getGost())) ||
                                        (domacin.equals(zakazanaUtakmica.getGost()) && gost.equals(zakazanaUtakmica.getDomacin())));
        Preconditions.checkState(!reprezentacijeSuIgraliVecMedjusobno, "Reprezetancije su vec igrale medjusobno utakmicu");
    }

    public Utakmica getById(Long id) {
        return utakmicaRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Nije pronadjena utakmica sa id: " + id));
    }

    public Utakmica evidentirajRezultat(Long utakmicaId, EvidencijaUtakmice evidencijaUtakmice) {
        Utakmica utakmica = getById(utakmicaId);

        if (evidencijaUtakmice.getPredaja().equals(Predaja.NEMA_PREDAJE) && (utakmica.getTermin().getKraj().isAfter(LocalDateTime.now()))) {
            throw new IllegalStateException("Ne mozete uneti rezultat pre nego sto se zavrsi utakmica");
        }

        utakmica.setEvidencijaUtakmice(evidencijaUtakmice);

        matchResultProcessor.processRezultatUtakmice(utakmica);

        utakmica.setOdigrana(true);

        evidencijaUtakmiceRepository.save(evidencijaUtakmice);
        return utakmicaRepository.save(utakmica);
    }

    public List<Utakmica> getAllByReprezentacija(Long reprezentacijaId) {
        Reprezentacija reprezentacija = reprezentacijaService.getById(reprezentacijaId);
        return utakmicaRepository.findByDomacinIdOrGostId(reprezentacija.getId(), reprezentacija.getId());
    }
}
