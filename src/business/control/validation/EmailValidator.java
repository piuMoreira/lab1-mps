package business.control.validation;

public class EmailValidation implements Validation {

    @Override
    public void validate(String data) {
        // verificar se estar vazio
        // verificar se está com mais de 12
        // verificar se existe números

        if (data.length() < 0) {
            //
        } else if (data.length() > 12 ) {
            //
        }

        data.matches(".*\\d.*");
    }
}
