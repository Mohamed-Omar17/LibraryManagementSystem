import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BalancedBST {
    private ArrayList<String> books;
    Node root;
    private LibraryStorage storage;

    public BalancedBST(LibraryStorage lib) {
        this.books = new ArrayList<>();
        storage = lib;
    }

    public void printInOrder() {
        printInOrder(this.root);
    }

    private void printInOrder(Node node) {
        if (node != null) {
            printInOrder(node.left);
            System.out.print(node.data + " ");
            printInOrder(node.right);
        }
    }

    public boolean search(String target) {
        long startTime = System.nanoTime();
        boolean found = searchRec(root, target);
        long endTime = System.nanoTime();
        System.out.println("Total time: " + (endTime - startTime));
        return found;
    }

    private boolean searchRec(Node root, String target) {
        if (root == null) return false;
        if (root.data.equalsIgnoreCase(target)) {
            return true;
        }
        if (target.compareToIgnoreCase(root.data) < 0)
            return searchRec(root.left, target);
        else
            return searchRec(root.right, target);
    }

    public void insertNode(String book) {
        long startTime = System.nanoTime();
        book = book.toLowerCase();
        books.add(book);
        storage.addBook(book);
        root = insertNodeRec(root, book);
        long endTime = System.nanoTime();
        System.out.println("Book insertion Time of " + book + ": " + (endTime-startTime));
    }

    private Node insertNodeRec(Node node, String book) {
        if (node == null) return new Node(book);
        if (book.compareToIgnoreCase(node.data) < 0) {
            node.left = insertNodeRec(node.left, book);
        } else if (book.compareToIgnoreCase(node.data) > 0) {
            node.right = insertNodeRec(node.right, book);
        } else {
            return node;
        }

        node.height = 1 + Math.max(height(node.left), height(node.right));
        return balanceTree(node, book);
    }

    private Node balanceTree(Node node, String book) {
        int balance = getBalance(node);
        if (balance > 1 && book.compareToIgnoreCase(node.left.data) < 0)
            return rightRotate(node);
        if (balance < -1 && book.compareToIgnoreCase(node.right.data) > 0)
            return leftRotate(node);
        if (balance > 1 && book.compareToIgnoreCase(node.left.data) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        if (balance < -1 && book.compareToIgnoreCase(node.right.data) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }

    public void printLevelOrder() {
        if (root == null) return;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            System.out.println(current.data + " (Balance Factor: " + getBalance(current) + ")");
            if (current.left != null) queue.add(current.left);
            if (current.right != null) queue.add(current.right);
        }
    }

    public void removeBook(String book) {
        book = book.toLowerCase();
        root = removeBookRec(root, book);
    }

    private Node removeBookRec(Node node, String book) {
        if (node == null) return null;

        if (book.compareToIgnoreCase(node.data) < 0) {
            node.left = removeBookRec(node.left, book);
        } else if (book.compareToIgnoreCase(node.data) > 0) {
            node.right = removeBookRec(node.right, book);
        } else {
            if (node.left == null || node.right == null) {
                Node temp = (node.left != null) ? node.left : node.right;
                node = (temp != null) ? temp : null;
            } else {
                Node temp = minValueNode(node.right);
                node.data = temp.data;
                node.right = removeBookRec(node.right, temp.data);
            }
        }

        if (node == null) return node;
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        return balanceTree(node, book);
    }

    private Node minValueNode(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    private int height(Node node) {
        return (node == null) ? 0 : node.height;
    }

    private int getBalance(Node node) {
        return (node == null) ? 0 : height(node.left) - height(node.right);
    }

    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;
        x.right = y;
        y.left = T2;
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        return x;
    }

    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;
        y.left = x;
        x.right = T2;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        return y;
    }
}
