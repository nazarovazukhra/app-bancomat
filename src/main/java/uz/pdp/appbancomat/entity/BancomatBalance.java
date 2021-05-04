package uz.pdp.appbancomat.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BancomatBalance {

    @Id
    @GeneratedValue()
    private UUID id;

    private Double amount;

    @ManyToOne
    private Bancomat bancomat;

    @ManyToOne
    private Banknote banknote;
}

