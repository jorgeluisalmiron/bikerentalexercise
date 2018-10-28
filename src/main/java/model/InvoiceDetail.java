package model;

import lombok.Getter;
import lombok.Setter;
import utils.emuns.UnitOfMeasureEnum;

@Setter
@Getter
public class InvoiceDetail {

    private Product product;
    private UnitOfMeasureEnum uom;
    private int times;
    private int quantity;
    private double unitPrice;

    public double getTotal(){
        return quantity * times * unitPrice;
    }



}
