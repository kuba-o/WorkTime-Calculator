import java.util.Calendar;
import java.util.Date;

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

    private boolean isVacationDay(Calendar date){
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

    public boolean wasNotRunCurrentMonth(Date lastFireDate){
        return !wasRunCurrentMonth(lastFireDate);
    }

    public boolean wasRunCurrentMonth(Date lastFireDate) {
        try {
            return validateLastRunTime(lastFireDate);
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    private boolean validateLastRunTime(Date lastFireDate) throws Exception {
        boolean yearly = wasRunThisYear(lastFireDate);
        boolean monthly;
        if (yearly){
            monthly = wasRunThisMonth(lastFireDate);
            return monthly;
        } return false;
    }


    private int resolveCalendarType(String type) throws Exception {
        if (Constants.CALENDAR_MONTHLY.equals(type)){
            return Calendar.MONTH;
        } else if (Constants.CALENDAR_YEARLY.equals(type)){
            return Calendar.YEAR;
        } else {
            System.out.println(type);
            throw new Exception("typeResolver cannot resolve value");
        }
    }

    private boolean wasRunThisType(Date lastFireTime, int type){
        Calendar now = Calendar.getInstance();
        Date nowDate = new Date();
        now.setTime(nowDate);
        Calendar lastFireTimeCalendar = Calendar.getInstance();
        lastFireTimeCalendar.setTime(lastFireTime);

        return now.get(type) <= lastFireTimeCalendar.get(type);
    }

    private boolean wasRunThisYear(Date lastFireTime) throws Exception {
        int type = resolveCalendarType(Constants.CALENDAR_YEARLY);
        return wasRunThisType(lastFireTime, type);

    }

    private boolean wasRunThisMonth(Date lastFireTime) throws Exception {
        int type = resolveCalendarType(Constants.CALENDAR_MONTHLY);
        return wasRunThisType(lastFireTime, type);
    }
}
