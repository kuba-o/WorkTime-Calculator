import java.text.DecimalFormat;

public class Calculator {

    public double calculateRequiredTotalTime(int workingDays, int customVacationDays) {
        return calculateTimeProduct(workingDays - customVacationDays);
    }

    private double calculateTimeProduct(int days){
        double workTimePercentage = Constants.WORK_TIME_PERCENTAGE;
        double workingHoursPerDay = Constants.WORK_HOURS_PER_DAY;
        DecimalFormat df = new DecimalFormat(Constants.DECIMAL_FORMAT_PATTERN);
        double totalTime = days * workingHoursPerDay * workTimePercentage;
        return Double.valueOf(df.format(totalTime));
    }
}
