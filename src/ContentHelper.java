import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

public class ContentHelper {
    private static HashMap<String, String> linesToWrite = new HashMap<>();
    private static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    public static void getCustomDys(){
        System.out.println("NO CUSTOM DAYS SPECIFIED FOR THIS MONTH");
        getUserInput();
    }

    public static void addCustomVacationDays(String customVacationDays){
        linesToWrite.put(Constants.CUSTOM_VACATION_DAYS, customVacationDays);
    }

    public static void getUserInput(){
        while (readKeyboard() == -1){
        }
    }

    public static Integer readKeyboard(){
        try {
            System.out.println("enter an integer");
            return Integer.valueOf(input.readLine());
        } catch (Exception e){
            return -1;
        }
    }
}
