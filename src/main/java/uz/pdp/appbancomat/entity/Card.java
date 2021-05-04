package uz.pdp.appbancomat.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Card {

    @Id
    @GeneratedValue()
    private UUID id;

    @Column(nullable = false,unique = true)
    private String uniqueNumber;  // consists of 16 characters

    @Column(nullable = false)
    private String bankName;

    @Column(nullable = false,unique = true)
    private String cvvCode;

    @Column(nullable = false)
    private Date expireDate;

    private Boolean isActive=true;

    private String pinCode; // pin code -->  password

    @OneToOne
    private User user;

    @ManyToOne
    private CardType cardType;


}
