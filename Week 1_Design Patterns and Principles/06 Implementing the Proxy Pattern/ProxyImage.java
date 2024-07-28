public class ProxyImage implements Image {
    private RealImage realImage;
    private String filename;

    // Constructor to initialize filename
    public ProxyImage(String filename) {
        this.filename = filename;
    }

    @Override
    public void display() {
        if (realImage == null) {
            realImage = new RealImage(filename);
        }
        realImage.display();
    }
}
