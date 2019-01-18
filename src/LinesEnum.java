public enum LinesEnum {
    LAST_FIRE_TIME("Last fire time:"),
    CUSTOM_VACATION_DAYS("Custom vacation days: ");

    private final String value;

    LinesEnum(String s){
        value = s;
    }

    public String getValue() {
        return value;
    }
}