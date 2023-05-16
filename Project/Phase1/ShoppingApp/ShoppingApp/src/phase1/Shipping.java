package phase1;

import java.math.BigDecimal;

public class Shipping {
    private String shippingType;
    private BigDecimal cost;

    public Shipping(String shippingType) {
        setType(shippingType);
    }

    public void setType(String shippingType) {
        this.shippingType = shippingType;
        if (shippingType.equalsIgnoreCase("standard")) {
            this.cost = new BigDecimal("10.00");
        } else if (shippingType.equalsIgnoreCase("next day")) {
            this.cost = new BigDecimal("25.00");
        } else {
            System.out.println("Invalid Shipping Type. Please Try Again.");
            this.cost = new BigDecimal("-1");
        }
    }

    public void setFreeStandardShipping() {
        this.cost = BigDecimal.ZERO;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public String getShippingType() {
        return shippingType;
    }
}