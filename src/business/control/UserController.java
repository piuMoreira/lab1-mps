package business.control;

import business.control.validation.Validation;
import business.model.Usuario;

import java.util.List;

public class UserController {

    private final List<Validation> validations;

    public UserController(List<Validation> validations) {
        this.validations = validations;
    }

    public void cadastrarUsuario (String email, String password) {
        // validar os dados

        // criar usuario
        usuario = new Usuario(email, password);
    }

}
