package snps.hack.message.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class RegistrationForm {
    @NotBlank
    @Size(min = 3, max = 50)
    private String name;

    @NotBlank
    @Size(max = 60)
    @Email
    private String email;
    
    @NotBlank
    @Size(min = 6, max = 40)
    private String password;
    

    @NotBlank
    @Size(min = 3, max = 20)
    private String mobilenumber;
    
    @NotBlank
    @Size(min = 4, max = 6)
    private String bu;

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    public String getMobilenumber() {
    	return mobilenumber;
    }
    
    public void setMobilenumber(String mobilenumber) {
    	this.mobilenumber = mobilenumber;
    }

    public String getBu() {
        return bu;
    }

    public void setBu(String bu) {
        this.bu = bu;
    }
}