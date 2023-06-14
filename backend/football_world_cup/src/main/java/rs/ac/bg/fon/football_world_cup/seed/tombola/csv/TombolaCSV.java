package rs.ac.bg.fon.football_world_cup.seed.tombola.csv;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Component;
import rs.ac.bg.fon.football_world_cup.domain.Grupa;
import rs.ac.bg.fon.football_world_cup.domain.Reprezentacija;
import rs.ac.bg.fon.football_world_cup.domain.StatistikaReprezentacije;
import rs.ac.bg.fon.football_world_cup.repository.GrupaRepository;
import rs.ac.bg.fon.football_world_cup.repository.ReprezentacijaRepository;
import rs.ac.bg.fon.football_world_cup.repository.StatistikaReprezentacijeRepository;
import rs.ac.bg.fon.football_world_cup.seed.tombola.Tombola;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TombolaCSV extends Tombola {
    public TombolaCSV(GrupaRepository grupaRepository, ReprezentacijaRepository reprezentacijaRepository, StatistikaReprezentacijeRepository statistikaReprezentacijeRepository) {
        super(grupaRepository, reprezentacijaRepository, statistikaReprezentacijeRepository);
    }
    @Override
    protected List<Grupa> getGrupe() {
        Path filePath = Paths.get("csv\\grupereprezentacije.csv");
        try (Reader reader = Files.newBufferedReader(filePath)) {
            CsvToBean<GrupaReprezentacija> csvToBean = new CsvToBeanBuilder<GrupaReprezentacija>(reader)
                    .withType(GrupaReprezentacija.class)
                    .withSeparator(',')
                    .build();

            List<Grupa> grupe = new ArrayList<>();
            Map<String, Grupa> grupaMap = new HashMap<>();

            for (GrupaReprezentacija grupaReprezentacija : csvToBean) {
                String grupaNaziv = grupaReprezentacija.getGrupa();
                Grupa grupa;

                // Check if the Grupa already exists in the map
                if (grupaMap.containsKey(grupaNaziv)) {
                    grupa = grupaMap.get(grupaNaziv);
                } else {
                    grupa = new Grupa();
                    grupa.setNaziv(grupaNaziv);
                    grupaMap.put(grupaNaziv, grupa);
                    grupe.add(grupa);
                }

                // Add the associated Reprezentacija to the Grupa
                Reprezentacija reprezentacija = Reprezentacija.builder()
                        .naziv(grupaReprezentacija.getNaziv())
                        .dvoslovniNaziv(grupaReprezentacija.getDvoslovniNaziv())
                        .troslovniNaziv(grupaReprezentacija.getTroslovniNaziv())
                        .zastava(grupaReprezentacija.getZastava())
                        .build();

                grupa.addReprezentacija(reprezentacija);

                grupa.addStatistikaReprezentacija(StatistikaReprezentacije.builder()
                        .reprezentacija(reprezentacija)
                        .build());
            }

            return grupe;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
