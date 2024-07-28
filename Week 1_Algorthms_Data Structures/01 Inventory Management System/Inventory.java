import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Inventory 
{
    private List<Product> products;

    public Inventory() 
    {
        this.products = new ArrayList<>();
    }

    // Method to add a product
    public void addProduct(Product product) 
    {
        products.add(product);
    }

    // Method to update a product
    public void updateProduct(int productId, String productName, int quantity, double price) 
    {
        boolean found = false;
        for (Product product : products) 
        {
            if (product.getProductId() == productId) 
            {
                product.setProductName(productName);
                product.setQuantity(quantity);
                product.setPrice(price);
                System.out.println("Product with ID : " + productId + " Updated");
                found = true;
                break;
            }
        }
        if (!found)
        {
            System.out.println("Invalid Product ID : " + productId);
        }
    }

    // Method to delete a product
    public boolean deleteProduct(int productId) 
    {
        for (Product product : products)
        {
            if (product.getProductId() == productId)
            {
                products.remove(product);
                System.out.println("Product with ID : " + productId + " Removed");
                return true;
            }
        }
        System.out.println("Invalid Product ID : " + productId);
        return false;
    }

    // Method to display all products
    public void displayProducts() 
    {
        for (Product product : products) 
        {
            System.out.println(product);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Inventory inventory = new Inventory();

        int productId;
        String productName;
        int quantity;
        double price;

        // Adding Product
        char makeEntry = 'y';
        while (makeEntry == 'y')
        {
            System.out.println("Enter the Product Details");
            System.out.print("Product ID: ");
            productId = sc.nextInt();
            sc.nextLine(); 
            System.out.print("Product Name: ");
            productName = sc.nextLine();
            System.out.print("Quantity: ");
            quantity = sc.nextInt();
            System.out.print("Price: ");
            price = sc.nextDouble();
            Product p = new Product(productId, productName, quantity, price);
            inventory.addProduct(p);

            System.out.print("Want to add another product [y/n]: ");
            makeEntry = sc.next().charAt(0);
            System.out.println();
        }

        // Displaying products
        System.out.println("Products in inventory:");
        inventory.displayProducts();

        // Updating a product
        System.out.print("Enter Product ID to update: ");
        productId = sc.nextInt();
        inventory.updateProduct(productId, "Gaming Laptop", 5, 95000.00);

        // Displaying products after update
        System.out.println("Products in inventory after update:");
        inventory.displayProducts();

        // Deleting a product
        System.out.print("Enter Product ID to delete: ");
        productId = sc.nextInt();
        inventory.deleteProduct(productId);

        // Displaying products after deletion
        System.out.println("Products in inventory after deletion:");
        inventory.displayProducts();
        
        sc.close();
    }
}
