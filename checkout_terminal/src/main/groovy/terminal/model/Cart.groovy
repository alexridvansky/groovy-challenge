package terminal.model

class Cart {

    Map<String, Integer> cart = [:]

    void add(String product) {
        if (cart.containsKey(product)) {
            cart[product] += 1
        } else {
            cart[product] = 1
        }
    }

}
