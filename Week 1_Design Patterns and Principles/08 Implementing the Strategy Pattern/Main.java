// Main.java
public class Main {
    public static void main(String[] args) {

        System.out.println();
        // Create payment strategies
        PaymentStrategy creditCardPayment = new CreditCardPayment("1234-5678-9876-5432", "John Doe");
        PaymentStrategy payPalPayment = new PayPalPayment("john.doe@example.com");

        // Create payment contexts with different strategies
        PaymentContext paymentContext = new PaymentContext(creditCardPayment);
        paymentContext.executePayment(100);

        paymentContext = new PaymentContext(payPalPayment);
        paymentContext.executePayment(200);
    }
}
