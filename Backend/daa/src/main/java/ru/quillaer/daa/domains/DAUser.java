package ru.quillaer.daa.domains;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class DAUser {

    @Id
    private Long id;

    private String code;
    private String name;
    private String avatar;
    private String email;
    private String socket_connection_token;

}
