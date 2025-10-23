package cz.upce.fei.nnpda.dto.User;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserChangePasswordDTO {
    @NotNull
    private String oldPassword;

    @NotNull
    @NotBlank
    @Size(min = 6)
    private String newPassword;
}
