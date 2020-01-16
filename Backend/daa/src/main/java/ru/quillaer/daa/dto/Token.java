package ru.quillaer.daa.dto;

import lombok.Data;

@Data
public class Token {

    private String token_type;
    private int expires_in;
    private String access_token;
    private String refresh_token;

}
