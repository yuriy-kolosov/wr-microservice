package pro.sky.wr_m.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UzerDTO {

    @NotNull
    private Long id;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$"
            , message = "Требуется логин: email пользователя")
    private String email;

    @NotNull
    private String password;

    public UzerDTO() {
    }

    public UzerDTO(Long id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

}
