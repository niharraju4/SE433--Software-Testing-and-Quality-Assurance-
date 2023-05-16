package phase1;


import java.util.Scanner;
import java.util.HashMap;

public class ShoppingCartDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("\n");
        System.out.println("  _        ,");
        System.out.println("(_\\______/________");
        System.out.println(" \\-|-|/|-|-|-|-|/");
        System.out.println("  \\==/-|-|-|-|-/");
        System.out.println("   \\/|-|-|-|,-'");
        System.out.println("    \\--|-'''");
        System.out.println("     \\_j________");
        System.out.println("      (_)     (_)");
        System.out.println("==========================");

        System.out.println("Welcome To The Grocery App!");
        System.out.println("\n");

        // Create a customer and get their information
        Customer customer = new Customer("", "");
        System.out.print("Please Enter Your Name: ");
        customer.setName(scanner.nextLine());
        System.out.print("Please Enter Your State Of Residence (ex. NY, IL, CA): ");
        customer.setState(scanner.nextLine());

        // Create a cart and shipping object
        Cart cart = new Cart();
        Shipping shipping = null;

        boolean isRunning = true;

        while (isRunning) {
            // Show available options
            System.out.println("\nOptions:");
            System.out.println("1. Add An Item To Your Cart");
            System.out.println("2. Remove Item From Your Cart");
            System.out.println("3. Update An Item's Quantity");
            System.out.println("4. View Your Cart");
            System.out.println("5. Select A Shipping Option");
            System.out.println("6. View Cart Total");
            System.out.println("7. Checkout");
            System.out.println("8. Exit");

            int choice;
            while (true) { 
                // Validation loop
                System.out.print("Enter The Option Number Of Your Choice: ");
                if (scanner.hasNextInt()) {
                    choice = scanner.nextInt();
                    if (choice >= 1 && choice <= 8) {
                        break; // Valid input, exit the validation loop
                    } else {
                        System.out.println("Invalid input! Please enter a number between 1 and 8.");
                    }
                } else {
                    System.out.println("Invalid input! Please enter a number.");
                    scanner.next(); // Consume the invalid input
                }
            }
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    //Add Item Your Cart
                    System.out.print("Enter Item Name: ");
                    String itemName = scanner.nextLine();
                    System.out.print("Enter Item Price: ");
                    double itemPrice = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline character
                    System.out.print("Enter Quantity: ");
                    int itemQuantity = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character

                    Item item = new Item(itemName, itemPrice);
                    cart.addItem(item, itemQuantity);
                    break;

                case 2:
                    // Remove Item From Your Cart
                    System.out.print("Enter Item Name To Remove: ");
                    itemName = scanner.nextLine();
                    boolean itemRemoved = false;
                    for (Item it : cart.getItems().keySet()) {
                        if (it.getName().equalsIgnoreCase(itemName)) {
                            cart.removeItem(it);
                            itemRemoved = true;
                            break;
                        }
                    }
                    if (itemRemoved) {
                        System.out.println("Item Removed From Cart.");
                    } else {
                        System.out.println("Item Not Found In Cart.");
                    }
                    break;

                   case 3:
                    // Update An Item's Quantity
                    System.out.print("Enter Item Name To Update: ");
                    itemName = scanner.nextLine();
                    System.out.print("Enter New Quantity: ");
                    itemQuantity = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character
                    boolean itemUpdated = false;
                    for (Item it : cart.getItems().keySet()) {
                        if (it.getName().equalsIgnoreCase(itemName)) {
                            cart.updateQuantity(it, itemQuantity);
                            itemUpdated = true;
                            break;
                        }
                    }
                    if (itemUpdated) {
                        System.out.println("Item Quantity Updated.");
                    } else {
                        System.out.println("Item Not Found In Cart.");
                    }
                    break;

                case 4:
                    //View Your Cart
                    if (cart.getItems().isEmpty()) {
                        System.out.println("Your Cart Is Empty.");
                    } else {
                        System.out.println("\nYour Cart:");
                        for (HashMap.Entry<Item, Integer> entry : cart.getItems().entrySet()) {
                            System.out.printf("%s - $%.2f - Quantity: %d%n", entry.getKey().getName(), entry.getKey().getPrice(), entry.getValue());
                        }
                    }
                    break;

                case 5:
                    // Select shipping option
                    System.out.print("Enter Shipping Type (Standard or Next Day): ");
                    String shippingType = scanner.nextLine().toLowerCase().trim();
                    if (shippingType.equals("standard") || shippingType.equals("next day")) {
                        shipping = new Shipping(shippingType);
                        System.out.println("Shipping Option Selected.");
                    } else {
                        System.out.println("Invalid Shipping Type. Please Try Again.");
                    }
                    break;

                case 6:
                    //View Cart Total
                    Order orderTotal = new Order(customer, cart, shipping);
                    orderTotal.getCartTotal();
                    break;

                case 7:
                    // Checkout
                    if (shipping == null) {
                        System.out.println("Error: Please Select A Shipping Option.");
                    } else {
                        Order order = new Order(customer, cart, shipping);
                        order.checkout();
                    }
                    isRunning = false;
                    break;

                case 8:
                    // Exit
                    System.out.println("Thank You For Using The Grocery App!");
                    System.out.println("Have A Nice Day!");
                    isRunning = false;
                    break;

                default:
                    System.out.println("Invalid Option. Please Try Again.");
                    break;
            }
        }

        scanner.close();
    }
}