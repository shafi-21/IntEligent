package snps.hack.message.request;

import javax.validation.constraints.NotBlank;

public class ResetForm {
    @NotBlank
    private String password;

    private String token;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

