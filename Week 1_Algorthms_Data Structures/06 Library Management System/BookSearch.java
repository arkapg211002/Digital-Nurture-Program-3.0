import java.util.Arrays;

public class BookSearch {

    public static void main(String[] args) {
        Book[] books = {
            new Book(1, "Java Programming", "John Doe"),
            new Book(2, "Data Structures", "Jane Doe"),
            new Book(3, "Algorithms", "Alice Smith"),
            new Book(4, "Operating Systems", "Bob Johnson")
        };

        // Linear Search
        String searchTitle = "Data Structures";
        Book foundBookLinear = linearSearch(books, searchTitle);
        System.out.println("Linear Search Result: " + (foundBookLinear != null ? foundBookLinear : "Not Found"));

        // Binary Search
        // Note: Make sure books array is sorted by title before performing binary search
        Arrays.sort(books, (b1, b2) -> b1.getTitle().compareTo(b2.getTitle()));
        Book foundBookBinary = binarySearch(books, searchTitle);
        System.out.println("Binary Search Result: " + (foundBookBinary != null ? foundBookBinary : "Not Found"));
    }

    // Linear Search
    public static Book linearSearch(Book[] books, String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    // Binary Search
    public static Book binarySearch(Book[] books, String title) {
        int low = 0;
        int high = books.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int cmp = books[mid].getTitle().compareToIgnoreCase(title);

            if (cmp == 0) {
                return books[mid];
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return null;
    }
}
