package model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Invoice {
    private String number;
    private String letter;
    private String pointOfSale;
    private LocalDate date;
    private String clientName;
    private String taxCondition;
    private List<InvoiceDetail> invoiceDetails = new ArrayList<>();
    private double totalOfDiscounts;


    public double getTotalWithOutDiscounts(){
        return invoiceDetails.stream().mapToDouble(invoiceDetail -> invoiceDetail.getTotal()).sum();
    }

    public double getTotal(){
        return this.getTotalWithOutDiscounts() - this.getTotalOfDiscounts();
    }


}
