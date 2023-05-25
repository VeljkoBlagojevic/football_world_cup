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

public class Utakmica {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "domacin_id")
//    @Valid
//    @NotNull(message = "Mora se proslediti koja reprezentacija je domacin na utakmici")
    private Reprezentacija domacin;

    @ManyToOne
    @JoinColumn(name = "gost_id")
//    @Valid
//    @NotNull(message = "Mora se proslediti koja reprezentacija je gost na utakmici")
    private Reprezentacija gost;

    @OneToOne
    @JoinColumn(name = "evidencija_utakmice_id")
    private EvidencijaUtakmice evidencijaUtakmice;

    @ManyToOne
    @JoinColumn(name = "stadion_id")
//    @Valid
//    @NotNull(message = "Mora se izabrati stadion na kojem ce se igrati utakmica")
    private Stadion stadion;

    @Embedded
//    @Valid
//    @NotNull(message = "Mora se proslediti zeljeni termin")
    private Termin termin;

}
