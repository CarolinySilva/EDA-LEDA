package adt.stack;

public class StackImpl<T> implements Stack<T> {

	private T[] array;
	private int top;

	@SuppressWarnings("unchecked")
	public StackImpl(int size) {
		array = (T[]) new Object[size];
		top = -1;
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if(this.isFull()) {
			throw new StackOverflowException();
		} else {
			array[++top] = element;
		}
	}

	@Override
	public T pop() throws StackUnderflowException {
		
		if(this.isEmpty()) {
			throw new StackUnderflowException();
		} else {
			return array[top--];
		}
	}

	@Override
	public T top() {
		T result = null;
		
		if(!this.isEmpty()) {
			result = array[top];
		}
		
		return result;
	}

	@Override
	public boolean isEmpty() {
		boolean result = false;
		
		if(top == -1) {
			result = true;
		}
		
		return result;
	}

	@Override
	public boolean isFull() {
		boolean result = false;
		
		if(top == array.length - 1) {
			result = true;
		} 

		
		return result;
	}

}