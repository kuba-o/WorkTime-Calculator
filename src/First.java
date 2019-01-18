import java.util.*;

public class First {
    public static void main(String[] args) {
        CalendarHelper calendarHelper = new CalendarHelper();
        calendarHelper.setUp();

        FileHelper fileHelper = new FileHelper();
        fileHelper.processFile();

        List<String> fileLines = fileHelper.getFileLines();

        Calculator calculator = new Calculator();
        Date lastFireDate = fileHelper.getLastFireDate();
        int customVacationDays = fileHelper.getCustomVacationDays();
        fileHelper.updateDataFile();
        double requiredHours = calculator.calculateRequiredTotalTime(calendarHelper.getWorkingDays(), customVacationDays);
//        System.out.println(requiredHours);

        if (calendarHelper.wasNotRunCurrentMonth(lastFireDate)){
            ContentHelper.getCustomDys();
        }

        HashMap<LinesEnum, String> mapToWrite = FileHelper.getMapToWrite();
//        Iterator it = mapToWrite.entrySet().iterator();
//        while (it.hasNext()){
//            Map.Entry pair = (Map.Entry)it.next();
//            System.out.println(pair.getKey() + " = " + pair.getValue());
//        }


        //its more less OK
//        if (calendarHelper.wasRunCurrentMonth(lastFireDate)){
//
//        } else {
//            List<String> linesToWrite = fileHelper.getLinesToWrite();
//            SimpleDateFormat dateFormat = fileHelper.getDateFormat();
//            linesToWrite.add(Constants.LAST_FIRE_TIME + " " + dateFormat.format(new Date()) + "\n");
//        }

        //start program
        //read file
        //get last fire date - is it current month?
        // if not, ask for custom days
        // if yes, read custom days
        //calculate value and print
        //allow to change custom days
        //update the file

        //przerobic linie (lista stringow) w hashmape i przeniesc do contencthelper'a
    }
}
