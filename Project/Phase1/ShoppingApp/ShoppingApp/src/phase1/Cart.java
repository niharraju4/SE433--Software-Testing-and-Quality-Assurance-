package phase1;

import java.util.HashMap;
import java.math.BigDecimal;

public class Cart {
    private HashMap<Item, Integer> items;

    public Cart() {
        items = new HashMap<>();
    }

    public void addItem(Item item, int quantity) {
        if (quantity < 1) {
            System.out.println("Error: Quantity Must Be A Positive Integer.");
            return;
        }
        int currentQuantity = items.getOrDefault(item, 0);
        items.put(item, currentQuantity + quantity);
        System.out.println("Item Added To Cart. Current Count Of Items In The Cart: " + itemsCount());
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public void updateQuantity(Item item, int quantity) {
        if (quantity < 1) {
            System.out.println("Error: Quantity Must Be A Positive Integer.");
            return;
        }
        if (items.containsKey(item)) {
            items.put(item, quantity);
        }
    }

    public HashMap<Item, Integer> getItems() {
        return items;
    }

    public BigDecimal getTotal(Customer customer, Shipping shipping) {
        BigDecimal total = items.entrySet().stream()
                .map(entry -> entry.getKey().getPrice().multiply(BigDecimal.valueOf(entry.getValue())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal taxRate = getTaxRate(customer);
        BigDecimal tax = total.multiply(taxRate);

        if (shipping.getShippingType().equalsIgnoreCase("standard") && total.compareTo(BigDecimal.valueOf(50)) > 0) {
            shipping.setFreeStandardShipping();
        }

        BigDecimal grandTotal = total.add(tax).add(shipping.getCost());
        return grandTotal;
    }

    public BigDecimal getTaxRate(Customer customer) {
        BigDecimal taxRate = BigDecimal.ZERO;
        if (customer.getState().equalsIgnoreCase("IL") || customer.getState().equalsIgnoreCase("CA") || customer.getState().equalsIgnoreCase("NY")) {
            taxRate = BigDecimal.valueOf(0.06);
        }
        return taxRate;
    }

    public BigDecimal getTaxCost(Customer customer) {
        BigDecimal total = items.entrySet().stream()
                .map(entry -> entry.getKey().getPrice().multiply(BigDecimal.valueOf(entry.getValue())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal taxRate = getTaxRate(customer);
        return total.multiply(taxRate);
    }

    private int itemsCount() {
        return items.values().stream().mapToInt(Integer::intValue).sum();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }
}
