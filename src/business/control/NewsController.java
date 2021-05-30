package business.control;

import java.util.ArrayList;
import java.util.List;

import business.model.News;
import business.model.User;

public class NewsController {

    public List<String> add(User user, String title) {

        List<String> errors = new ArrayList<>();

        try {
            News news = new News(user, title);
            //TODO: passar o title para a infra para salvar no arquivo
        } catch (CustomException ex) {
            //
        }

        return errors;
    }

    public List<String> delete(User user, String title) {
        List<String> errors = new ArrayList<>();

        try {
            // Na infra pesquisar se a news do título {title} criado por {user} existe
            //TODO: Passar o email para o infra para deletar (infra deve retornar erro caso o email não exista na base de dados)
        } catch (CustomException ex) {
            //
        }

        return errors;
    }
}
