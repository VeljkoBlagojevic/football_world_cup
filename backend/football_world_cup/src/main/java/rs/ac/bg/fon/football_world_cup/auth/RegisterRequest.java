package rs.ac.bg.fon.football_world_cup.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterRequest {
    @NotBlank(message = "Vase ime ne moze biti prazno")
    private String ime;
    @NotBlank(message = "Vase prezime ne moze biti prazno")
    private String prezime;
    @NotBlank(message = "Vasa email adresa ne moze biti prazna")
    @Email(message = "Vasa email adresa nije u validnom formatu")
    private String email;
    @NotBlank(message = "Vas username ne moze biti prazan")
    private String username;
    @NotBlank(message = "Vas password ne moze biti prazan")
    private String password;
}
