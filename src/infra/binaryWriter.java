package infra;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;

import business.model.User;



//classe responsável pela persistência (escrevendo e deletando users)
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

    public void removeUser(String userLogin) throws IOException{
        int lines = countLines();
        List<String> out = Files.lines(filename).filter(line -> !line.contains(userLogin)).collect(Collectors.toList());
        if(out.size() == lines) System.out.println("Oops");
        else {
            Files.write(filename, out, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
        }

    }

    private int countLines() throws IOException {
        int lines = 0;

        FileInputStream fis = new FileInputStream(pathname);
        byte[] buffer = new byte[1024]; // BUFFER_SIZE = 8 * 1024
        int read;

        while ((read = fis.read(buffer)) != -1) {
            for (int i = 0; i < read; i++) {
                if (buffer[i] == '\n')
                    lines++;
            }
        }

        fis.close();

        return lines;
    }



    



}



