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
import business.control.validation.exceptions.InexistentNewsException;
import business.model.News;



//classe responsável pela persistência (escrevendo e deletando users)
public class NewsBinaryWriter {

    String pathname;
    Path filename;
    
    public NewsBinaryWriter() throws FileException{
        String path = "news.bin";
        File file = new java.io.File(path);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new FileException("Não foi possível criar o arquivo.", e);
        }

        this.filename = Paths.get(path);
        this.pathname = path;
    }

    public void writeNews(News news) throws FileException {
        byte newline[] = "\n".getBytes(StandardCharsets.UTF_8);
        byte tab[] = "\t".getBytes(StandardCharsets.UTF_8);

        byte email[] = news.getCreatedBy().getEmail().getBytes(StandardCharsets.UTF_8);
        byte title[] = news.getTitle().getBytes(StandardCharsets.UTF_8);

        try {
            Files.write(filename, title, StandardOpenOption.APPEND);
            Files.write(filename, tab, StandardOpenOption.APPEND);
            Files.write(filename, email, StandardOpenOption.APPEND);
            Files.write(filename, newline, StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new FileException("Não foi possível escrever no arquivo.", e);
        }

    }

    public void removeNews(News news) throws InexistentNewsException, FileException {
        int lineNb = countLines();
        List<String> out;
        try {
            out = Files.lines(filename).filter(line -> (!line.split("\t")[0].equals(news.getTitle()) || !line.split("\t")[1].equals(news.getCreatedBy().getEmail()))).collect(Collectors.toList());           
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



