package rs.ac.bg.fon.football_world_cup.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rs.ac.bg.fon.football_world_cup.domain.Reprezentacija;
import rs.ac.bg.fon.football_world_cup.repository.ReprezentacijaRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ReprezentacijaService {

    private final ReprezentacijaRepository reprezentacijaRepository;

    public List<Reprezentacija> getAll() {
        return reprezentacijaRepository.findAll();
    }

    public Reprezentacija getById(Long id) {
        return reprezentacijaRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Nije pronadjena reprezentacija sa id: " + id));
    }

}
