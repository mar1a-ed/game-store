package com.maria.game_store.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClientCreateDTO {

    @NotBlank(message = "A name is required")
    private String name;

    @NotBlank(message = "A nickname is required")
    private String nickname;

    @NotBlank(message = "A email is required")
    @Email(message = "Insert a valid email")
    private String email;

    @NotBlank(message = "A password is required")
    @Size(min = 8, max = 20, message = "The password must be between 8 and 20 characters long")
    private String password;

    @CPF(message = "Insert a valid cpf (numbers only)")
    @Size(min = 11, max = 11)
    private String cpf;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate birthday;
}
