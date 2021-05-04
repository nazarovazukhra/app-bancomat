package uz.pdp.appbancomat.payload;

import lombok.Data;

@Data
public class LoginDto {


    private String username;

    private String password;

    private String pinCode;
}
