package uz.pdp.appbancomat.payload;

import lombok.Data;
import uz.pdp.appbancomat.entity.Role;

import java.util.Set;


@Data
public class RegisterDto {

    private String firstName;

    private String lastName;

    private Set<Role> roleName;

    private String email;

    private String password;
}
