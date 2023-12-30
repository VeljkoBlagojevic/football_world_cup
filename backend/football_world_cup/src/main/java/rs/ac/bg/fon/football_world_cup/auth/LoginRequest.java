package rs.ac.bg.fon.football_world_cup.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class LoginRequest {
    @NotBlank(message = "Username ne moze biti prazan")
    private String username;
    @NotBlank(message = "Password ne moze biti prazan")
    private String password;
}
