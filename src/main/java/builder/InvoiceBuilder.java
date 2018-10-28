package builder;

import model.*;

import repositories.PriceListRepository;
import repositories.ProductRepository;
import repositories.PromotionsRepository;

import utils.emuns.PromoTypesEnum;
import utils.emuns.UnitOfMeasureEnum;
import utils.exceptions.PriceNotExistsException;
import utils.exceptions.ProductDoesNotExistException;


import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class InvoiceBuilder {

    private PriceListRepository priceListRepository;
    private ProductRepository productRepository;
    private PromotionsRepository promotionsRepository;
    private Invoice invoice;


    public InvoiceBuilder(){
        this.invoice = new Invoice();
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public InvoiceBuilder addNumber(String number){
        this.invoice.setNumber(number);
        return this;
    }

    public InvoiceBuilder addPoinOfSale(String pointOfSale){
        this.invoice.setPointOfSale(pointOfSale);
        return this;
    }

    public InvoiceBuilder addClientName(String clientName){
        this.invoice.setClientName(clientName);
        return this;
    }
    public InvoiceBuilder addLetter(String letter){
        this.invoice.setLetter(letter);
        return this;
    }

    public InvoiceBuilder addTaxCondition(String taxCondition){
        this.invoice.setTaxCondition(taxCondition);
        return this;
    }

    public InvoiceBuilder addDate(LocalDate localDate){
        this.invoice.setDate(localDate);
        return this;
    }

     public InvoiceBuilder applyPromotionsOverTotal() {
        List<Promotion> listOfPromotions = promotionsRepository.getListOfPromotions();
         for (Promotion promotion : listOfPromotions) {
            if (promotion.getPromoType().equals(PromoTypesEnum.OVER_TOTAL_BY_QUANTITY)) {
                List<InvoiceItem> invoiceItems = this.invoice.getInvoiceItems();
                int quantity = invoiceItems.stream().filter(item -> item.getProduct().getCode()==promotion.getProductCode()).mapToInt(item->item.getQuantity()).sum();
                if (quantity>= promotion.getMin() && quantity<= promotion.getMax()){
                    double discounts = invoice.getTotalWithOutDiscounts()*promotion.getPercentege()/100;
                    invoice.setTotalOfDiscounts(discounts);
                }
            }

        }
        return this;
    }


    public InvoiceBuilder addProduct(int productCode, int quantity, int times, UnitOfMeasureEnum uom) throws
            ProductDoesNotExistException, PriceNotExistsException {

        Product product = productRepository.getProduct(productCode);
        if (Objects.isNull(product)){
            throw new ProductDoesNotExistException(productCode);
        }

        PriceListItem priceListItem = priceListRepository.getItem(productCode,uom.getCode());
        if (Objects.isNull(priceListItem)){
            throw new PriceNotExistsException(productCode,uom.getCode());
        }

        double price = priceListItem.getPrice();


        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setProduct(product);
        invoiceItem.setQuantity(quantity);
        invoiceItem.setUnitPrice(price);
        invoiceItem.setTimes(times);
        invoiceItem.setUom(uom);

        invoice.getInvoiceItems().add(invoiceItem);

        return this;
    }
}
