package adt.bst.extended;

import java.util.ArrayList;
import java.util.Comparator;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;

/**
 * Implementacao de SortComparatorBST, uma BST que usa um comparator interno em
 * suas funcionalidades e possui um metodo de ordenar um array dado como
 * parametro, retornando o resultado do percurso desejado que produz o array
 * ordenado.
 * 
 * @author Adalberto
 *
 * @param <T>
 */
public class SortComparatorBSTImpl<T extends Comparable<T>> extends BSTImpl<T>
		implements SortComparatorBST<T> {

	private Comparator<T> comparator;

	public SortComparatorBSTImpl(Comparator<T> comparator) {
		super();
		this.comparator = comparator;
	}

	@Override
	public T[] sort(T[] array) {
		while (!root.isEmpty()) {
			remove(root.getData());
		}
		for (int i = 0; i < array.length; i++) {
			insert(array[i]);
		}
		T[] arr = this.order();

		return arr;
	}

	@Override
	public T[] reverseOrder() {

		ArrayList<T> list = new ArrayList<T>();
		reverseOrder(root, list);
		T[] array = list.toArray((T[]) new Comparable[this.size()]);
		return array;
	}

	private void reverseOrder(BSTNode<T> node, ArrayList<T> list) {
		if (!node.isEmpty()) {
			reverseOrder((BSTNode<T>) node.getRight(), list);
			visit(list, node);
			reverseOrder((BSTNode<T>) node.getLeft(), list);
		}

	}

	public Comparator<T> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			this.insert(element, this.getRoot(), (BSTNode<T>) this.getRoot()
					.getParent());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void insert(T element, BSTNode<T> node, BSTNode<T> parent) {
		if (node.isEmpty()) {
			node.setData(element);
			node.setLeft(new BSTNode.Builder<T>().parent(node).build());
			node.setRight(new BSTNode.Builder<T>().parent(node).build());
			node.setParent(parent);
		} else if (comparator.compare(element, node.getData()) < 0) {
			this.insert(element, (BSTNode<T>) node.getLeft(), node);
		} else {
			this.insert(element, (BSTNode<T>) node.getRight(), node);
		}
	}

	@Override
	public BSTNode<T> search(T element) {
		BSTNode<T> saida = new BSTNode<T>();
		if (element != null) {
			saida = treeSearch(root, element);
		}
		return saida;

	}

	@Override
	public BSTNode<T> treeSearch(BSTNode<T> node, T element) {
		if (node.isEmpty() || element.equals(node.getData())) {
			return node;
		} else if (comparator.compare(element, node.getData()) < 0) {
			return treeSearch((BSTNode<T>) node.getLeft(), element);

		} else {
			return treeSearch((BSTNode<T>) node.getRight(), element);
		}

	}

}