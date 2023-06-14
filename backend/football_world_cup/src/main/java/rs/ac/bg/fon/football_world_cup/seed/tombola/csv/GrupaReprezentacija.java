package rs.ac.bg.fon.football_world_cup.seed.tombola.csv;

import com.opencsv.bean.CsvBindByName;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
public class GrupaReprezentacija {


    @CsvBindByName(column = "grupa")
    private String grupa;

    @CsvBindByName(column = "id")
    private Long id;

    @CsvBindByName(column = "naziv")
    private String naziv;

    @CsvBindByName(column = "dvoslovniNaziv")
    private String dvoslovniNaziv;

    @CsvBindByName(column = "troslovniNaziv")
    private String troslovniNaziv;

    @CsvBindByName(column = "zastava")
    private String zastava;

}
