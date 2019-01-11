import java.text.DecimalFormat;

public class Calculator {

    public double calculateRequiredTotalTime(int workingDays, int customVacationDays) {
        return calculateTimeProduct(workingDays - customVacationDays);
    }
    public double calculateMonthlyRequiredHours(int workingDays){
        return calculateTimeProduct(workingDays);
    }

    public double calculateCustomVacationHours(int customVacationDays){
        return calculateTimeProduct(customVacationDays);
    }

    private double calculateTimeProduct(int days){
        double workTimePercentage = Constants.WORK_TIME_PERCENTAGE;
        double workingHoursPerDay = Constants.WORK_HOURS_PER_DAY;
        DecimalFormat df = new DecimalFormat("#.##");
        double totalTime = days * workingHoursPerDay * workTimePercentage;
        return Double.valueOf(df.format(totalTime));
    }
}
