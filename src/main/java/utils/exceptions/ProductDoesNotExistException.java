package utils.exceptions;

public class ProductDoesNotExistException extends Exception {

    private static final String PRODUCT_NUMBER = "Product Number: ";

    public ProductDoesNotExistException(int productCode) {
        super(PRODUCT_NUMBER + String.valueOf(productCode));
    }
}
