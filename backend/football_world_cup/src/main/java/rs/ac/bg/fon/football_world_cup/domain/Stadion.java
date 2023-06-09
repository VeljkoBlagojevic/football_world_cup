package rs.ac.bg.fon.football_world_cup.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@Entity

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Stadion {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message = "Mora postojati naziv stadiona")
    private String naziv;

    @PositiveOrZero(message = "Kapacitet mora biti pozitivan celobrojan broj")
    private int kapacitet;

    private String slika;
}
