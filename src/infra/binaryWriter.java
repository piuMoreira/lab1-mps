import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

//classe que escreve a lista de usuários em um arquivo binário

public class binaryWriter {
    public binaryWriter() {

    }

    public void writeUserList(List<String> users) throws IOException {
        Path filename = Paths.get("users.bin");
        
        
        for(String user : users){
            byte bytes[] = user.getBytes(StandardCharsets.UTF_8);
            byte newline[] = "\n".getBytes(StandardCharsets.UTF_8);
            Files.write(filename, bytes, StandardOpenOption.APPEND);
            Files.write(filename, newline, StandardOpenOption.APPEND);
        }

        // TODO: mudar tipo de de users para List<User> e escrever os parametros corretamente
        // TODO: lidar com a exceção corretamente

    }

}
