package uz.pdp.appbancomat.repository;

import uz.pdp.appbancomat.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    boolean existsByEmail(String email);

    Optional<uz.pdp.appbancomat.entity.User> findAllByEmailAndEmailCode(String email, String emailCode);

    Optional<User> findAllByEmail(String email);

}
