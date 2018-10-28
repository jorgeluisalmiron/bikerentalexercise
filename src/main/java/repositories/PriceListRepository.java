package repositories;

import model.PriceListItem;

import java.util.List;

public interface PriceListRepository {

    List<PriceListItem> getPriceList();
    PriceListItem getItem(int productCode, String uom);
}
