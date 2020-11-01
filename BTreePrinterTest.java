import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Binary tree from Michal.kreuzman
// https://stackoverflow.com/questions/4965335/how-to-print-binary-tree-diagram/8948691 

public class BTreePrinterTest {

    private static Node<String> test1() {
        Node<String> root = new Node<String>("S");
        Node<String> n11 = new Node<String>("B");
        Node<String> n12 = new Node<String>("D");
        Node<String> n21 = new Node<String>("F");


        root.left = n11;
        root.right = n12;

        n11.left = n21;



        return root;
    }

//    private static Node<String> test2() {
//        Node<String> root = new Node<String>(2);
//        Node<String> n11 = new Node<String>(7);
//        Node<String> n12 = new Node<String>(5);
//        Node<String> n21 = new Node<String>(2);
//        Node<String> n22 = new Node<String>(6);
//        Node<String> n23 = new Node<String>(9);
//        Node<String> n31 = new Node<String>(5);
//        Node<String> n32 = new Node<String>(8);
//        Node<String> n33 = new Node<String>(4);
//
//        root.left = n11;
//        root.right = n12;
//
//        n11.left = n21;
//        n11.right = n22;
//
//        n12.right = n23;
//        n22.left = n31;
//        n22.right = n32;
//
//        n23.left = n33;
//
//        return root;
//    }

    public static void main(String[] args) {

        BTreePrinter.printNode(test1());
       // BTreePrinter.printNode(test2());

    }
}

class Node<T extends Comparable<?>> {
    Node<T> left, right;
    T data;

    public Node(T data) {
        this.data = data;
    }
}

class BTreePrinter {

    public static <T extends Comparable<?>> void printNode(Node<T> root) {
        int maxLevel = BTreePrinter.maxLevel(root);

        printNodeInternal(Collections.singletonList(root), 1, maxLevel);
    }

    private static <T extends Comparable<?>> void printNodeInternal(List<Node<T>> nodes, int level, int maxLevel) {
        if (nodes.isEmpty() || BTreePrinter.isAllElementsNull(nodes))
            return;

        int floor = maxLevel - level;
        int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

        BTreePrinter.printWhitespaces(firstSpaces);

        List<Node<T>> newNodes = new ArrayList<Node<T>>();
        for (Node<T> node : nodes) {
            if (node != null) {
                System.out.print(node.data);
                newNodes.add(node.left);
                newNodes.add(node.right);
            } else {
                newNodes.add(null);
                newNodes.add(null);
                System.out.print(" ");
            }

            BTreePrinter.printWhitespaces(betweenSpaces);
        }
        System.out.println("");

        for (int i = 1; i <= endgeLines; i++) {
            for (int j = 0; j < nodes.size(); j++) {
                BTreePrinter.printWhitespaces(firstSpaces - i);
                if (nodes.get(j) == null) {
                    BTreePrinter.printWhitespaces(endgeLines + endgeLines + i + 1);
                    continue;
                }

                if (nodes.get(j).left != null)
                    System.out.print("/");
                else
                    BTreePrinter.printWhitespaces(1);

                BTreePrinter.printWhitespaces(i + i - 1);

                if (nodes.get(j).right != null)
                    System.out.print("\\");
                else
                    BTreePrinter.printWhitespaces(1);

                BTreePrinter.printWhitespaces(endgeLines + endgeLines - i);
            }

            System.out.println("");
        }

        printNodeInternal(newNodes, level + 1, maxLevel);
    }

    private static void printWhitespaces(int count) {
        for (int i = 0; i < count; i++)
            System.out.print(" ");
    }

    private static <T extends Comparable<?>> int maxLevel(Node<T> node) {
        if (node == null)
            return 0;

        return Math.max(BTreePrinter.maxLevel(node.left), BTreePrinter.maxLevel(node.right)) + 1;
    }

    private static <T> boolean isAllElementsNull(List<T> list) {
        for (Object object : list) {
            if (object != null)
                return false;
        }

        return true;
    }

}