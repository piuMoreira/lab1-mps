package infra;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
public class BinaryWriter {
    private Path filename;
    private String pathname;



    public BinaryWriter() throws Exception{
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

    public void writeUserList(List<User> users) throws Exception{       
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

    public void removeUser(String userLogin) throws Exception {
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

    private int countLines() throws Exception{
        int lines = 0;

        FileInputStream fis;
        try {
            fis = new FileInputStream(pathname);
        } catch (FileNotFoundException e) {
            throw new FileException("Não foi possível encontrar o arquivo.", e);
        }
        byte[] buffer = new byte[1024]; // BUFFER_SIZE = 8 * 1024
        int read;

        try {
            while ((read = fis.read(buffer)) != -1) {
                for (int i = 0; i < read; i++) {
                    if (buffer[i] == '\n')
                        lines++;
                }
            }
        } catch (IOException e) {
            throw new FileException("Não foi possível ler o arquivo.", e);
        }

        try {
            fis.close();
        } catch (Exception e) {
            throw new FileException("Não foi possível fechar o arquivo.", e);
        }
        

        return lines;
    }





    



}



