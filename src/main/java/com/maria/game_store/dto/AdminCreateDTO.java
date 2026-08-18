package com.maria.game_store.dto;

import lombok.Data;

@Data
public class AdminCreateDTO {

    private String nickname;

    private String email;

    private String password;

    private String codeRh;
}
