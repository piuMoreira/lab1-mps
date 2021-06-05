package infra;

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
public class UserBinaryWriter {
    private Path filename;
    private String pathname;



    public UserBinaryWriter() throws FileException{
        String path = "users.bin";
        File file = new java.io.File(path);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new FileException("Não foi possível criar o arquivo.", e);
        }

        this.filename = Paths.get(path);
        this.pathname = path;
    }

    public void writeUserList(List<User> users) throws FileException{       
        byte newline[] = "\n".getBytes(StandardCharsets.UTF_8);
        byte tab[] = "\t".getBytes(StandardCharsets.UTF_8);
        
        for(User user : users){
            
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

    public void removeUser(String userLogin) throws InexistentUserException, FileException {
        int lines = countLines();
        List<String> out;
        try {
            out = Files.lines(filename).filter(line -> !line.contains(userLogin)).collect(Collectors.toList());
            if (out.size() == lines)
                throw new InexistentUserException("Usuário não encontrado.");
            else {
                Files.write(filename, out, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
            }
        } catch (IOException e) {
            throw new FileException("Não foi possível alterar o arquivo.", e);
        }

    }

    private int countLines() throws FileException{
        int lines = 0;
        try{
            lines = (int)Files.lines(Paths.get(pathname)).count();
        }catch(IOException e){
            throw new FileException("Occorreu um erro ao lidar com o arquivo.", e);
        }
        

        return lines;
    }





    



}



