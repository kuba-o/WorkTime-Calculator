import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class FileHelper {
    private static final Logger LOGGER = Logger.getLogger( FileHelper.class.getName() );
    private BufferedReader bufferedReader = null;
    private List<String> fileLines = new ArrayList<>();
    private SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.DATE_FORMAT_PATTERN);
    private static HashMap<LinesEnum, String> mapToWrite = new HashMap<>();

    public static HashMap<LinesEnum, String> getMapToWrite() {
        return mapToWrite;
    }

    public static void setMapToWrite(HashMap<LinesEnum, String> mapToWrite) {
        FileHelper.mapToWrite = mapToWrite;
    }

    public SimpleDateFormat getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(SimpleDateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    public void processFile(){
//        LOGGER.logp(Level.INFO,  FileHelper.class.getName(), "processFile", "msg");
        initializeFile();
        readFile();
        closeStream();
        addToMap();
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
            System.out.println(Constants.PROBLEM_WITH_READING_FILE);
            e.printStackTrace();
        }
    }

    public Date getLastFireDate(){
        List<String> collect = fileLines.stream().filter(e -> e.startsWith(Constants.LAST_FIRE_TIME)).collect(Collectors.toList());
        int dataDates = collect.size();
        Date dateNow = new Date();

        mapToWrite.put(LinesEnum.LAST_FIRE_TIME, dateFormat.format(dateNow)+ "\n");
        try {
            if (dataDates == 1) {
                String dateStringFromFile = collect.get(0);
                dateStringFromFile = dateStringFromFile.replaceFirst(Constants.LAST_FIRE_TIME, "").trim();
                return dateFormat.parse(dateStringFromFile);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        if (dataDates > 1){
            System.out.println(Constants.TOO_MANY_LAST_FIRE_DATE);
        } else {
            System.out.println(Constants.MISSING_LAST_FIRE_DATE);
        }
        return dateNow;
    }

    public int getCustomVacationDays(){
        List<String> collect = fileLines.stream().filter(e -> e.startsWith(Constants.CUSTOM_VACATION_DAYS)).collect(Collectors.toList());;
        if (collect.size() == 1) {
            ContentHelper.addCustomVacationDays(collect.get(0).replaceFirst(Constants.CUSTOM_VACATION_DAYS, "").trim());
            return Integer.valueOf(collect.get(0).replaceFirst(Constants.CUSTOM_VACATION_DAYS, "").trim());
        }

        int size = collect.size();

        if (size > 1){
            System.out.println(Constants.TOO_MANY_CUSTOM_VACATION_DAYS);
        } else {
            System.out.println(Constants.MISSING_CUSTOM_VACATION_DAYS);
        }
        mapToWrite.put(LinesEnum.CUSTOM_VACATION_DAYS, "5");
        return 0;
    }

    public void writeToFileFromMap(){
        removeLastNewLine(mapToWrite);
        FileWriter fileWriter = null;
        Iterator it = mapToWrite.entrySet().iterator();

        try {
            fileWriter = new FileWriter(Constants.FILE_NAME);
            while (it.hasNext()){
                Map.Entry pair = (Map.Entry)it.next();
                LinesEnum linesEnum = (LinesEnum) pair.getKey();
                String value = (String) pair.getValue();
                fileWriter.write(linesEnum.getValue() + value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (Objects.nonNull(fileWriter)){
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void updateDataFile(){
        writeToFileFromMap();
    }

    private void removeLastNewLine(HashMap<LinesEnum, String> mapToWrite){
        Iterator<Map.Entry<LinesEnum, String>> entries = mapToWrite.entrySet().iterator();
        Map.Entry<LinesEnum, String> entry =  null;
        while (entries.hasNext())
        {
            entries.next();
            entry = entries.next();
        }
        LinesEnum mapKey=entry.getKey();
        mapToWrite.put(mapKey, mapToWrite.get(mapKey).replaceFirst("\\n", ""));
    }

    private void addToMap(){
        for (String fileLine : fileLines){
            resolveLineStartWithEnum(fileLine);
        }
    }

    private void resolveLineStartWithEnum(String line){
        for (LinesEnum linesEnum : LinesEnum.values()){
            if (line.startsWith(linesEnum.getValue())){
                mapToWrite.put(linesEnum, line.replaceFirst(linesEnum.getValue(), "") + "\n");
            }
        }
    }
}
