package rs.ac.bg.fon.football_world_cup.domain;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode(exclude = {"reprezentacije", "statistikeReprezentacija"})
@ToString(exclude = {"reprezentacije", "statistikeReprezentacija"})
public class Grupa {

    public static final int BROJ_REPREZENTACIJA_U_GRUPI = 4;

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message = "Morate uneti naziv grupe")
    private String naziv;

    @OneToMany
    @Valid
    @Size(min = BROJ_REPREZENTACIJA_U_GRUPI, max = BROJ_REPREZENTACIJA_U_GRUPI, message = "Moguce je tacno " + BROJ_REPREZENTACIJA_U_GRUPI + " ekipe u jednoj grupi")
    private List<Reprezentacija> reprezentacije = new ArrayList<>(BROJ_REPREZENTACIJA_U_GRUPI);

    @OneToMany(cascade = CascadeType.ALL)
    @Valid
    @Size(min = BROJ_REPREZENTACIJA_U_GRUPI, max = BROJ_REPREZENTACIJA_U_GRUPI, message = "Moguce je tacno " + BROJ_REPREZENTACIJA_U_GRUPI + " ekipe u jednoj grupi")
    private List<StatistikaReprezentacije> statistikeReprezentacija = new ArrayList<>(BROJ_REPREZENTACIJA_U_GRUPI);

    public void addStatistikaReprezentacija(StatistikaReprezentacije statistikaReprezentacije) {
        statistikeReprezentacija.add(statistikaReprezentacije);
    }
}
