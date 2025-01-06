package terminal.repository

import org.springframework.stereotype.Repository
import terminal.model.Price
import terminal.model.PriceType


/**
 * Stab for a Repository.
 */
@Repository
class PriceRepository {

    Map<String, Price> prices

    PriceRepository() {
        prices = [:]
        prices.put("A", new Price(new BigDecimal("2"), PriceType.VOLUME_FOR_PRICE, 4, new BigDecimal("7")))
        prices.put("B", new Price(new BigDecimal("12")))
        prices.put("C", new Price(new BigDecimal("1.25"), PriceType.VOLUME_FOR_PRICE, 6, new BigDecimal("6")))
        prices.put("D", new Price(new BigDecimal("0.15")))
        prices.put("G", new Price(new BigDecimal("1"), PriceType.VOLUME_FOR_PRICE, 10, new BigDecimal("9")))
        prices.put("F", new Price(new BigDecimal("1"), PriceType.VOLUME_DISCOUNT, 10, new BigDecimal("0.9")))
    }

    Price getPrice(String productCode) {
        return Optional.ofNullable(prices.get(productCode))
                .orElseThrow(() -> new IllegalArgumentException("Product code " + productCode + " does not exist"))
    }

}
