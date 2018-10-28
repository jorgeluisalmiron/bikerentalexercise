package model;

import lombok.Getter;
import lombok.Setter;
import utils.emuns.UnitOfMeasureEnum;

@Setter
@Getter
public class PriceListItem {
    private int productCode;
    private UnitOfMeasureEnum uom;
    private double price;
}
