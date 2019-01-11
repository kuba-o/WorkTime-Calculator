import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileHelper {
    private BufferedReader bufferedReader = null;
    private List<String> fileLines = new ArrayList<>();

    public void processFile(){
        initializeFile();
        readFile();
        closeStream();
    }

    public List<String> getFileLines() {
        return fileLines;
    }

    public void setFileLines(List<String> fileLines) {
        this.fileLines = fileLines;
    }

    public void initializeFile(){
        try {
            FileReader fileReader = new FileReader(Constants.FILE_NAME);
            bufferedReader = new BufferedReader(fileReader);
        } catch (Exception e) {
            System.out.println(Constants.NO_SUCH_FILE);
            e.printStackTrace();
        }
    }

    public void readFile(){
        try {
            String line;
            while((line = bufferedReader.readLine()) != null) {
                fileLines.add(line);
            }
        } catch (IOException e) {
            System.out.println(Constants.NO_SUCH_FILE);
            e.printStackTrace();
        }
    }

    public void closeStream(){
        try {
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
