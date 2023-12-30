package rs.ac.bg.fon.football_world_cup.auth;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationResponse {
  private String token;
}
