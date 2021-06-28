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
import business.control.validation.exceptions.InexistentNewsException;
import business.model.News;



//classe responsável pela persistência (escrevendo e deletando users)
public class NewsBinaryWriter extends BinaryWriter {
    
    public NewsBinaryWriter() throws FileException {
        super("news.bin");
    }

    public void write(Object news) throws FileException {
        byte newline[] = "\n".getBytes(StandardCharsets.UTF_8);
        byte tab[] = "\t".getBytes(StandardCharsets.UTF_8);

        byte email[] = ((News) news).getCreatedBy().getEmail().getBytes(StandardCharsets.UTF_8);
        byte title[] = ((News) news).getTitle().getBytes(StandardCharsets.UTF_8);

        try {
            Files.write(filename, title, StandardOpenOption.APPEND);
            Files.write(filename, tab, StandardOpenOption.APPEND);
            Files.write(filename, email, StandardOpenOption.APPEND);
            Files.write(filename, newline, StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new FileException("Não foi possível escrever no arquivo.", e);
        }

    }

    public void remove(Object news) throws InexistentNewsException, FileException {
        int lineNb = countLines();
        List<String> out;
        try {
            out = Files.lines(filename).filter(line -> (!line.split("\t")[0].equals(((News) news).getTitle()) || !line.split("\t")[1].equals(((News) news).getCreatedBy().getEmail()))).collect(Collectors.toList());           
            System.out.println(out.get(0).split("\t")[1]);
            if (out.size() == lineNb)
                throw new InexistentNewsException("Notícia não encontrada.");
            else {
                Files.write(filename, out, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
            }
        } catch (IOException e) {
            throw new FileException("Não foi possível alterar o arquivo.", e);
        }

    }


}



