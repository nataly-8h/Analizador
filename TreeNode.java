import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class TreeNode<T extends Comparable<?>> {
	TreeNode<String> left, right;
    String data;
    
	public TreeNode(TreeNode<String> left, TreeNode<String> right, String data) {
		this.left = left;
		this.right = right;
		this.data = data;
	}

    
}