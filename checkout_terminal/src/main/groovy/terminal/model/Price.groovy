package terminal.model

class Price {

    BigDecimal RegularPrice
    PriceType priceType
    Integer volumeQuantity
    BigDecimal volumePrice

    Price(BigDecimal RegularPrice) {
        this.RegularPrice = RegularPrice
        this.priceType = PriceType.REGULAR
    }

    Price(BigDecimal RegularPrice, PriceType priceType, Integer volumeQuantity, BigDecimal volumePrice) {
        this.RegularPrice = RegularPrice
        this.priceType = priceType
        this.volumeQuantity = volumeQuantity
        this.volumePrice = volumePrice
    }

}
