package rs.ac.bg.fon.football_world_cup.seed.tombola.http;

import lombok.*;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Response {
    private List<Record> records;
}

