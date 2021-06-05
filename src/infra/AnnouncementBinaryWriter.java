package infra;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

// import business.control.validation.exceptions.CustomException;
import business.control.validation.exceptions.FileException;
import business.control.validation.exceptions.InexistentAnnouncementException;
import business.model.Announcement;



//classe responsável pela persistência (escrevendo e deletando announcements)
public class AnnouncementBinaryWriter {

    String pathname;
    Path filename;
    SimpleDateFormat dateFormat;
    
    public AnnouncementBinaryWriter() throws FileException{
        String path = "announcement.bin";
        File file = new java.io.File(path);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new FileException("Não foi possível criar o arquivo.", e);
        }

        this.filename = Paths.get(path);
        this.pathname = path;
        this.dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    }

    public void writeAnnouncement(Announcement announ) throws FileException {
        byte newline[] = "\n".getBytes(StandardCharsets.UTF_8);
        byte tab[] = "\t".getBytes(StandardCharsets.UTF_8);

        byte email[] = announ.getCreatedBy().getEmail().getBytes(StandardCharsets.UTF_8);
        byte title[] = announ.getTitle().getBytes(StandardCharsets.UTF_8);
        byte date[] = dateFormat.format(announ.getCreatedAt()).getBytes(StandardCharsets.UTF_8);

        try {
            Files.write(filename, title, StandardOpenOption.APPEND);
            Files.write(filename, tab, StandardOpenOption.APPEND);
            Files.write(filename, email, StandardOpenOption.APPEND);
            Files.write(filename, tab, StandardOpenOption.APPEND);
            Files.write(filename, date, StandardOpenOption.APPEND);
            Files.write(filename, newline, StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new FileException("Não foi possível escrever no arquivo.", e);
        }

    }

    public void removeNews(Announcement announ) throws InexistentAnnouncementException, FileException {
        int lines = countLines();
        List<String> out;
        try {
            out = Files.lines(filename).filter(line -> (!line.contains(announ.getCreatedBy().getEmail()) && !line.contains(dateFormat.format(announ.getCreatedAt())) && !line.contains(announ.getTitle()))).collect(Collectors.toList());
            if (out.size() == lines)
                throw new InexistentAnnouncementException("Anúncio não encontrado.");
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



