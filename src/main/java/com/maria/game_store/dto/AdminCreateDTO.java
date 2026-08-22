package com.maria.game_store.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AdminCreateDTO {

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

    @NotBlank
    private String codeRh;

    @NotBlank
    private String position;
}
