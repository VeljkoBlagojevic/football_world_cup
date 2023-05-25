package rs.ac.bg.fon.football_world_cup.service;

import com.google.common.base.Preconditions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rs.ac.bg.fon.football_world_cup.domain.Utakmica;
import rs.ac.bg.fon.football_world_cup.repository.UtakmicaRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UtakmicaService {


    private final UtakmicaRepository utakmicaRepository;
    private final GrupaService grupaService;
    private final TerminService terminService;
    public static final int TRAJANJE_UTAKMICE = 90;

    public List<Utakmica> getAll() {
        return utakmicaRepository.findAll();
    }

    public Utakmica zakaziUtakmicu(Utakmica utakmica) {
        checkPreconditions(utakmica);
        return utakmicaRepository.save(utakmica);
    }

    private void checkPreconditions(Utakmica utakmica) {
        Preconditions.checkNotNull(utakmica, "Morate uneti utakmicu");
        Preconditions.checkNotNull(utakmica.getDomacin(), "Morate izabrati reprezentaciju domacina utakmice");
        Preconditions.checkNotNull(utakmica.getGost(), "Morate izabrati reprezentaciju gosta utakmice");
        Preconditions.checkNotNull(utakmica.getStadion(), "Morate izabrati stadion");
        Preconditions.checkNotNull(utakmica.getTermin(), "Morate izabrati vremenski termin");

        Preconditions.checkArgument(!utakmica.getDomacin().equals(utakmica.getGost()), "Ne moze reprezentacija da igra sama sa sobom utakmicu");
        Preconditions.checkArgument(utakmica.getTermin().getPocetak().isAfter(LocalDateTime.now()), "Vreme odvijanja zakazane utakmice mora biti u buducnosti");
        Preconditions.checkArgument(utakmica.getTermin().getPocetak().isEqual(utakmica.getTermin().getKraj().minusMinutes(TRAJANJE_UTAKMICE)), "Utakmica mora trajati " + TRAJANJE_UTAKMICE + " minuta");
        Preconditions.checkArgument(grupaService.izIsteGrupe(utakmica.getDomacin(), utakmica.getGost()), "Reprezentacije moraju biti iz iste grupe");


        List<Utakmica> zakazaneUtakmiceDomacina = utakmicaRepository.findByDomacinIdOrGostId(utakmica.getDomacin().getId(), utakmica.getDomacin().getId());
        boolean domacinVecImaZakazanuUtakmicuUToVreme = terminService.areOverlapping(utakmica.getTermin(), zakazaneUtakmiceDomacina.stream().map(Utakmica::getTermin).toList());
        Preconditions.checkState(!domacinVecImaZakazanuUtakmicuUToVreme, "Postoji vec zakazana utakmica za " + utakmica.getDomacin() + " u preklapajucem terminu");

        List<Utakmica> zakazaneUtakmiceGosta = utakmicaRepository.findByDomacinIdOrGostId(utakmica.getGost().getId(), utakmica.getGost().getId());
        boolean gostVecImaZakazanuUtakmicuUToVreme = terminService.areOverlapping(utakmica.getTermin(), zakazaneUtakmiceGosta.stream().map(Utakmica::getTermin).toList());
        Preconditions.checkState(!gostVecImaZakazanuUtakmicuUToVreme, "Postoji vec zakazana utakmica za " + utakmica.getGost() + " u preklapajucem terminu");


        List<Utakmica> zakazaneUtakmiceNaDatomStadionu = utakmicaRepository.findByStadionId(utakmica.getStadion().getId());
        boolean stadionVecImaZakazanuUtakmicuUToVreme = terminService.areOverlapping(utakmica.getTermin(), zakazaneUtakmiceNaDatomStadionu.stream().map(Utakmica::getTermin).toList());
        Preconditions.checkState(!stadionVecImaZakazanuUtakmicuUToVreme, "Postoji vec zakazana utakmica na datom stadionu u preklapajucem vremenu");


        List<Utakmica> sveZakazaneUtakmice = utakmicaRepository.findAll();
        boolean reprezentacijeSuIgraliVecMedjusobno = sveZakazaneUtakmice.stream()
                .anyMatch(
                        zakazanaUtakmica ->
                                (utakmica.getDomacin().equals(zakazanaUtakmica.getDomacin()) && utakmica.getGost().equals(zakazanaUtakmica.getGost())) ||
                                (utakmica.getDomacin().equals(zakazanaUtakmica.getGost()) && utakmica.getGost().equals(zakazanaUtakmica.getDomacin())));
        Preconditions.checkState(!reprezentacijeSuIgraliVecMedjusobno, "Reprezetancije su vec igrale medjusobno utakmicu");
    }
}
