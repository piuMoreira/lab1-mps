package infra.factory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import business.control.validation.exceptions.CustomException;
import business.control.validation.exceptions.FileException;

public abstract class BinaryWriter {

	String pathname ="";
    Path filename = null;
    
    public BinaryWriter(String path) throws FileException {
        File file = new java.io.File(path);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new FileException("Não foi possível criar o arquivo.", e);
        }

        this.filename = Paths.get(path);
        this.pathname = path;
    }
	
    public abstract void write(Object ob) throws CustomException;
    
    public abstract void remove(Object ob) throws CustomException;
    
    protected int countLines() throws FileException{
        int lines = 0;
        try{
            lines = (int)Files.lines(Paths.get(pathname)).count();
        }catch(IOException e){
            throw new FileException("Occorreu um erro ao lidar com o arquivo.", e);
        }
        

        return lines;
    }
}
