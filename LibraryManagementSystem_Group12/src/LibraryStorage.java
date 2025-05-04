import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class LibraryStorage {

    private HashMap<String, Boolean> bookAvailability;
    private ArrayList<String> books;

    public LibraryStorage() {
        bookAvailability = new HashMap<>();
        books = new ArrayList<>();
    }

    public boolean checkBook(String book) {
        return bookAvailability.containsKey(book);
    }

    public void addBook(String book) {
        books.add(book);
        bookAvailability.put(book, true);
    }

    public void updateAvailability(String book) {
        if (bookAvailability.get(book)) {
            bookAvailability.put(book, false);
        }
        else
            bookAvailability.put(book, true);
    }

    public void getStorage() {
        for (String book : bookAvailability.keySet())
            System.out.println(book + ": " + bookAvailability.get(book));
    }

    public int findAvailability(String book) {
        long startTime = System.nanoTime();
        if (!bookAvailability.containsKey(book)) {
            long endTime = System.nanoTime();
            long totalTime = endTime - startTime;
            System.out.println("Hash search: " + totalTime);
            return -1;
        }
        else if (!bookAvailability.get(book)) {
            long endTime = System.nanoTime();
            long totalTime = endTime - startTime;
            System.out.println("Hash search: " + totalTime);
            return 0;
        }
        else  {
            long endTime = System.nanoTime();
            long totalTime = endTime - startTime;
            System.out.println("Hash search: " + totalTime);
            return 1;
        }
    }

    public void removeBook(String book) {
        bookAvailability.remove(book);
    }

    public void showAvailable() {
        System.out.println("Available books: ");
        for (String book : bookAvailability.keySet()) {
            if (bookAvailability.get(book)) {
                System.out.print(book + " ");
            }
        }
        System.out.println();
    }
}
