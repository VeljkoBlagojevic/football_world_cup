package rs.ac.bg.fon.football_world_cup.seed;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Group {
    private String code;
    private List<Team> teams;
}
