package uz.pdp.appbancomat.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Banknote {  // kupyura

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // operation ning takrorlanmas qismi


    @Column(unique = true, nullable = false)
    private String name; // oparation nomi

    private Double cost;

}
