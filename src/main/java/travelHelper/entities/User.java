package travelHelper.entities;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class User {

    private int user_id;

    @NotNull(message = "Users name is required!")
    @NotEmpty(message = "Users name is required!")
    private String name;

    @NotNull(message = "Users surrname is required!")
    @NotEmpty(message = "Users surrname is required!")
    private String surrname;

    @NotNull(message = "Users type is required!")
    @NotEmpty(message = "Users type is required!")
    private String type;

    @NotNull(message = "Users password is required!")
    @NotEmpty(message = "Users password is required!")
    private String password;

    @NotNull(message = "Users email is required!")
    @NotEmpty(message = "Users email is required!")
    private String email;

    @NotNull(message = "Users status is required!")
    @NotEmpty(message = "Users status is required!")
    private String status;

    public User() {
    }

    public User(int user_id, String name, String surrname, String type, String password, String email,String status) {
        this.user_id = user_id;
        this.name = name;
        this.surrname = surrname;
        this.type = type;
        this.password = password;
        this.email = email;
        this.status = status;
    }
}
