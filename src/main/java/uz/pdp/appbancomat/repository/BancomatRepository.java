package uz.pdp.appbancomat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appbancomat.entity.Bancomat;

import java.util.UUID;

public interface BancomatRepository extends JpaRepository<Bancomat, UUID> {
}
