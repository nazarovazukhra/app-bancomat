package uz.pdp.appbancomat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.appbancomat.entity.Card;

import java.util.Optional;


@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {

    Optional<Card> findCardByPinCode(String pinCode);
}
