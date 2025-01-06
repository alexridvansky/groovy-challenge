package terminal.service

import org.springframework.stereotype.Service
import terminal.model.Price
import terminal.model.PriceType
import terminal.repository.PriceRepository

@Service
class PriceService {

    private final PriceRepository priceRepository

    PriceService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository
    }

    BigDecimal calculatePriceForAmount(String productCode, Integer volume) {
        Price price = priceRepository.getPrice(productCode)

        return switch (price.priceType) {
            case PriceType.REGULAR -> calculateRegularPrice(price, volume)
            case PriceType.VOLUME_FOR_PRICE -> calculateVolumeForPrice(price, volume)
            case PriceType.VOLUME_DISCOUNT -> calculateVolumeDiscount(price, volume)
        }
    }

    private static BigDecimal calculateRegularPrice(Price price, Integer volume) {
        return price.regularPrice * volume
    }

    private static BigDecimal calculateVolumeForPrice(Price price, Integer volume) {
        Integer volumeWithDiscount = (Integer) (volume / price.volumeQuantity)
        Integer volumeWithRegularPrice = volume % price.volumeQuantity

        return volumeWithDiscount * price.volumePrice + volumeWithRegularPrice * price.regularPrice
    }

    private static BigDecimal calculateVolumeDiscount(Price price, Integer volume) {
        if (volume >= price.getVolumeQuantity()) {
            return price.getVolumePrice() * volume
        } else {
            return price.getRegularPrice() * volume
        }
    }

}
