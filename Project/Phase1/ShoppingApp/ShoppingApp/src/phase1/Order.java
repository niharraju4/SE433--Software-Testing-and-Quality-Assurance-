package phase1;

import java.math.BigDecimal;
import java.util.HashMap;

public class Order {
    private Customer customer;
    private Cart cart;
    private Shipping shipping;

    public Order(Customer customer, Cart cart, Shipping shipping) {
        this.customer = customer;
        this.cart = cart;
        this.shipping = shipping;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public void setShipping(Shipping shipping) {
        this.shipping = shipping;
    }

    public void getCartTotal() {
    	if (shipping == null) {
    		System.out.println("Error: Please Select A Shipping Option");
            return;
    	}
    	BigDecimal cartTotal = new BigDecimal(cart.getTotal(customer, shipping).toString());
    	System.out.printf("Your total is: $%.2f%n", cartTotal);
    }

    public void checkout() {
        if (customer == null || cart == null || shipping == null) {
            System.out.println("Error: Incomplete Order Information.");
            return;
        }

        HashMap<Item, Integer> cartItems = cart.getItems();
        BigDecimal rawTotal = cartItems.entrySet().stream()
                .map(entry -> entry.getKey().getPrice().multiply(BigDecimal.valueOf(entry.getValue())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal maxTotal = new BigDecimal("99999.99");
        BigDecimal minTotal = new BigDecimal("1");
        if (rawTotal.compareTo(maxTotal) > 0 || rawTotal.compareTo(minTotal) < 0) {
            System.out.println("Error: Purchase Amount Must Be Between $1 And $99,999.99.");
            return;
        }

        BigDecimal total = new BigDecimal(cart.getTotal(customer, shipping).toString());

        // Print receipt
        System.out.println("\nTransaction Complete!\n");
        System.out.println("\nReceipt: \n");
        System.out.printf("Customer: %s%n", customer.getName());
        System.out.printf("State: %s%n", customer.getState());

        System.out.println("\nItems:");
        for (HashMap.Entry<Item, Integer> entry : cart.getItems().entrySet()) {
            System.out.printf("%s - $%.2f - Quantity: %d%n", entry.getKey().getName(), entry.getKey().getPrice(), entry.getValue());
        }
        System.out.println("\n\nTaxes And Shipping: \n");
        System.out.printf("Shipping: %s - $%.2f%n", shipping.getShippingType(), shipping.getCost());

        BigDecimal taxRate = cart.getTaxRate(customer);
        BigDecimal taxCost = cart.getTaxCost(customer);
        System.out.printf("Tax Rate: %.2f%% - Tax Cost: $%.2f%n", taxRate.multiply(BigDecimal.valueOf(100)), taxCost);

        System.out.printf("\nTotal: $%.2f%n", total);
        System.out.printf("\nThank you for shopping with us, %s. We'll see you again soon!%n", customer.getName());
    }
}