package adt.queue;

import adt.stack.Stack;
import adt.stack.StackImpl;
import adt.stack.StackOverflowException;

public class QueueUsingStack<T> implements Queue<T> {

	private Stack<T> stack1;
	private Stack<T> stack2;

	public QueueUsingStack(int size) {
		stack1 = new StackImpl<T>(size);
		stack2 = new StackImpl<T>(size);
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		try {
			stack1.push(element);
		} catch (StackOverflowException soe) {
			throw new QueueOverflowException();
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if (this.isEmpty()) {
			throw new QueueUnderflowException();
		}
		while (!stack1.isEmpty()) {
			try {
				stack2.push(stack1.pop());
			} catch (Exception e) {
				throw new RuntimeException();
			}
		}

		T tailValue = null;

		try {
			tailValue = stack2.pop();
		} catch (Exception e) {
			throw new RuntimeException();
		}

		while (!stack2.isEmpty()) {
			try {
				stack1.push(stack2.pop());
			} catch (Exception e) {
				throw new RuntimeException();
			}
		}

		return tailValue;
	}

	@Override
	public T head() {
		if (this.isEmpty()) {
			return null;
		}
		while (!stack1.isEmpty()) {
			try {
				stack2.push(stack1.pop());
			} catch (Exception e) {
				throw new RuntimeException();
			}
		}

		T tailValue = null;

		try {
			tailValue = stack2.top();
		} catch (Exception e) {
			throw new RuntimeException();
		}

		while (!stack2.isEmpty()) {
			try {
				stack1.push(stack2.pop());
			} catch (Exception e) {
				throw new RuntimeException();
			}
		}

		return tailValue;
	}

	@Override
	public boolean isEmpty() {
		return stack1.isEmpty();
	}

	@Override
	public boolean isFull() {
		return stack1.isFull();
	}

}