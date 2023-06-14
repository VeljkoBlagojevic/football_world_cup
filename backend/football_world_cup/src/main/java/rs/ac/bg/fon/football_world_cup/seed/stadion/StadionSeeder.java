package rs.ac.bg.fon.football_world_cup.seed.stadion;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import rs.ac.bg.fon.football_world_cup.domain.Stadion;
import rs.ac.bg.fon.football_world_cup.repository.StadionRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class StadionSeeder {

    private final StadionRepository stadionRepository;

    public void seedStadionData() {
        if (stadionRepository.count() != 0) {
            return;
        }
        Stadion alBayt = Stadion.builder()
                .naziv("Al Bayt Stadium")
                .kapacitet(69_895)
                .slika("https://www.qatar2022.qa/sites/default/files/styles/1440x815/public/2022-08/Al-Bayt-Stadium.jpg?h=aa3f82a2&itok=mIBqOXcB")
                .build();
        Stadion lusail = Stadion.builder()
                .naziv("Lusail Stadium")
                .kapacitet(88_966)
                .slika("https://www.qatar2022.qa/sites/default/files/styles/1440x815/public/2022-08/Lusail-Stadium.jpg?h=48c16481&itok=vK9KsyE3")
                .build();
        Stadion ahmadBinAli = Stadion.builder()
                .naziv("Ahmad Bin Ali Stadium")
                .kapacitet(45_032)
                .slika("https://www.qatar2022.qa/sites/default/files/styles/1440x815/public/2022-08/Ahmad-Bin-Ali-Stadium.jpg?h=8f9cfe54&itok=zt2GOB01")
                .build();
        Stadion alJanoub = Stadion.builder()
                .naziv("Al Janoub Stadium")
                .kapacitet(44_325)
                .slika("https://www.qatar2022.qa/sites/default/files/styles/1440x815/public/2022-08/Al-Janoub-Stadium.jpg?h=f8ea1366&itok=aJpXm9Lf")
                .build();
        Stadion alThumamaStadium = Stadion.builder()
                .naziv("Al Thumama Stadium")
                .kapacitet(44_400)
                .slika("https://www.qatar2022.qa/sites/default/files/styles/1440x815/public/2022-08/Al-Thumama-Stadium.jpg?h=98540297&itok=Gu6kll24")
                .build();
        Stadion educationCity = Stadion.builder()
                .naziv("Education City Stadium")
                .kapacitet(44_667)
                .slika("https://www.qatar2022.qa/sites/default/files/styles/1440x815/public/2022-08/Education-City-Stadium.jpg?h=bf548865&itok=wIMLDvYX")
                .build();
        Stadion khalifaInternational = Stadion.builder()
                .naziv("Khalifa International Stadium")
                .kapacitet(45_857)
                .slika("https://www.qatar2022.qa/sites/default/files/styles/1440x815/public/2022-08/Khalifa-International-Stadium.jpg?h=b5673621&itok=_sZr4yIe")
                .build();
        Stadion stadium974 = Stadion.builder()
                .naziv("Stadium 974")
                .kapacitet(44_089)
                .slika("https://www.qatar2022.qa/sites/default/files/styles/1440x815/public/2022-08/Stadium-974.jpg?h=27457cb0&itok=lwq-j9pX")
                .build();

        List<Stadion> stadioni = List.of(ahmadBinAli, alJanoub, alBayt, lusail, alThumamaStadium, educationCity, khalifaInternational, stadium974);
        stadionRepository.saveAll(stadioni);
    }
}
