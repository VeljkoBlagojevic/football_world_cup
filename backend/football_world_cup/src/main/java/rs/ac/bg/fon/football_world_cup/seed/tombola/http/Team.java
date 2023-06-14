package rs.ac.bg.fon.football_world_cup.seed.tombola.http;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Team {
    private String country;
    private String alternateName;
}
