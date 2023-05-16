package phase1;

import java.math.BigDecimal;

public class Item {
    private String name;
    private BigDecimal price;

    public Item(String name, double price) {
        this.name = name;
        this.price = BigDecimal.valueOf(price);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(double price) {
    	this.price = BigDecimal.valueOf(price);
    }
}