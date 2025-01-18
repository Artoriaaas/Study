package DataObject;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;

/**
 *
 * @author SwordLake
 * @param <T>
 */
public class FileManager {

    private String fileName;

    public FileManager() {
    }

    //--------------------------------------------------        
    public FileManager(String fileName) {
        this.fileName = fileName;
    }

    //--------------------------------------------------   
    public List<String> readDataFromFile() throws IOException {
        List<String> result;
        result = Files.readAllLines(new File(fileName).toPath());
        return result;
    }

    //--------------------------------------------------   
    public void saveDataToFile(String data) throws IOException {
        Files.write(new File(fileName).toPath(), data.getBytes());
        //to do here 
    }
}
