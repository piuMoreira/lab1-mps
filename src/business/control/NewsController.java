package business.control;

import business.model.News;
import business.model.User;

public class NewsController {

    public void add(User user, String title) {
        try {
            News news = new News(user, title);
            //TODO: passar o title para a infra para salvar no arquivo
        } catch (CustomException ex) {
            //
        }
    }

    public void delete(User user, String title) {
        try {
            // Na infra pesquisar se a news do título {title} criado por {user} existe
            //TODO: Passar o email para o infra para deletar (infra deve retornar erro caso o email não exista na base de dados)
        } catch (CustomException ex) {
            //
        }
    }
}
