package rs.ac.bg.fon.football_world_cup.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rs.ac.bg.fon.football_world_cup.domain.Stadion;
import rs.ac.bg.fon.football_world_cup.repository.StadionRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class StadionService {

    private final StadionRepository stadionRepository;

    public List<Stadion> getAll() {
        return stadionRepository.findAll();
    }

    public Stadion getById(Long id) {
        return stadionRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }
}
