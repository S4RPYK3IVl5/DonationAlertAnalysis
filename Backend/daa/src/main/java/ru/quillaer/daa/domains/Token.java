package ru.quillaer.daa.domains;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Data
@Entity
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String token_type;
    private int expires_in;
    @Type(type = "text")
    private String access_token;
    @Type(type = "text")
    private String refresh_token;

    @ToString.Exclude
    @OneToOne
    @JoinColumn(name = "user_id")
    private DAUser daUser;

    @ToString.Exclude
    @OneToOne(mappedBy = "token")
    private User user;

    private long creation_date;

}
