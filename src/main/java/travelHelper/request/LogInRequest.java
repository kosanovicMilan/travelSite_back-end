package travelHelper.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class LogInRequest {

    @NotNull(message = "Email field is required!")
    @NotEmpty(message = "Email field is required!")
    private String email;

    @NotNull(message = "Password field is required!")
    @NotEmpty(message = "Password field is required!")
    private String password;

    public LogInRequest() {
    }
}
