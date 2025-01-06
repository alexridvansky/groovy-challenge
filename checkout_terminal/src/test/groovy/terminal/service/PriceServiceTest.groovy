package terminal.service

import spock.lang.Specification
import terminal.model.Price
import terminal.model.PriceType
import terminal.repository.PriceRepository

class PriceServiceTest extends Specification{

    def priceRepository = Mock(PriceRepository)
    def priceService = new PriceService(priceRepository)

    def "Calculating amount for REGULAR price type"() {
        given: "Volume for product of REGULAR price type"
        def volume = 10
        def productCode = "B"
        def price = new Price(new BigDecimal("12"))
        priceRepository.getPrice(productCode) >> price

        when: "Calculating total amount"
        def amount = priceService.calculatePriceForAmount(productCode, volume)

        then: "The amount calculated based on REGULAR price type"
        amount == 120
    }

    def "Calculating amount for REGULAR price type with float value"() {
        given: "Volume for product of REGULAR price type"
        def volume = 8
        def productCode = "D"
        def price = new Price(new BigDecimal("0.15"))
        priceRepository.getPrice(productCode) >> price

        when: "Calculating total amount"
        def amount = priceService.calculatePriceForAmount(productCode, volume)

        then: "The amount calculated based on REGULAR price type"
        amount == 1.2
    }

    def "Calculating amount for VOLUME_FOR_PRICE price type"() {
        given: "Volume for product of VOLUME_FOR_PRICE price type"
        def volume = 5
        def productCode = "A"
        def price = new Price(new BigDecimal("2"), PriceType.VOLUME_FOR_PRICE, 4, new BigDecimal("7"))
        priceRepository.getPrice(productCode) >> price

        when: "Calculating total amount"
        def amount = priceService.calculatePriceForAmount(productCode, volume)

        then: "The amount calculated based on VOLUME_FOR_PRICE type"
        amount == 9
    }

    def "Calculating amount for VOLUME_DISCOUNT price type"() {
        given: "Volume for product of VOLUME_DISCOUNT price type"
        def volume = 12
        def productCode = "F"
        def price = new Price(new BigDecimal("1"), PriceType.VOLUME_DISCOUNT, 10, new BigDecimal("0.9"))
        priceRepository.getPrice(productCode) >> price

        when: "Calculating total amount"
        def amount = priceService.calculatePriceForAmount(productCode, volume)

        then: "The amount calculated based on VOLUME_DISCOUNT type"
        amount == 10.80
    }

    def "Calculating amount for VOLUME_DISCOUNT price type with nanos"() {
        given: "Volume for product of VOLUME_DISCOUNT price type"
        def volume = 7
        def productCode = "C"
        def price = new Price(new BigDecimal("1.25"), PriceType.VOLUME_FOR_PRICE, 6, new BigDecimal("6"))
        priceRepository.getPrice(productCode) >> price

        when: "Calculating total amount"
        def amount = priceService.calculatePriceForAmount(productCode, volume)

        then: "The amount calculated based on VOLUME_DISCOUNT type"
        amount == 7.25
    }

}
