import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        LibraryStorage lib = new LibraryStorage();
        BalancedBST bst = new BalancedBST(lib);
        User user = new User();

        /////////////////////////////////////////////////////////

        bst.insertNode("a");
        bst.insertNode("b");
        bst.insertNode("c");
        bst.insertNode("d");
        bst.insertNode("e");
        bst.insertNode("f");
        bst.insertNode("g");
        bst.insertNode("h");
        bst.insertNode("i");
        bst.insertNode("j");
        bst.insertNode("k");
        bst.insertNode("l");
        bst.insertNode("m");
        bst.insertNode("n");
        bst.insertNode("o");
        bst.insertNode("p");
        bst.insertNode("q");
        bst.insertNode("r");
        bst.insertNode("s");
        bst.insertNode("t");
        bst.insertNode("u");
        bst.insertNode("v");
        bst.insertNode("w");
        bst.insertNode("x");
        bst.insertNode("y");
        bst.insertNode("z");

        bst.printLevelOrder();



        ///////////////////////////////////////////////////////////////////////

        boolean searching = true;

        while (searching) {
            System.out.println("1. Browse Catalog/Return Books");
            System.out.println("2. View Borrowed Books");
            System.out.println("3. Show Available Books");
            System.out.println("4. Leave Library");
            String selection = "0";

            Scanner select = new Scanner(System.in);

            selection = select.nextLine();

            switch (selection) {
                case "1":
                    //HashMap<String, String> hashmap = bst.hashmap;
                    System.out.println("What book are you looking for?");
                    String bookToBorrow = "";

                    Scanner scan = new Scanner(System.in);

                    bookToBorrow = scan.nextLine();
                    bookToBorrow = bookToBorrow.toLowerCase();

                    //System.out.println(bst.hashmap.get(bookToBorrow));

                    if (bst.search(bookToBorrow)) {
                        String borrow = "";
                        if (lib.findAvailability(bookToBorrow) == 1) {
                            System.out.println("Book available");


                            System.out.println("Would you like to borrow it? (y/n)");

                            while (!borrow.equalsIgnoreCase("y") && !borrow.equalsIgnoreCase("n")) {
                                borrow = scan.nextLine();
                                if (!borrow.equalsIgnoreCase("y") && !borrow.equalsIgnoreCase("n"))
                                    System.out.println("Select 'y' or 'n'!");
                            }

                            if (borrow.equalsIgnoreCase("y")) {
                                lib.updateAvailability(bookToBorrow);
                                user.addToBorrowed(bookToBorrow);
                                System.out.println("Enjoy your reading");
                            } else
                                System.out.println("Hope you find something you like");


                        } else if (lib.findAvailability(bookToBorrow) == 0) {

                            System.out.println("You are borrowing the book. Would you like to return it? (y/n)");

                            while (!borrow.equalsIgnoreCase("y") && !borrow.equalsIgnoreCase("n")) {
                                borrow = scan.nextLine();
                                if (!borrow.equalsIgnoreCase("y") && !borrow.equalsIgnoreCase("n"))
                                    System.out.println("Select 'y' or 'n'!");
                            }

                            if (borrow.equalsIgnoreCase("y")) {
                                lib.updateAvailability(bookToBorrow);
                                user.removeFromBorrowed(bookToBorrow);
                                System.out.println("Thank you for returning the book!");
                            } else
                                System.out.println("Happy Reading!");
                        }
                    } else
                        System.out.println("We do not carry this book");

                    String cont = "";

                    /*System.out.println("Continue Searching? (y/n)");

                    while (!Objects.equals(cont, "y") && !Objects.equals(cont, "n")) {
                        cont = scan.nextLine();
                        if (!Objects.equals(cont, "y") && !Objects.equals(cont, "n"))
                            System.out.println("Input 'y' or 'n'!");
                    }

                    if (cont.equalsIgnoreCase("n"))
                        searching = false;*/
                    break;
                case "2":
                    user.borrowedBooks();
                    break;
                case "3":
                    lib.showAvailable();
                    break;
                case "4":
                    searching = false;
                    break;
            }
        }
        System.out.println("Have a nice day!");
    }
}