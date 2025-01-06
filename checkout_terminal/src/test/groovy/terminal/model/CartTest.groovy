package terminal.model

import spock.lang.Specification


class CartTest extends Specification {

    def "test adding items to cart"() {
        given: "A new cart"
        def cart = new Cart()

        when: "Adding items to the cart"
        cart.add('apple')
        cart.add('banana')
        cart.add('apple')
        cart.add('apple')

        then: "The cart should contain the added items"
        cart.getCart() == ['apple': 3, 'banana': 1]
    }

}
