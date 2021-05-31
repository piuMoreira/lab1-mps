package infra;

import java.io.DataInputStream;
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
import business.model.User;



//classe que escreve a lista de usuários em um arquivo binário

public class binaryWriter {
    private Path filename;
    private String pathname;



    public binaryWriter() throws IOException{
        String path = "users.bin";
        File file = new java.io.File(path);
        file.createNewFile();

        this.filename = Paths.get(path);
        this.pathname = path;
    }

    public void writeUserList(List<User> users) throws IOException{       
        
        
        for(User user : users){
            
            byte email[] = user.getEmail().getBytes(StandardCharsets.UTF_8);
            byte newline[] = "\n".getBytes(StandardCharsets.UTF_8);
            Files.write(filename, email, StandardOpenOption.APPEND);
            Files.write(filename, newline, StandardOpenOption.APPEND);
        }

    }

    public void removeUser(String userLogin) throws FileNotFoundException{
        DataInputStream in = new DataInputStream(new FileInputStream(pathname));

    }



    



}



