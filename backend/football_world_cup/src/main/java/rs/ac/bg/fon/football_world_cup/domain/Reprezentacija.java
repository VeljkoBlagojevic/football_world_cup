package rs.ac.bg.fon.football_world_cup.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Reprezentacija {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message = "Naziv reprezentacije ne sme biti prazan")
    @NotEmpty(message = "Mora se postaviti naziv reprezentacije")
    private String naziv;

    @Size(min = 2, max = 2, message = "Dvoslovni naziv predstavlja Alpha-2 code")
    private String dvoslovniNaziv;

    @Size(min = 3, max = 3, message = "Dvoslovni naziv predstavlja Alpha-3 code")
    private String troslovniNaziv;

    private String zastava;
}
