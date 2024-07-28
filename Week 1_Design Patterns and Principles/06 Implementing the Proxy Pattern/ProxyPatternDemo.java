public class ProxyPatternDemo {
    public static void main(String[] args) {

        System.out.println();

        // Create ProxyImage objects
        Image image1 = new ProxyImage("image1.jpg");
        Image image2 = new ProxyImage("image2.jpg");

        // Image will be loaded and displayed
        image1.display();
        
        // Image will be displayed without loading (cached)
        image1.display();

        // Image will be loaded and displayed
        image2.display();
        
        // Image will be displayed without loading (cached)
        image2.display();
    }
}
