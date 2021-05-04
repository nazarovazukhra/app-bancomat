package uz.pdp.appbancomat.payload;

import lombok.Data;
import uz.pdp.appbancomat.entity.CardType;

import java.util.List;

@Data
public class BancomatDto {

    private Double moneyAmount;

    private String region;

    private String street;

    private List<CardType> cardType;
}
