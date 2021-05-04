package uz.pdp.appbancomat.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Bancomat {

    @Id
    @GeneratedValue()
    private UUID id;

    private Double moneyAmount;

    private String region;

    private String street;

    @ManyToMany
    private List<CardType> cardType;

    public Bancomat(Double moneyAmount, String region, String street, List<CardType> cardType) {
        this.moneyAmount = moneyAmount;
        this.region = region;
        this.street = street;
        this.cardType = cardType;
    }
}

