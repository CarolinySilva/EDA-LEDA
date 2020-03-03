package adt.bst;

import java.util.ArrayList;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

	protected BSTNode<T> root;

	public BSTImpl() {
		root = new BSTNode<T>();
	}

	public BSTNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

	@Override
	public int height() {
		return height(root);
	}

	private int height(BSTNode<T> no) {
		int height = -1;
		if (!no.isEmpty()) {

			int heightLeft, heightRight;
			heightLeft = height((BSTNode<T>) no.getLeft());
			heightRight = height((BSTNode<T>) no.getRight());
			height = Math.max(heightLeft, heightRight) + 1;
		}
		return height;
	}

	@Override
	public BSTNode<T> search(T element) {
		BSTNode<T> saida = new BSTNode<T>();
		if (element != null) {
			saida = treeSearch(root, element);
		}
		return saida;

	}

	public BSTNode<T> treeSearch(BSTNode<T> node, T element) {
		if (node.isEmpty() || element.equals(node.getData())) {
			return node;
		} else if (element.compareTo(node.getData()) < 0) {
			return treeSearch((BSTNode<T>) node.getLeft(), element);

		} else {
			return treeSearch((BSTNode<T>) node.getRight(), element);
		}

	}

	@Override
	public void insert(T element) {
		if (element != null) {
			this.insert(element, this.getRoot(), (BSTNode<T>) this.getRoot()
					.getParent());
		}
	}

	@SuppressWarnings("unchecked")
	public void insert(T element, BSTNode<T> node, BSTNode<T> parent) {
		if (node.isEmpty()) {
			node.setData(element);
			node.setLeft(new BSTNode.Builder<T>().parent(node).build());
			node.setRight(new BSTNode.Builder<T>().parent(node).build());
			node.setParent(parent);
		} else if (element.compareTo(node.getData()) < 0) {
			this.insert(element, (BSTNode<T>) node.getLeft(), node);
		} else {
			this.insert(element, (BSTNode<T>) node.getRight(), node);
		}
	}

	@Override
	public BSTNode<T> maximum() {
		return maximum(root);
	}

	@Override
	public BSTNode<T> minimum() {

		return minimum(root);

	}

	private BSTNode<T> minimum(BSTNode<T> node) {
		BSTNode<T> saida = null;
		if (!node.isEmpty()) {
			while (!node.getLeft().isEmpty()) {
				node = (BSTNode<T>) node.getLeft();
			}
			saida = node;

		}
		return saida;

	}

	private BSTNode<T> maximum(BSTNode<T> node) {
		BSTNode<T> saida = null;
		if (!node.isEmpty()) {
			while (!node.getRight().isEmpty()) {
				node = (BSTNode<T>) node.getRight();
			}
			saida = node;

		}
		return saida;
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		BSTNode<T> node = search(element);
		BSTNode<T> sucessor = null;
		if (!node.isEmpty()) {
			if (!node.getRight().isEmpty()) {
				sucessor = minimum((BSTNode<T>) node.getRight());
			} else {
				sucessor = sucessor(node, element);
			}
		}
		return sucessor;
	}

	private BSTNode<T> sucessor(BSTNode<T> node, T element) {
		BSTNode<T> sucessor = null;
		if (node.getParent() != null) {
			if (node.getParent().getData().compareTo(element) > 0) {
				sucessor = (BSTNode<T>) node.getParent();
			} else {
				sucessor = sucessor((BSTNode<T>) node.getParent(), element);
			}
		}
		return sucessor;
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		BSTNode<T> node = search(element);
		BSTNode<T> predecessor = null;
		if (!node.isEmpty()) {
			if (!node.getLeft().isEmpty()) {
				predecessor = maximum((BSTNode<T>) node.getLeft());
			} else {
				predecessor = predecessor(node, element);
			}
		}
		return predecessor;
	}

	private BSTNode<T> predecessor(BSTNode<T> node, T element) {
		BSTNode<T> predecessor = null;
		if (node.getParent() != null) {
			if (node.getParent().getData().compareTo(element) < 0) {
				predecessor = (BSTNode<T>) node.getParent();
			} else {
				predecessor = predecessor((BSTNode<T>) node.getParent(),
						element);
			}
		}
		return predecessor;
	}

	@Override
	public void remove(T element) {
		BSTNode<T> node = search(element);
		remove(node);
	}

	private void remove(BSTNode<T> node) {
		if (!node.isEmpty()) {

			if (node.isLeaf()) {

				node.setData(null);
				node.setLeft(null);
				node.setRight(null);

			} else if (oneChild(node)) {

				if (node != root) {
					if (node.getParent().getLeft() == node) {

						if (node.getLeft().isEmpty()) {
							node.getParent().setLeft(node.getRight());
							node.getRight().setParent(node.getParent());
						} else {
							node.getParent().setLeft(node.getLeft());
							node.getLeft().setParent(node.getParent());
						}

					} else {

						if (node.getLeft().isEmpty()) {
							node.getParent().setRight(node.getRight());
							node.getRight().setParent(node.getParent());
						} else {
							node.getParent().setRight(node.getLeft());
							node.getLeft().setParent(node.getParent());
						}

					}

				} else {
					if (root.getLeft().isEmpty()) {
						root = (BSTNode<T>) root.getRight();
					} else {
						root = (BSTNode<T>) root.getLeft();
					}
				}

			} else {
				BSTNode<T> sucessor = sucessor(node.getData());
				node.setData(sucessor.getData());
				remove(sucessor);

			}

		}

	}

	private boolean oneChild(BSTNode<T> node) {
		return (!node.getLeft().isEmpty() && node.getRight().isEmpty() || node
				.getLeft().isEmpty() && !node.getRight().isEmpty());
	}

	@Override
	public T[] preOrder() {

		ArrayList<T> list = new ArrayList<T>();
		preOrder(root, list);
		@SuppressWarnings("unchecked")
		T[] array = list.toArray((T[]) new Comparable[this.size()]);
		return array;

	}

	private void preOrder(BSTNode<T> node, ArrayList<T> list) {

		if (!node.isEmpty()) {
			visit(list, node);
			preOrder((BSTNode<T>) node.getLeft(), list);
			preOrder((BSTNode<T>) node.getRight(), list);
		}

	}

	public void visit(ArrayList<T> list, BSTNode<T> node) {

		list.add(node.getData());

	}

	@Override
	public T[] order() {

		ArrayList<T> list = new ArrayList<T>();
		order(root, list);
		@SuppressWarnings("unchecked")
		T[] array = list.toArray((T[]) new Comparable[this.size()]);
		return array;
	}

	private void order(BSTNode<T> node, ArrayList<T> list) {
		if (!node.isEmpty()) {
			order((BSTNode<T>) node.getLeft(), list);
			visit(list, node);
			order((BSTNode<T>) node.getRight(), list);
		}

	}

	@Override
	public T[] postOrder() {

		ArrayList<T> list = new ArrayList<T>();
		postOrder(root, list);
		@SuppressWarnings("unchecked")
		T[] array = list.toArray((T[]) new Comparable[this.size()]);
		return array;
	}

	private void postOrder(BSTNode<T> node, ArrayList<T> list) {
		if (!node.isEmpty()) {

			postOrder((BSTNode<T>) node.getLeft(), list);

			postOrder((BSTNode<T>) node.getRight(), list);
			visit(list, node);

		}

	}

	/**
	 * This method is already implemented using recursion. You must understand
	 * how it work and use similar idea with the other methods.
	 */
	@Override
	public int size() {
		return size(root);
	}

	private int size(BSTNode<T> node) {
		int result = 0;
		// base case means doing nothing (return 0)
		if (!node.isEmpty()) { // indusctive case
			result = 1 + size((BSTNode<T>) node.getLeft())
					+ size((BSTNode<T>) node.getRight());
		}
		return result;
	}

}