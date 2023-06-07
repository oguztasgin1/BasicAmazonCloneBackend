package com.oguztasgin.dto.request;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RegisterRequestDto {
    @NotNull(message = "Username is required.")
    @Size(min = 3,max = 18)
    String username;
    @NotNull(message = "Password cannot be blank.")
    @Size(min = 8, max = 64)

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=*!])(?=\\S+$).{8,}$",
            message = "Password must consist of at least 8 Characters, At least one uppercase and lowercase letters, Numbers and special characters.")
    String password;
    @NotNull(message = "Password cannot be blank.")
    @Size(min = 8, max = 64)
    String repassword;
    @Email(message = "Please enter a valid e-mail address.")
    String email;
}
