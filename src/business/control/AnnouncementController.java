package business.control;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import business.control.validation.exceptions.CustomException;
import business.model.Announcement;
import business.model.User;

public class AnnouncementController {

    public List<String> add (String userEmail, String title) {
        
        //TODO: criar a função no findUserByEmail
        User user = findUserByEmail(userEmail)
            
        List<String> errors = new ArrayList<>();

        try {
            Announcement announcement = new Announcement(createdBy, title, new Date());
            //TODO: Enviar para o infra para ser gravado
        } catch (CustomException ex) {
            errors.add(ex.getMessage());
        }

        return errors;
    }

    public List<String> delete (String userEmail, String title) {
        //TODO: criar a função no findUserByEmail
        User user = findUserByEmail(userEmail)
        List<String> errors = new ArrayList<>();

//        try {
//            //TODO: Enviar para o infra para ser deletado
//        } catch (CustomException ex) {
//            errors.add(ex.getMessage());
//        }

        return errors;
    }
}
