package utils.emuns;

public enum PromoTypesEnum {
    OVER_TOTAL_BY_QUANTITY("over_total_by_quantity"),
    OVER_TOTAL_BY_TIMES("over_total_by_times"),
    OVER_TOTAL_BY_QUANTITY_AND_TIMES("over_total_by_quantity_and_times");

    private String code;

    PromoTypesEnum(String code) {
        this.code = code;
    }

    public String getCode() { return code; }

    public static PromoTypesEnum get(String code) {
        for(PromoTypesEnum s : values()) {
            if(s.code == code) return s;
        }
        return null;
    }

}
