package adt.avltree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.Util;

/**
 * 
 * Performs consistency validations within a AVL Tree instance
 * 
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements
		AVLTree<T> {

	// TODO Do not forget: you must override the methods insert and remove
	// conveniently.

	@Override
	public void insert(T element) {
		if (element != null) {
			BSTNode<T> node = super.insert(element, this.getRoot());
			rebalanceUp(node);
		}
	}

	@Override
	public void remove(T element) {

		BSTNode<T> node = search(element);
		BSTNode<T> toRebalance;

		if (!node.isEmpty() && element != null) {
			if (!node.isLeaf()) {
				toRebalance = (BSTNode<T>) super.sucessor(element);
			} else {
				toRebalance = (BSTNode<T>) node;
			}

			super.remove(node);
			rebalanceUp((BSTNode<T>) toRebalance);
		}
	}

	// AUXILIARY
	protected int calculateBalance(BSTNode<T> node) {
		if (node != null && !node.isEmpty()) {
			return this.height((BSTNode<T>) node.getRight())
					- this.height((BSTNode<T>) node.getLeft());
		}
		return 0;
	}

	// AUXILIARY
	protected void rebalance(BSTNode<T> node) {
		int height = calculateBalance(node);
		if (height > 1) {
			if (calculateBalance((BSTNode<T>) node.getRight()) < 0) {
				rightRotation((BSTNode<T>) node.getRight());
				leftRotation(node);
			} else {
				leftRotation(node);
			}
		} else if (height < -1) {
			if (calculateBalance((BSTNode<T>) node.getLeft()) > 0) {
				leftRotation((BSTNode<T>) node.getLeft());
				rightRotation(node);
			} else {
				rightRotation(node);
			}
		}
	}

	private void leftRotation(BSTNode<T> node) {
		BSTNode<T> pivot = (BSTNode<T>) node.getRight();

		if (this.getRoot() == node) {
			this.root = pivot;
		}
		Util.leftRotation(node);
	}

	private void rightRotation(BSTNode<T> node) {
		BSTNode<T> pivot = (BSTNode<T>) node.getLeft();

		if (this.getRoot() == node) {
			this.root = pivot;
		}
		Util.rightRotation(node);
	}

	// AUXILIARY
	protected void rebalanceUp(BSTNode<T> node) {
		if (node != null) {
			BSTNode<T> parent = (BSTNode<T>) node.getParent();
			while (parent != null) {
				this.rebalance(parent);
				parent = (BSTNode<T>) parent.getParent();
			}
		}
	}

}