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
    private InvoiceDetail invoiceDetail;

    @Before
    public void setUp(){
        product = new Product();
        product.setCode(1);
        product.setName("Generic Bike");
        product.setCategory("BIKES");

        invoiceDetail = new InvoiceDetail();
        invoiceDetail.setProduct(product);
    }
    @Test
    public void testGetters(){
        final double exceptedTotal = UNIT_PRICE * TIMES * QUANTITY;
        invoiceDetail.setUnitPrice(UNIT_PRICE);
        invoiceDetail.setTimes(TIMES);
        invoiceDetail.setQuantity(QUANTITY);
        assertEquals(invoiceDetail.getProduct(),this.product);
        assertEquals(invoiceDetail.getQuantity(), QUANTITY);
        assertEquals(invoiceDetail.getTimes(), TIMES);
        assertEquals(invoiceDetail.getUnitPrice(), UNIT_PRICE,0);
        assertEquals(invoiceDetail.getTotal(),exceptedTotal,0);
    }
}
