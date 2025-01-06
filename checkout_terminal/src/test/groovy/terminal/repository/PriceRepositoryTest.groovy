package terminal.repository

import spock.lang.Specification
import terminal.model.Price
import terminal.model.PriceType

class PriceRepositoryTest extends Specification {

    def "Requesting Price for not existing product code should throw an exception"() {
        given: "Not existing product code"
        def wrongProductCode = "X"
        def priceRepository = new PriceRepository()

        when: "Querying price for wrong product code"
        priceRepository.getPrice(wrongProductCode)

        then: "IllegalArgumentException should be thrown"
        def exception = thrown(IllegalArgumentException)
        exception.message == "Product code X does not exist"
    }

    def "Requesting Price for existing product code should return the price"() {
        given: "Existing product code"
        def existingProductCode = "B"
        def priceRepository = new PriceRepository()

        when: "Querying price for existing product code"
        Price price = priceRepository.getPrice(existingProductCode)

        then: "Price object should be returned"
        price.priceType == PriceType.REGULAR
        price.regularPrice == 12
    }

}
