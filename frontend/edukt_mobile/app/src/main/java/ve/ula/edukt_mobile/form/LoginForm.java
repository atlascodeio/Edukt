package ve.ula.edukt_mobile.form;

import android.support.annotation.StringRes;

import net.sf.oval.constraint.Email;
import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.NotEmpty;

import ve.ula.edukt_mobile.R;

public class LoginForm {



    @Email(profiles = "email", message = "Debe ser un correo valido")
    @NotEmpty(profiles = "email")
    @Length(max=50, min = 10, profiles = "email", message = "Debe tener entre 10 y 50 caracteres")
	private String email;
    @NotEmpty(profiles = "password")
    @Length(min=5, max=20, message = "El password longitud", profiles = "password")
	private String password;

	public LoginForm() {
        email = "";
        password = "";
	}

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
