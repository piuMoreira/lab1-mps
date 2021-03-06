package infra.factory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

// import business.control.validation.exceptions.CustomException;
import business.control.validation.exceptions.CustomException;
import business.control.validation.exceptions.FileException;
import business.control.validation.exceptions.InexistentAnnouncementException;
import business.model.Announcement;

//classe responsável pela persistência (escrevendo e deletando announcements)
public class AnnouncementBinaryWriter extends BinaryWriter {

	SimpleDateFormat dateFormat;

	public AnnouncementBinaryWriter() throws FileException {
		super("announcement.bin");

		this.dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}

	public void write(Object announ) throws FileException {
		Announcement concreteAnnoun = (Announcement) announ;

		byte newline[] = "\n".getBytes(StandardCharsets.UTF_8);
		byte tab[] = "\t".getBytes(StandardCharsets.UTF_8);

		byte email[] = concreteAnnoun.getCreatedBy().getEmail().getBytes(StandardCharsets.UTF_8);
		byte name[] = concreteAnnoun.getCreatedBy().getName().getBytes(StandardCharsets.UTF_8);
		byte title[] = concreteAnnoun.getTitle().getBytes(StandardCharsets.UTF_8);
		byte date[] = dateFormat.format(concreteAnnoun.getCreatedAt()).getBytes(StandardCharsets.UTF_8);

		try {
			Files.write(filename, title, StandardOpenOption.APPEND);
			Files.write(filename, tab, StandardOpenOption.APPEND);
			Files.write(filename, name, StandardOpenOption.APPEND);
			Files.write(filename, tab, StandardOpenOption.APPEND);
			Files.write(filename, email, StandardOpenOption.APPEND);
			Files.write(filename, tab, StandardOpenOption.APPEND);
			Files.write(filename, date, StandardOpenOption.APPEND);
			Files.write(filename, newline, StandardOpenOption.APPEND);
		} catch (IOException e) {
			throw new FileException("Não foi possível escrever no arquivo.", e);
		}

	}

	public void remove(Object announ) throws InexistentAnnouncementException, FileException {
		Announcement concreteAnnoun = (Announcement) announ;

		int lines = countLines();
		List<String> out;
		try {
			out = Files.lines(filename)
					.filter(line -> (!line.split("\t")[1].equals(concreteAnnoun.getCreatedBy().getEmail())
							|| !line.split("\t")[2].equals(dateFormat.format(concreteAnnoun.getCreatedAt()))
							|| !line.split("\t")[0].equals(concreteAnnoun.getTitle())))
					.collect(Collectors.toList());
			if (out.size() == lines)
				throw new InexistentAnnouncementException("Anúncio não encontrado.");
			else {
				Files.write(filename, out, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
			}
		} catch (IOException e) {
			throw new FileException("Não foi possível alterar o arquivo.", e);
		}

	}

	@Override
	public void update(Object announcemnt, Object ob) throws CustomException {
		//
	}

	public void removeAll(Object announ) throws FileException {
		Announcement concreteAnnoun = (Announcement) announ;
		
		List<String> filtered;
		try {
			
			filtered = Files.lines(filename).filter(x -> !x.contains(concreteAnnoun.getCreatedBy().getEmail()))
					.collect(Collectors.toList());
			Files.write(filename, filtered, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
		} catch (IOException e) {
			throw new FileException("Não foi possível alterar o arquivo.", e);
		}		
	}

}
