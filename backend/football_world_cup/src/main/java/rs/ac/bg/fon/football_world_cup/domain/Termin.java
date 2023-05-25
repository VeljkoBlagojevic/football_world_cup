package rs.ac.bg.fon.football_world_cup.domain;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.time.LocalDateTime;

@Embeddable

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Termin {

    private LocalDateTime pocetak;

    private LocalDateTime kraj;
}
