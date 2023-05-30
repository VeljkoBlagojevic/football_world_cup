package rs.ac.bg.fon.football_world_cup.domain;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "Pocetak termina se mora definisati")
    private LocalDateTime pocetak;

    @NotNull(message = "Kraj termina se mora definisati")
    private LocalDateTime kraj;
}
