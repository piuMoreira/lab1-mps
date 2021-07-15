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
import business.control.validation.exceptions.CustomException;
import business.control.validation.exceptions.FileException;
import business.control.validation.exceptions.InexistentNewsException;
import business.model.Announcement;
import business.model.News;



//classe responsável pela persistência (escrevendo e deletando users)
public class NewsBinaryWriter extends BinaryWriter {
    
    public NewsBinaryWriter() throws FileException {
        super("news.bin");
    }

    public void write(Object news) throws FileException {    	
    	News concreteNews = (News) news;
    	
        byte newline[] = "\n".getBytes(StandardCharsets.UTF_8);
        byte tab[] = "\t".getBytes(StandardCharsets.UTF_8);

        byte email[] = concreteNews.getCreatedBy().getEmail().getBytes(StandardCharsets.UTF_8);
        byte name[] = concreteNews.getCreatedBy().getName().getBytes(StandardCharsets.UTF_8);
        byte title[] = concreteNews.getTitle().getBytes(StandardCharsets.UTF_8);

        try {
            Files.write(filename, title, StandardOpenOption.APPEND);
            Files.write(filename, tab, StandardOpenOption.APPEND);
            Files.write(filename, name, StandardOpenOption.APPEND);
            Files.write(filename, tab, StandardOpenOption.APPEND);
            Files.write(filename, email, StandardOpenOption.APPEND);
            Files.write(filename, newline, StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new FileException("Não foi possível escrever no arquivo.", e);
        }

    }

    public void remove(Object news) throws InexistentNewsException, FileException {
    	News concreteNews = (News) news;
    	
        int lineNb = countLines();
        List<String> out;
        try {
            out = Files.lines(filename).filter(line -> (!line.split("\t")[0].equals(concreteNews.getTitle()) || !line.split("\t")[1].equals(concreteNews.getCreatedBy().getEmail()))).collect(Collectors.toList());           
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

    @Override
    public void update(Object news, Object ob) throws CustomException {
        //
    }
    
    public void removeAll(Object news) throws FileException {
    	News concreteNews = (News) news;
    	
		List<String> filtered;
		try {
			
			filtered = Files.lines(filename).filter(x -> !x.contains(concreteNews.getCreatedBy().getEmail()))
					.collect(Collectors.toList());
			Files.write(filename, filtered, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
		} catch (IOException e) {
			throw new FileException("Não foi possível alterar o arquivo.", e);
		}
    }


}



