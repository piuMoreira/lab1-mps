package infra.factory;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;

// import business.control.validation.exceptions.CustomException;
import business.control.validation.exceptions.FileException;
import business.control.validation.exceptions.InexistentUserException;
import business.model.User;



//classe responsável pela persistência (escrevendo e deletando users)
public class UserBinaryWriter extends BinaryWriter {



    public UserBinaryWriter() throws FileException{
        super("users.bin");
    }

    public void write(Object users) throws FileException{       
        byte newline[] = "\n".getBytes(StandardCharsets.UTF_8);
        byte tab[] = "\t".getBytes(StandardCharsets.UTF_8);
        
        for(User user : ((List<User>) users)){
            
            byte email[] = user.getEmail().getBytes(StandardCharsets.UTF_8);
            byte password[] = user.getPassword().getBytes(StandardCharsets.UTF_8);
            
            try {
                Files.write(filename, email, StandardOpenOption.APPEND);
                Files.write(filename, tab, StandardOpenOption.APPEND);
                Files.write(filename, password, StandardOpenOption.APPEND);
                Files.write(filename, newline, StandardOpenOption.APPEND);
            } catch (IOException e) {
                throw new FileException("Não foi possível escrever no arquivo.", e);
            }
            
        }

    }

    public void remove(Object userLogin) throws InexistentUserException, FileException {
        int lines = countLines();
        List<String> out;
        try {
            out = Files.lines(filename).filter(line -> !line.contains((String)userLogin)).collect(Collectors.toList());
            if (out.size() == lines)
                throw new InexistentUserException("Usuário não encontrado.");
            else {
                Files.write(filename, out, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
            }
        } catch (IOException e) {
            throw new FileException("Não foi possível alterar o arquivo.", e);
        }

    }


}



