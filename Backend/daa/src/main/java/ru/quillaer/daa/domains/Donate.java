package ru.quillaer.daa.domains;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@NoArgsConstructor
@Entity
@Data
public class Donate {

    @Id
    private Long id;
    private String name;
    private String username;
    private String message_type;
    @Type(type = "text")
    private String message;
    private Double amount;
    private String currency;
    private Integer is_shown;
    private String created_at;
    private String shown_at;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "dauser_id", nullable = false)
    private DAUser daUser;

}
