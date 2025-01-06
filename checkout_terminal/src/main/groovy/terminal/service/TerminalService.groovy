package terminal.service

import org.springframework.stereotype.Service
import terminal.model.Cart

@Service
class TerminalService {

    PriceService priceService

    TerminalService(PriceService priceService) {
        this.priceService = priceService
    }

    void scan(Cart cart, String productCode) {
        cart.add(productCode)
    }

    BigDecimal total(Cart cart) {
        BigDecimal total = cart.cart.collect() { String productCode, Integer volume ->
            priceService.calculatePriceForAmount(productCode, volume)
        }.sum() as BigDecimal

        return total
    }

}
