package infra;

import java.io.BufferedReader;
import java.io.FileInputStream;
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

    public User findUserByEmail(String loginData) throws InexistentUserException, FileException{

        BufferedReader bfr;
        String[] usuario;
        try {
            bfr = new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            throw new FileException("Não foi possível encontrar o arquivo.", e);
        }
        String eof = null;
    
        while (true) {
            try{
            eof = bfr.readLine();
            if (eof == null) {
                throw new InexistentUserException("Usuário não encontrado.");
            }
            usuario = eof.split("\t");
            if(usuario[0] == loginData){
                bfr.close();
                return new User(usuario[0],usuario[1]);
            }
            }catch(IOException e){
                throw new FileException("Não foi possível ler o arquivo.", e);
            }

        }
        

    }

}