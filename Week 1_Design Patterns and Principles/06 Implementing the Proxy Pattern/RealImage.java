public class RealImage implements Image {
    private String filename;

    // Constructor to initialize filename
    public RealImage(String filename) {
        this.filename = filename;
        loadImageFromServer();
    }

    // Simulate loading image from a remote server
    private void loadImageFromServer() {
        System.out.println("Loading image: " + filename);
    }

    @Override
    public void display() {
        System.out.println("Displaying image: " + filename);
    }
}
