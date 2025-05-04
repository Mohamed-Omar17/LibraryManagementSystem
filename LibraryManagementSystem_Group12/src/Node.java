class Node {
    String data;
    Node left, right;
    int height;

    public Node(String data) {
        this.data = data;
        this.left = this.right = null;
        this.height = 1;
    }
}