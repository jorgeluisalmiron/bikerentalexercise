package model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;


@RunWith(MockitoJUnitRunner.class)
public class InvoiceDetailTest {

    private static final double UNIT_PRICE = 10.5;
    private static final int TIMES = 2;
    private static final int QUANTITY = 2;
    private Product product;
    private InvoiceItem invoiceItem;

    @Before
    public void setUp(){
        product = new Product();
        product.setCode(1);
        product.setName("Generic Bike");
        product.setCategory("BIKES");

        invoiceItem = new InvoiceItem();
        invoiceItem.setProduct(product);
    }
    @Test
    public void testGetters(){
        final double exceptedTotal = UNIT_PRICE * TIMES * QUANTITY;
        invoiceItem.setUnitPrice(UNIT_PRICE);
        invoiceItem.setTimes(TIMES);
        invoiceItem.setQuantity(QUANTITY);
        assertEquals(invoiceItem.getProduct(),this.product);
        assertEquals(invoiceItem.getQuantity(), QUANTITY);
        assertEquals(invoiceItem.getTimes(), TIMES);
        assertEquals(invoiceItem.getUnitPrice(), UNIT_PRICE,0);
        assertEquals(invoiceItem.getTotal(),exceptedTotal,0);
    }
}
