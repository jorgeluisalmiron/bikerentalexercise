package invoice;

import model.Invoice;
import model.PriceListItem;
import model.Product;
import model.Promotion;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import repositories.PriceListRepository;
import repositories.ProductRepository;
import repositories.PromotionsRepository;
import builder.InvoiceBuilder;
import utils.emuns.PromoTypesEnum;
import utils.emuns.UnitOfMeasureEnum;
import utils.exceptions.PriceNotExistsException;
import utils.exceptions.ProductDoesNotExistException;

import java.time.LocalDate;

import java.util.ArrayList;

import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.Assert.*;


@RunWith(MockitoJUnitRunner.class)
public class InvoiceBuilderTest {

    private static final String INVOICE_NUMBER = "90067341";
    private static final String INVOICE_POINT_OF_SALE = "0001";
    private static final String INVOICE_LETTER = "C";
    private static final String INVOICE_TAX_CONDITION = "RI";
    private static final String INVOICE_CLIENT_NAME = "Johnny B Good";
    private static final LocalDate INVOICE_DATE = LocalDate.of(2018, 10, 28);
    @Mock
    private PriceListRepository priceListRepository;


    @Mock
    private PromotionsRepository promotionsRepository;


    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private InvoiceBuilder invoiceBuilder = new InvoiceBuilder();

    private Product product;

    @Before
    public void setUp() {

        product = new Product();
        product.setCode(1);
        product.setName("Generic Bike");
        product.setCategory("BIKES");

        invoiceBuilder.addNumber(INVOICE_NUMBER)
                    .addPoinOfSale(INVOICE_POINT_OF_SALE)
                    .addLetter(INVOICE_LETTER)
                    .addDate(INVOICE_DATE)
                    .addTaxCondition(INVOICE_TAX_CONDITION)
                    .addClientName(INVOICE_CLIENT_NAME);

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRentOneBikeForADayExpectedNoPromotion() {
        final double exceptedPrice = 20;
        final double exceptedDiscount = 0;
        final int times = 1;
        final int quantity = 1;
        when(priceListRepository.getItem(product.getCode(),UnitOfMeasureEnum.DAY.getCode())).thenReturn(getPriceByDay());
        when(productRepository.getProduct(product.getCode())).thenReturn(product);
        when(promotionsRepository.getListOfPromotions()).thenReturn(getPromotionsList());
        try {
            invoiceBuilder.addProduct(product.getCode(),quantity,times,UnitOfMeasureEnum.DAY)
                        .applyPromotionsOverTotal();

            verify(productRepository, times(1)).getProduct(product.getCode());
            Invoice invoice = invoiceBuilder.getInvoice();
            assertEquals(invoice.getTotal(),exceptedPrice,0);
            assertEquals(invoice.getTotalOfDiscounts(),exceptedDiscount,0);

        }
        catch (PriceNotExistsException e) {
            Assert.fail();
        }
        catch (ProductDoesNotExistException e) {
            Assert.fail();
        }
        catch (Exception e) {
            Assert.fail();
        }
    }


    @Test
    public void testRentTwoBikeForThreeDaysExpectedNoPromotion() {
        final double exceptedPrice = 120;
        final double exceptedDiscount = 0;
        final int times = 3;
        final int quantity = 2;
        when(priceListRepository.getItem(product.getCode(),UnitOfMeasureEnum.DAY.getCode())).thenReturn(getPriceByDay());
        when(productRepository.getProduct(product.getCode())).thenReturn(product);
        when(promotionsRepository.getListOfPromotions()).thenReturn(getPromotionsList());
        try {
            invoiceBuilder.addProduct(product.getCode(),quantity,times,UnitOfMeasureEnum.DAY)
                        .applyPromotionsOverTotal();

            verify(productRepository, times(1)).getProduct(product.getCode());
            Invoice invoice = invoiceBuilder.getInvoice();
            assertEquals(invoice.getTotal(),exceptedPrice,0);
            assertEquals(invoice.getTotalOfDiscounts(),exceptedDiscount,0);

        }
        catch (PriceNotExistsException e) {
            Assert.fail();
        }
        catch (ProductDoesNotExistException e) {
            Assert.fail();
        }
        catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void testRentTwoBikeForFiveHoursExpectedNoPromotion() {
        final double exceptedPrice = 50;
        final double exceptedDiscount = 0;
        final int times = 5;
        final int quantity = 2;
        when(priceListRepository.getItem(product.getCode(),UnitOfMeasureEnum.HOUR.getCode())).thenReturn(getPriceByHour());
        when(productRepository.getProduct(product.getCode())).thenReturn(product);
        when(promotionsRepository.getListOfPromotions()).thenReturn(getPromotionsList());
        try {
            invoiceBuilder.addProduct(product.getCode(),quantity,times,UnitOfMeasureEnum.HOUR)
                    .applyPromotionsOverTotal();

            verify(productRepository, times(1)).getProduct(product.getCode());
            Invoice invoice = invoiceBuilder.getInvoice();
            assertEquals(invoice.getTotal(),exceptedPrice,0);
            assertEquals(invoice.getTotalOfDiscounts(),exceptedDiscount,0);

        }
        catch (PriceNotExistsException e) {
            Assert.fail();
        }
        catch (ProductDoesNotExistException e) {
            Assert.fail();
        }
        catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void testRentOneBikeForOneWeekExpectedNoPromotion() {
        final double exceptedPrice = 60;
        final double exceptedDiscount = 0;
        final int times = 1;
        final int quantity = 1;
        when(priceListRepository.getItem(product.getCode(),UnitOfMeasureEnum.WEEK.getCode())).thenReturn(getPriceByWeek());
        when(productRepository.getProduct(product.getCode())).thenReturn(product);
        when(promotionsRepository.getListOfPromotions()).thenReturn(getPromotionsList());
        try {
            invoiceBuilder.addProduct(product.getCode(),quantity,times,UnitOfMeasureEnum.WEEK)
                .applyPromotionsOverTotal();

            verify(productRepository, times(1)).getProduct(product.getCode());
            Invoice invoice = invoiceBuilder.getInvoice();
            assertEquals(invoice.getTotal(),exceptedPrice,0);
            assertEquals(invoice.getTotalOfDiscounts(),exceptedDiscount,0);

        }
        catch (PriceNotExistsException e) {
            Assert.fail();
        }
        catch (ProductDoesNotExistException e) {
            Assert.fail();
        }
        catch (Exception e) {
            Assert.fail();
        }
    }


    @Test
    public void testProductDoesNotExistException() {
        final int times = 1;
        final int quantity = 1;
        when(priceListRepository.getItem(product.getCode(),UnitOfMeasureEnum.WEEK.getCode())).thenReturn(getPriceByWeek());
        when(productRepository.getProduct(product.getCode())).thenReturn(null);
        when(promotionsRepository.getListOfPromotions()).thenReturn(getPromotionsList());
        try {
            invoiceBuilder.addProduct(product.getCode(),quantity,times,UnitOfMeasureEnum.WEEK);
        } catch (ProductDoesNotExistException e) {
            Assert.assertTrue(true);
        } catch (PriceNotExistsException e) {
            Assert.fail();
        }
    }



    @Test
    public void testPriceNotExistsException() {
        final int times = 1;
        final int quantity = 1;
        when(priceListRepository.getItem(product.getCode(),UnitOfMeasureEnum.WEEK.getCode())).thenReturn(null);
        when(productRepository.getProduct(product.getCode())).thenReturn(product);
        when(promotionsRepository.getListOfPromotions()).thenReturn(getPromotionsList());
        try {
            invoiceBuilder.addProduct(product.getCode(),quantity,times,UnitOfMeasureEnum.WEEK);
        } catch (ProductDoesNotExistException e) {
            Assert.fail();
        } catch (PriceNotExistsException e) {

            Assert.assertTrue(true);
        }
    }

    @Test
    public void testRentThreeBikeForOneHourExpectedPromotion() {
        final double exceptedPrice = 10.5;
        final double exceptedTotalWihtoutDiscounts = 15;
        final double exceptedDiscount = 4.5;
        final int times = 1;
        final int quantity = 3;
        when(priceListRepository.getItem(product.getCode(),UnitOfMeasureEnum.HOUR.getCode())).thenReturn(getPriceByHour());
        when(productRepository.getProduct(product.getCode())).thenReturn(product);
        when(promotionsRepository.getListOfPromotions()).thenReturn(getPromotionsList());
        try {
            invoiceBuilder.addProduct(product.getCode(),quantity,times,UnitOfMeasureEnum.HOUR)
               .applyPromotionsOverTotal();

            verify(productRepository, times(1)).getProduct(product.getCode());
            Invoice invoice = invoiceBuilder.getInvoice();
            assertEquals(invoice.getTotal(),exceptedPrice,0);
            assertEquals(invoice.getTotalWithOutDiscounts(),exceptedTotalWihtoutDiscounts,0);
            assertEquals(invoice.getTotalOfDiscounts(),exceptedDiscount,0);

        }
        catch (PriceNotExistsException e) {
            Assert.fail();
        }
        catch (ProductDoesNotExistException e) {
            Assert.fail();
        }
        catch (Exception e) {
            Assert.fail();
        }
    }


    @Test
    public void testRentComboBikesExpectedPromotion() {
        final double exceptedPrice = 63;
        final double exceptedTotalWihtoutDiscounts = 90;
        final double exceptedDiscount =27;
        final int times = 1;

        when(priceListRepository.getItem(product.getCode(),UnitOfMeasureEnum.HOUR.getCode())).thenReturn(getPriceByHour());
        when(priceListRepository.getItem(product.getCode(),UnitOfMeasureEnum.WEEK.getCode())).thenReturn(getPriceByWeek());
        when(priceListRepository.getItem(product.getCode(),UnitOfMeasureEnum.DAY.getCode())).thenReturn(getPriceByDay());
        when(productRepository.getProduct(product.getCode())).thenReturn(product);
        when(promotionsRepository.getListOfPromotions()).thenReturn(getPromotionsList());
        try {
            invoiceBuilder.addProduct(product.getCode(),1,times,UnitOfMeasureEnum.DAY)
                .addProduct(product.getCode(),1,times,UnitOfMeasureEnum.WEEK)
                .addProduct(product.getCode(),2,times,UnitOfMeasureEnum.HOUR)
                .applyPromotionsOverTotal();

            verify(productRepository, times(3)).getProduct(product.getCode());
            Invoice invoice = invoiceBuilder.getInvoice();
            assertEquals(invoice.getTotal(),exceptedPrice,0);
            assertEquals(invoice.getTotalWithOutDiscounts(),exceptedTotalWihtoutDiscounts,0);
            assertEquals(invoice.getTotalOfDiscounts(),exceptedDiscount,0);

        }
        catch (PriceNotExistsException e) {
            Assert.fail();
        }
        catch (ProductDoesNotExistException e) {
            Assert.fail();
        }
        catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void testRentComboMoreThan5BikesNoExpectedPromotion() {
        final double exceptedPrice = 170;
        final double exceptedTotalWihtoutDiscounts = 170;
        final double exceptedDiscount =0;
        final int times = 1;

        when(priceListRepository.getItem(product.getCode(),UnitOfMeasureEnum.HOUR.getCode())).thenReturn(getPriceByHour());
        when(priceListRepository.getItem(product.getCode(),UnitOfMeasureEnum.WEEK.getCode())).thenReturn(getPriceByWeek());
        when(priceListRepository.getItem(product.getCode(),UnitOfMeasureEnum.DAY.getCode())).thenReturn(getPriceByDay());
        when(productRepository.getProduct(product.getCode())).thenReturn(product);
        when(promotionsRepository.getListOfPromotions()).thenReturn(getPromotionsList());
        try {
            invoiceBuilder.addProduct(product.getCode(),2,times,UnitOfMeasureEnum.DAY)
                .addProduct(product.getCode(),2,times,UnitOfMeasureEnum.WEEK)
                .addProduct(product.getCode(),2,times,UnitOfMeasureEnum.HOUR)
                .applyPromotionsOverTotal();

            verify(productRepository, times(3)).getProduct(product.getCode());
            Invoice invoice = invoiceBuilder.getInvoice();
            assertEquals(invoice.getTotal(),exceptedPrice,0);
            assertEquals(invoice.getTotalWithOutDiscounts(),exceptedTotalWihtoutDiscounts,0);
            assertEquals(invoice.getTotalOfDiscounts(),exceptedDiscount,0);

        }
        catch (PriceNotExistsException e) {
            Assert.fail();
        }
        catch (ProductDoesNotExistException e) {
            Assert.fail();
        }
        catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void testAddProducts() {

        when(priceListRepository.getItem(product.getCode(),UnitOfMeasureEnum.DAY.getCode())).thenReturn(getPriceByDay());
        when(productRepository.getProduct(product.getCode())).thenReturn(product);
        when(promotionsRepository.getListOfPromotions()).thenReturn(getPromotionsList());
        try {
            invoiceBuilder.addProduct(product.getCode(),2,1,UnitOfMeasureEnum.DAY);
            Invoice invoice = invoiceBuilder.getInvoice();
            assertEquals(product,invoice.getInvoiceDetails().get(0).getProduct());

        }
        catch (PriceNotExistsException e) {
            Assert.fail();
        }
        catch (ProductDoesNotExistException e) {
            Assert.fail();
        }
        catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void testInvoiceHeader(){
        Invoice invoice = invoiceBuilder.getInvoice();
        assertEquals(invoice.getClientName(),INVOICE_CLIENT_NAME);
        assertEquals(invoice.getLetter(),INVOICE_LETTER);
        assertEquals(invoice.getNumber(),INVOICE_NUMBER);
        assertEquals(invoice.getPointOfSale(),INVOICE_POINT_OF_SALE);
        assertEquals(invoice.getTaxCondition(),INVOICE_TAX_CONDITION);
        assertEquals(invoice.getDate(),INVOICE_DATE);

    }


    private List<Promotion> getPromotionsList(){
        List<Promotion> promotionList = new ArrayList<>();
        Promotion promotion = new Promotion();
        promotion.setProductCode(product.getCode());
        promotion.setMax(5);
        promotion.setMin(3);
        promotion.setPercentege(30);
        promotion.setPromoType(PromoTypesEnum.OVER_TOTAL_BY_QUANTITY);
        promotion.setName("Family Rental");
        promotionList.add(promotion);
        return promotionList;
    }



    private PriceListItem getPriceByDay() {

        PriceListItem priceByDay = new PriceListItem();
        priceByDay.setProductCode(1);
        priceByDay.setUom(UnitOfMeasureEnum.DAY);
        priceByDay.setPrice(20);

        return priceByDay;
    }

    private PriceListItem getPriceByHour() {

        PriceListItem priceByHour= new PriceListItem();
        priceByHour.setProductCode(1);
        priceByHour.setUom(UnitOfMeasureEnum.HOUR);
        priceByHour.setPrice(5);

        return priceByHour;
    }

    private PriceListItem getPriceByWeek() {

        PriceListItem priceByWeek = new PriceListItem();
        priceByWeek.setProductCode(1);
        priceByWeek.setUom(UnitOfMeasureEnum.WEEK);
        priceByWeek.setPrice(60);

        return priceByWeek;
    }



}
