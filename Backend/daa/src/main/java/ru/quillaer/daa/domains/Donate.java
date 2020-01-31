package ru.quillaer.daa.domains;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@NoArgsConstructor
@Entity
@Data
public class Donate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

}
