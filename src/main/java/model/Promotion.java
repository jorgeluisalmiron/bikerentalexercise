package model;

import lombok.Getter;
import lombok.Setter;
import utils.emuns.PromoTypesEnum;


@Getter
@Setter
public class Promotion {
    private String name;
    private int productCode;
    private PromoTypesEnum promoType;
    private int min;
    private int max;
    private float percentege;

}
