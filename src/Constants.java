public final class Constants {
    private Constants() {
        // restrict instantiation
    }

    public static final String FILE_NAME = "fileName.txt";
    public static final String NO_SUCH_FILE = "No such file";
    public static final String PROBLEM_WITH_READING_FILE = "Problem with reading file";
    public static final String WRONG_DATA = "Wrong data in the file.";
    public static final String LAST_FIRE_TIME = "Last fire time:";
    public static final String CUSTOM_VACATION_DAYS = "Custom vacation days: ";
    public static final String TOO_MANY_CUSTOM_VACATION_DAYS = "Too many \"Custom vacation days:\" lines";
    public static final String MISSING_CUSTOM_VACATION_DAYS = "\"Custom vacation days:\" missing in file, adding now";
    public static final String MISSING_LAST_FIRE_DATE = "\"Last fire date\" missing in file, adding now";
    public static final String TOO_MANY_LAST_FIRE_DATE = "Too many last fire dates, cleaning up";
    public static final double WORK_TIME_PERCENTAGE = 0.6;
    public static final double WORK_HOURS_PER_DAY = 8.0;
    public static final String CALENDAR_MONTHLY = "monthly";
    public static final String CALENDAR_YEARLY = "yearly";
    public static final String DATE_FORMAT_PATTERN = "yyyy-MM-dd";
    public static final String DECIMAL_FORMAT_PATTERN = "#.##";
}
