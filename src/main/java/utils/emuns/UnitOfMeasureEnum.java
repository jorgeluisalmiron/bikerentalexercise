package utils.emuns;

public enum UnitOfMeasureEnum {
    HOUR("hour"),
    DAY("day"),
    WEEK("week");

    private String code;

    UnitOfMeasureEnum(String code) {
        this.code = code;
    }

    public String getCode() { return code; }

    public static UnitOfMeasureEnum get(String code) {
        for(UnitOfMeasureEnum s : values()) {
            if(s.code == code) return s;
        }
        return null;
    }

}
