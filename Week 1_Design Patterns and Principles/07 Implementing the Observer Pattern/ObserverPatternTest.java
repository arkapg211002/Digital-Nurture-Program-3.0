public class ObserverPatternTest {
    public static void main(String[] args) {

        System.out.println();

        // Create a StockMarket instance
        StockMarket stockMarket = new StockMarket("TechCorp", 100.0);

        // Create observer instances
        MobileApp mobileApp = new MobileApp("MobileApp1");
        WebApp webApp = new WebApp("WebApp1");

        // Register observers
        stockMarket.registerObserver(mobileApp);
        stockMarket.registerObserver(webApp);

        // Change stock price and notify observers
        System.out.println("Updating stock price...");
        stockMarket.setStockPrice(105.0);

        // Deregister an observer
        stockMarket.deregisterObserver(webApp);

        // Change stock price and notify observers
        System.out.println("Updating stock price again...");
        stockMarket.setStockPrice(110.0);
    }
}
