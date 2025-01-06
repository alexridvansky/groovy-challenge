package terminal.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification
import terminal.model.Cart

/**
 * This test class is used for End-to-End testing
 */
@SpringBootTest
class TerminalServiceTest extends Specification {

    @Autowired
    TerminalService terminalService

    def "Test for adding products and checkout for ABCD"() {
        given: "An empty cart and some products added"
        def cart = new Cart()
        terminalService.scan(cart, "A")
        terminalService.scan(cart, "B")
        terminalService.scan(cart, "C")
        terminalService.scan(cart, "D")

        when: "Processing the cart"
        def total = terminalService.total(cart)

        then: "Total should be 15.40"
        total == 15.40
    }

    def "Test for adding products and checkout for CCCCCCC"() {
        given: "An empty cart and some products added"
        def cart = new Cart()
        terminalService.scan(cart, "C")
        terminalService.scan(cart, "C")
        terminalService.scan(cart, "C")
        terminalService.scan(cart, "C")
        terminalService.scan(cart, "C")
        terminalService.scan(cart, "C")
        terminalService.scan(cart, "C")

        when: "Processing the cart"
        def total = terminalService.total(cart)

        then: "Total should be 7.25"
        total == 7.25
    }

    def "Test for adding products and checkout for ABCDABAA"() {
        given: "An empty cart and some products added"
        def cart = new Cart()
        terminalService.scan(cart, "A")
        terminalService.scan(cart, "B")
        terminalService.scan(cart, "C")
        terminalService.scan(cart, "D")
        terminalService.scan(cart, "A")
        terminalService.scan(cart, "B")
        terminalService.scan(cart, "A")
        terminalService.scan(cart, "A")

        when: "Processing the cart"
        def total = terminalService.total(cart)

        then: "Total should be 32.40"
        total == 32.40
    }

}
