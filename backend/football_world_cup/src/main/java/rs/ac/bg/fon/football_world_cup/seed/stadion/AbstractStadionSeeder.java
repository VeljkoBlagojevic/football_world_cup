package rs.ac.bg.fon.football_world_cup.seed.stadion;

import lombok.RequiredArgsConstructor;
import rs.ac.bg.fon.football_world_cup.domain.Stadion;
import rs.ac.bg.fon.football_world_cup.repository.StadionRepository;

import java.util.List;

@RequiredArgsConstructor
public abstract class AbstractStadionSeeder {

    private final StadionRepository stadionRepository;

    public void seedStadionData() {
        if (stadionRepository.count() != 0) {
            return;
        }
        List<Stadion> stadioni = getStadioni();
        stadionRepository.saveAll(stadioni);
    }

    protected abstract List<Stadion> getStadioni();
}
