package infra.factory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;

import business.control.validation.exceptions.FileException;
import business.control.validation.exceptions.InexistentUserException;
import business.model.User;



//classe responsável pela persistência (escrevendo e deletando users)
public class UserBinaryWriter extends BinaryWriter {



    public UserBinaryWriter() throws FileException{
        super("users.bin");
    }

    public void write(Object users) throws FileException{    
    	List<User> concreteUsers = (List<User>) users; 
    	
        byte newline[] = "\n".getBytes(StandardCharsets.UTF_8);
        byte tab[] = "\t".getBytes(StandardCharsets.UTF_8);
        
        for(User user : concreteUsers){
            
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
    	String concreteUserLogin = (String)userLogin;
    	
        int lines = countLines();
        List<String> out;
        try {
            out = Files.lines(filename).filter(line -> !line.contains(concreteUserLogin)).collect(Collectors.toList());
            if (out.size() == lines)
                throw new InexistentUserException("Usuário não encontrado.");
            else {
                Files.write(filename, out, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
            }
        } catch (IOException e) {
            throw new FileException("Não foi possível alterar o arquivo.", e);
        }

    }

    @Override
    public void update(Object user, Object userLogin) throws InexistentUserException, FileException {
        String concreteUserLogin = (String)userLogin;
        User concreteUser = (User) user;
        int lines = countLines();
        List<String> out;
        String oldContent = "";

        try {
            out = Files.lines(filename).filter(line -> !line.contains(concreteUser.getName())).collect(Collectors.toList());
            if (out.size() == lines)
                throw new InexistentUserException("Usuário não encontrado.");
            else {
                // pega a linha que contém o nome
                String targetLine = Files.lines(this.filename).filter(line -> line.contains(concreteUser.getName())).toString();

                // guarda a linha
                oldContent = oldContent + targetLine + System.lineSeparator();


                String newContent = oldContent.replaceAll(concreteUser.getEmail(), concreteUserLogin);
                FileWriter writer = new FileWriter(this.pathname);
                writer.write(newContent);
            }
        } catch (IOException e) {
            throw new FileException("Não foi possível alterar o arquivo.", e);
        }
    }


}



