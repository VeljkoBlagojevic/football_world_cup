package rs.ac.bg.fon.football_world_cup.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class StatistikaReprezentacije {


    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    @JoinColumn(name = "reprezentacija_id")
//    @Valid
//    @NotNull(message = "Mora postojati reprezentacija za koju se vrsti statistika")
    private Reprezentacija reprezentacija;

//    @PositiveOrZero(message = "Reprezentacija ne moze odigrati negativan broj utakmica")
    private int brojOdigranihUtakmica;

//    @PositiveOrZero(message = "Reprezentacija ne moze pobediti negativan broj utakmica")
    private int brojPobedjenihUtakmica;

//    @PositiveOrZero(message = "Reprezentacija ne moze igrati nereseno negativan broj utakmica")
    private int brojNeresenihUtakmica;

//    @PositiveOrZero(message = "Reprezentacija ne moze izgubiti negativan broj utakmica")
    private int brojIzgubljenihUtakmica;

//    @PositiveOrZero(message = "Reprezentacija ne moze dati negativan broj golova")
    private int brojDatihGolova;

//    @PositiveOrZero(message = "Reprezentacija ne moze primiti negativan broj golova")
    private int brojPrimljenihGolova;

//    @PositiveOrZero(message = "Reprezentacija ne moze imati negativan broj poena")
    private int brojOsvojenihPoena;
}
