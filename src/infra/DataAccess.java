package infra;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import business.control.validation.exceptions.FileException;
import business.control.validation.exceptions.InexistentUserException;
import business.model.User;

//Classe para acessar dados gravados.
public class DataAccess {

    String fileName;

    public DataAccess(String filePath) {
        this.fileName = filePath;
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
                    return new User(usuario[0], usuario[1]);
                }
            } catch (IOException e) {
                throw new FileException("Não foi possível ler o arquivo.", e);
            }

        }

    }

}