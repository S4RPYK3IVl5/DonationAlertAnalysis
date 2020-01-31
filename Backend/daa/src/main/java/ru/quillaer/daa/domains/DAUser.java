package ru.quillaer.daa.domains;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "daUser", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Donate> donates = new ArrayList<>();

}
