package utils.exceptions;

public class PriceNotExistsException extends Throwable {
    public PriceNotExistsException(int productCode, String uom) {
        super("Price nos exists for this product/unit of measure" + String.valueOf(productCode) + " " + uom);
    }
}
