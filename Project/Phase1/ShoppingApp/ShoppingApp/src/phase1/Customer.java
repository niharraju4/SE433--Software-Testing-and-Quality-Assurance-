package phase1;


public class Customer {
    private String name;
    private String state;

    public Customer(String name, String state) {
        this.name = name;
        this.state = state;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public String getName() {
        return name;
    }
    
}