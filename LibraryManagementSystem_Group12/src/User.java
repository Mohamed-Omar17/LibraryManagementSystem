import java.util.ArrayList;

public class User {
    private ArrayList<String> borrowed;
    public User() {
        this.borrowed = new ArrayList<>();
    }

    public void addToBorrowed(String book) {
        borrowed.add(book);
    }

    public void borrowedBooks() {
        System.out.print("Borrowed Books: ");
        if (borrowed.isEmpty()) System.out.println("None");
        else {
            for (String book : borrowed) {
                System.out.print(book);
            }
        }
        System.out.println();
    }

    public void removeFromBorrowed(String book) {
        borrowed.remove(book);
    }
}
