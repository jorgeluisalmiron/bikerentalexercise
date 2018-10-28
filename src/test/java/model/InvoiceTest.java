package model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class InvoiceTest {

    private static final double UNIT_PRICE_ITEM_1 = 10;
    private static final double UNIT_PRICE_ITEM_2 = 30;
    private static final double DISCOUNT = 5;

    private Invoice invoice;

    @Before
    public void setUp() {
        invoice = new Invoice();
    }

    @Test
    public void testInvoiceTotalWithoutDiscounts(){
        final double expectedTotal = UNIT_PRICE_ITEM_1 + UNIT_PRICE_ITEM_2;
        invoice.setInvoiceDetails(this.getInvoiceDetailsList());
        Assert.assertEquals(invoice.getTotal(),expectedTotal,0);
        Assert.assertEquals(invoice.getTotalWithOutDiscounts(),expectedTotal,0);

    }

    @Test
    public void testInvoiceTotalWithDiscounts(){
        final double expectedTotal = UNIT_PRICE_ITEM_1 + UNIT_PRICE_ITEM_2 - DISCOUNT;
        final double expectedeTotalWithoutDiscounts = UNIT_PRICE_ITEM_1 + UNIT_PRICE_ITEM_2;
        invoice.setInvoiceDetails(this.getInvoiceDetailsList());
        invoice.setTotalOfDiscounts(DISCOUNT);
        Assert.assertEquals(invoice.getTotal(),expectedTotal,0);
        Assert.assertEquals(invoice.getTotalWithOutDiscounts(),expectedeTotalWithoutDiscounts,0);
    }


    private List<InvoiceDetail> getInvoiceDetailsList(){
        InvoiceDetail invoiceDetail1 = new InvoiceDetail();
        invoiceDetail1.setQuantity(1);
        invoiceDetail1.setTimes(1);
        invoiceDetail1.setUnitPrice(UNIT_PRICE_ITEM_1);

        InvoiceDetail invoiceDetail2= new InvoiceDetail();
        invoiceDetail2.setQuantity(1);
        invoiceDetail2.setTimes(1);
        invoiceDetail2.setUnitPrice(UNIT_PRICE_ITEM_2);

        List<InvoiceDetail> invoiceDetails = new ArrayList<>();
        invoiceDetails.add(invoiceDetail1);
        invoiceDetails.add(invoiceDetail2);

        return invoiceDetails;
    }
}
