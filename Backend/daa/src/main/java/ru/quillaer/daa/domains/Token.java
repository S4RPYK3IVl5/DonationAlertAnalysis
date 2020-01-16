package ru.quillaer.daa.domains;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String token_type;
    private int expires_in;
    private String access_token;
    private String refresh_token;

    @OneToOne
    @JoinColumn(name = "user_id")
    private DAUser daUser;

}
