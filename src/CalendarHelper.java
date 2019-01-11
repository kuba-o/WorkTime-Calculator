import java.util.Calendar;

public class CalendarHelper {

    private Calendar today;
    private Calendar iterableDay;
    private Calendar futureBoundary;
    private int workingDays;
    private int vacationDays;

    public void setUp(){
        today = Calendar.getInstance();
        iterableDay = getFirstDayOfThemonth(today);
        futureBoundary = getDateBoundary(iterableDay);
        calculateWorkigAndVacationDays();
    }

    public Calendar getToday() {
        return today;
    }

    public void setToday(Calendar today) {
        this.today = today;
    }

    public Calendar getIterableDay() {
        return iterableDay;
    }

    public void setIterableDay(Calendar iterableDay) {
        this.iterableDay = iterableDay;
    }

    public Calendar getFutureBoundary() {
        return futureBoundary;
    }

    public void setFutureBoundary(Calendar futureBoundary) {
        this.futureBoundary = futureBoundary;
    }

    public int getWorkingDays() {
        return workingDays;
    }

    public void setWorkingDays(int workingDays) {
        this.workingDays = workingDays;
    }

    public int getVacationDays() {
        return vacationDays;
    }

    public void setVacationDays(int vacationDays) {
        this.vacationDays = vacationDays;
    }

    private Calendar getFirstDayOfThemonth(Calendar today){
        Calendar day = Calendar.getInstance();
        day.set(today.get(Calendar.YEAR), today.get(Calendar.MONTH), 1, 0, 0, 0);
        return day;
    }

    private Calendar getDateBoundary(Calendar iterableDay) {
        Calendar day = Calendar.getInstance();
        day.set(iterableDay.get(Calendar.YEAR), iterableDay.get(Calendar.MONTH)+1, 1, 0, 0, 0);
        return day;
    }

    public boolean isVacationDay(Calendar date){
        return (date.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || date.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY);
    }

    private void calculateWorkigAndVacationDays() {
        while (getIterableDay().before(getFutureBoundary())){
            if (isVacationDay(getIterableDay())){
                vacationDays++;
            } else {
                workingDays++;
            }
            getIterableDay().roll(Calendar.DAY_OF_YEAR, 1);
        }
    }
}
