import java.util.List;

public class First {
    public static void main(String[] args) {
        int customVacationDays = 1;
        CalendarHelper calendarHelper = new CalendarHelper();
        calendarHelper.setUp();

        FileHelper fileHelper = new FileHelper();
        fileHelper.processFile();

        List<String> fileLines = fileHelper.getFileLines();

        fileLines.forEach(System.out::println);

        Calculator calculator = new Calculator();
        System.out.println(calculator.calculateRequiredTotalTime(calendarHelper.getWorkingDays(), customVacationDays));
//        calendarHelper.setMonthlyRequiredHours(calculator.calculateMonthlyRequiredHours(calendarHelper.getWorkingDays()));
//        System.out.println(calendarHelper.getMonthlyRequiredHours());
//
//        calendarHelper.setCustomVacationHours(calculator.calculateCustomVacationHours(customVacationDays));
//        System.out.println(calendarHelper.getCustomVacationHours());
//        System.out.println(calendarHelper.getMonthlyRequiredHours() - calendarHelper.getCustomVacationHours());

//        System.out.println(calendarHelper.getWorkingDays());
//        System.out.println(calendarHelper.getVacationDays());
    }
}
