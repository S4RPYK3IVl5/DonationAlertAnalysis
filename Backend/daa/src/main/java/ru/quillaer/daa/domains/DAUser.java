package ru.quillaer.daa.domains;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

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

    @OneToOne(mappedBy = "daUser", cascade = CascadeType.ALL)
    private Token token;

}
