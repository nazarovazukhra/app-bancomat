package uz.pdp.appbancomat.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class OperationHistory {

    @Id
    @GeneratedValue()
    private UUID id; // transaction ning takrorlanmas qismi

    @ManyToOne
    private Card senderCard;   //jo'natuvchi

    @ManyToOne
    private Card receiverCard;  //oluvchi

    @ManyToOne
    private Operation operation;

    @ManyToOne
    private Bancomat bancomat;

    private Double summa;

    @CreationTimestamp
    private Timestamp date;
}
