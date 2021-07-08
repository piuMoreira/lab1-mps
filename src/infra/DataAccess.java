package infra;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import business.control.validation.exceptions.CustomException;
import business.control.validation.exceptions.FileException;
import business.control.validation.exceptions.InexistentUserException;
import business.model.User;
import business.model.News;
import business.model.Announcement;

//Classe para acessar dados gravados.
public class DataAccess {

    String fileName;
    SimpleDateFormat dateFormat;

    public DataAccess(String filePath) {
        this.fileName = filePath;
        this.dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    }

    public DataAccess() {
        this.fileName = "users.bin";
    }

    public User findUserByEmail(String loginData) throws InexistentUserException, FileException {

        BufferedReader bfr;
        String[] usuario;
        try {
            bfr = new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            throw new FileException("Não foi possível encontrar o arquivo.", e);
        }
        String eof = null;

        int i = 0;
        while (true) {
            try {
                i++;
                System.out.println(i);
                eof = bfr.readLine();
                if (eof == null) {
                    throw new InexistentUserException("Usuário não encontrado.");
                }
                usuario = eof.split("\t");
                System.out.println(usuario[0] + " " + usuario[1]);
                if (usuario[0].equals(loginData)) {
                    bfr.close();
                    return new User(usuario[0], usuario[1], usuario[2]);
                }
            } catch (IOException e) {
                throw new FileException("Não foi possível ler o arquivo.", e);
            }

        }

    }

    public News findNewsByUser(String loginData) throws InexistentUserException, FileException, CustomException {

        BufferedReader bfr;
        String[] usuario;
        try {
            bfr = new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            throw new FileException("Não foi possível encontrar o arquivo.", e);
        }
        String eof = null;

        int i = 0;
        while (true) {
            try {
                i++;
                System.out.println(i);
                eof = bfr.readLine();
                if (eof == null) {
                    throw new InexistentUserException("Usuário não encontrado.");
                }
                usuario = eof.split("\t");
                System.out.println(usuario[0] + " " + usuario[1]);
                if (usuario[2].equals(loginData)) {
                    bfr.close();
                    User thisUser = new User(usuario[1], usuario[2], "irrelevante");
                    return new News(thisUser, usuario[0]);
                }
            } catch (IOException e) {
                throw new FileException("Não foi possível ler o arquivo.", e);
            }

        }

    }

    public News findNewsByTitle(String title) throws InexistentUserException, FileException, CustomException {

        BufferedReader bfr;
        String[] usuario;
        try {
            bfr = new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            throw new FileException("Não foi possível encontrar o arquivo.", e);
        }
        String eof = null;

        int i = 0;
        while (true) {
            try {
                i++;
                System.out.println(i);
                eof = bfr.readLine();
                if (eof == null) {
                    throw new InexistentUserException("Usuário não encontrado.");
                }
                usuario = eof.split("\t");
                System.out.println(usuario[0] + " " + usuario[1]);
                if (usuario[0].equals(title)) {
                    bfr.close();
                    User thisUser = new User(usuario[1], usuario[2], "irrelevante");
                    return new News(thisUser, usuario[0]);
                }
            } catch (IOException e) {
                throw new FileException("Não foi possível ler o arquivo.", e);
            }

        }

    }

    public Announcement findAnnouncementByTitle(String title) throws InexistentUserException, FileException, ParseException {

        BufferedReader bfr;
        String[] usuario;
        try {
            bfr = new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            throw new FileException("Não foi possível encontrar o arquivo.", e);
        }
        String eof = null;

        int i = 0;
        while (true) {
            try {
                i++;
                System.out.println(i);
                eof = bfr.readLine();
                if (eof == null) {
                    throw new InexistentUserException("Usuário não encontrado.");
                }
                usuario = eof.split("\t");
                System.out.println(usuario[0] + " " + usuario[1]);
                if (usuario[0].equals(title)) {
                    bfr.close();
                    User thisUser = new User(usuario[1], usuario[2], "irrelevante");
                    return new Announcement(thisUser, usuario[0],dateFormat.parse(usuario[2]));
                }
            } catch (IOException e) {
                throw new FileException("Não foi possível ler o arquivo.", e);
            }

        }

    }

    public Announcement findAnnouncementByUser(String loginData) throws InexistentUserException, FileException, ParseException {

        BufferedReader bfr;
        String[] usuario;
        try {
            bfr = new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            throw new FileException("Não foi possível encontrar o arquivo.", e);
        }
        String eof = null;

        int i = 0;
        while (true) {
            try {
                i++;
                System.out.println(i);
                eof = bfr.readLine();
                if (eof == null) {
                    throw new InexistentUserException("Usuário não encontrado.");
                }
                usuario = eof.split("\t");
                System.out.println(usuario[0] + " " + usuario[1]);
                if (usuario[2].equals(loginData)) {
                    bfr.close();
                    User thisUser = new User(usuario[1], usuario[2], "irrelevante");
                    return new Announcement(thisUser, usuario[0],dateFormat.parse(usuario[2]));
                }
            } catch (IOException e) {
                throw new FileException("Não foi possível ler o arquivo.", e);
            }

        }

    }


}