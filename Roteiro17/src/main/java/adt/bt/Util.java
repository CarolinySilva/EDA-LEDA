package adt.bt;

import adt.bst.BSTNode;

public class Util {

	/**
	 * A rotacao a esquerda em node deve subir e retornar seu filho a direita
	 * 
	 * @param root
	 * @return
	 */
	public static <T extends Comparable<T>> BSTNode<T> leftRotation(
			BSTNode<T> root) {
		// Pivot = Root.OS
		BSTNode<T> pivot = (BSTNode<T>) root.getRight();
		pivot.setParent(root.getParent());

		if (root.getParent() != null) {
			// node filho a esquerda
			if (root.getParent().getLeft().equals(root)) {
				root.getParent().setLeft(pivot);
			} else {
				root.getParent().setRight(pivot);
			}
		}

		root.setParent(pivot);
		// Root.OS = Pivot.RS
		root.setRight(pivot.getLeft());

		if (pivot.getLeft() != null) {
			pivot.getLeft().setParent(root);
		}
		// Pivot.RS = Root
		pivot.setLeft(root);

		return pivot;
	}

	/**
	 * A rotacao a direita em node deve subir e retornar seu filho a esquerda
	 * 
	 * @param node
	 * @return
	 */
	public static <T extends Comparable<T>> BSTNode<T> rightRotation(
			BSTNode<T> node) {
		BSTNode<T> leftNode = (BSTNode<T>) node.getLeft();
		leftNode.setParent(node.getParent());

		if (node.getParent() != null) {
			if (node.getParent().getLeft().equals(node)) {
				node.getParent().setLeft(leftNode);
			} else {
				node.getParent().setRight(leftNode);
			}
		}

		node.setParent(leftNode);
		node.setLeft(leftNode.getRight());

		if (leftNode.getRight() != null) {
			leftNode.getRight().setParent(node);
		}

		leftNode.setRight(node);

		return leftNode;
	}

	public static <T extends Comparable<T>> T[] makeArrayOfComparable(int size) {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Comparable[size];
		return array;
	}
}