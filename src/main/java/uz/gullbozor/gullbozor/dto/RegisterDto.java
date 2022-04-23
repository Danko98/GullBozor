package uz.gullbozor.gullbozor.dto;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Getter
@Setter
public class RegisterDto {

    @NotNull
    @Size(min = 3,max = 50)
    private String firstName;// ismi

    @NotNull
    @Size(min = 3,max = 50)
    private String lastName;// familyasi

    @NotNull
    @Size(min = 3,max = 50)
    private String userName; // username

    @Email
    private String email; // userning emaili

    @NotNull
    private String password; //userning passwordi

}
