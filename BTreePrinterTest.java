import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Binary tree from Michal.kreuzman
// https://stackoverflow.com/questions/4965335/how-to-print-binary-tree-diagram/8948691 

public class BTreePrinterTest {

//    private static TreeNode<String> test1() {
//        TreeNode<String> root = new TreeNode<String>("S");
//        TreeNode<String> n11 = new TreeNode<String>("B");
//        TreeNode<String> n12 = new TreeNode<String>("D");
//        TreeNode<String> n21 = new TreeNode<String>("F");
//
//
//        root.left = n11;
//        root.right = n12;
//
//        n11.left = n21;
//
//
//
//        return root;
//    }

    public static void main(String[] args) {

        //BTreePrinter.printNode(test1());
       // BTreePrinter.printNode(test2());

    }
}



class BTreePrinter {

    public static <T extends Comparable<?>> void printNode(TreeNode<String> root) {
        int maxLevel = BTreePrinter.maxLevel(root);

        printNodeInternal(Collections.singletonList(root), 1, maxLevel);
    }

    private static <T extends Comparable<?>> void printNodeInternal(List<TreeNode<String>> nodes, int level, int maxLevel) {
        if (nodes.isEmpty() || BTreePrinter.isAllElementsNull(nodes))
            return;

        int floor = maxLevel - level;
        int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

        BTreePrinter.printWhitespaces(firstSpaces);

        List<TreeNode<String>> newNodes = new ArrayList<TreeNode<String>>();
        for (TreeNode<String> TreeNode : nodes) {
            if (TreeNode != null) {
                System.out.print(TreeNode.data);
                newNodes.add(TreeNode.left);
                newNodes.add(TreeNode.right);
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

    private static <T extends Comparable<?>> int maxLevel(TreeNode<String> TreeNode) {
        if (TreeNode == null)
            return 0;

        return Math.max(BTreePrinter.maxLevel(TreeNode.left), BTreePrinter.maxLevel(TreeNode.right)) + 1;
    }

    private static <String> boolean isAllElementsNull(List<String> list) {
        for (Object object : list) {
            if (object != null)
                return false;
        }

        return true;
    }

}