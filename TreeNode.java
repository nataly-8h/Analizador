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

	public TreeNode<String> getLeft() {
		return left;
	}

	public void setLeft(TreeNode<String> left) {
		this.left = left;
	}

	public TreeNode<String> getRight() {
		return right;
	}

	public void setRight(TreeNode<String> right) {
		this.right = right;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

    
}