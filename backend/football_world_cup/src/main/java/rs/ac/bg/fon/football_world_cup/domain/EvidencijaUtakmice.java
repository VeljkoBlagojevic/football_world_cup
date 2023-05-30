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
public class EvidencijaUtakmice {

    @Id
    @GeneratedValue
    private Long id;

    //    @PositiveOrZero(message = "Domacin ne moze dati negativan broj golova")
    private int brojGolovaDomacina;

    //    @PositiveOrZero(message = "Gost ne moze dati negativan broj golova")
    private int brojGolovaGosta;

    @Enumerated(EnumType.STRING)
//    @Valid
//    @NotNull(message = "Mora se odrediti da li je bilo predaje")
    @Builder.Default
    private Predaja predaja = Predaja.NEMA_PREDAJE;
}
